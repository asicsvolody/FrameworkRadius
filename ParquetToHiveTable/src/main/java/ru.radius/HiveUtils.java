/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HiveUtils {
    private enum TableType{
        EXTERNAL, TRANSACTIONAL
    }

    private static synchronized void createHiveTable(List<String> columnsArr, TableType type, String schema, String table, List<String> partitions) throws Exception {
        SparkSession spark = ParquetToHiveTable.spark;

        System.out.println("Creating database: "+ schema);

        spark.sql(String.format("CREATE DATABASE IF NOT EXISTS %s LOCATION '/%s'",schema, schema));

        System.out.println("Creating table: "+ table);
        System.out.println("Delete old table if exist");

        spark.sql(String.format("DROP TABLE IF EXISTS %s.%s", schema, table));


        FileSystem fs = FileSystem.get(spark.sparkContext().hadoopConfiguration());
        Path hiveDir = new Path("/kyloRadius/"+schema + Utils.SEPARATOR + table);

        if(fs.exists(hiveDir)) {
            System.out.println("Delete old directory");
            fs.delete(hiveDir, true);
        }


        String cols = String.join(", ",
                getUsualColumns(columnsArr, partitions)
        );

        String partitionsStr = String.join(",", partitions);

        System.out.println(cols);

        String hiveScript = String.format( getCreateScript(type)
                ,schema, table, cols, partitionsStr, hiveDir.toString() );

        System.out.println(hiveScript);

        spark.sql(hiveScript);

    }

    public static List<String> getUsualColumns(List<String> cols, List<String> partitions) {
        return cols.stream()
                .filter(v ->!partitions.contains(v))
                .collect(Collectors.toList());

    }

    private static String getCreateScript(TableType type){
        switch (type){
            case EXTERNAL:
                return "CREATE EXTERNAL TABLE IF NOT EXISTS %s.%s \n" +
                        " (%s) \n" +
                        " PARTITIONED BY (%s)\n" +
                        " STORED AS PARQUET \n" +
                        " LOCATION '%s' ";

            case TRANSACTIONAL:
                return "CREATE TABLE IF NOT EXISTS %s.%s \n" +
                        " (%s) \n" +
                        " PARTITIONED BY (%s)\n" +
                        " STORED AS ORC \n" +
                        " LOCATION '%s' " +
                        "TBLPROPERTIES ('transactional'='true')";
        }
        return null;
    }


    public static void createTransactionalHiveTable(List<String> listColumns, ParquetToHiveTableConfig config) throws Exception {
        createHiveTable(listColumns, TableType.TRANSACTIONAL, config.getBaseTo(), config.getTableTo(),  config.getPartitions());
    }

    private static List<String> getColumnsHiveTable(String databaseTo, String tableTo) throws XMLStreamException, IOException, SQLException {
        return ParquetToHiveTable.spark
                .sql(String.format("DESC %s.%s",databaseTo,tableTo))
                .toJavaRDD()
                .map(row -> row.getString(0)
                        .trim())
                .filter(v -> !v.startsWith("#"))
                .collect();
    }

    public static void insetToHiveTable(ParquetToHiveTableConfig config) throws Exception {
        SparkSession spark = ParquetToHiveTable.spark;

        String tmpTableName = "tmp_"+config.getPathFrom().replace("/","_");

        System.out.println("Read table columns");

        List<String> tableCols = getColumnsHiveTable(config.getBaseTo(), config.getTableTo());

        System.out.println(tableCols.toString());

        System.out.println("Spark read data from dir "+ config.getPathFrom());

        String sparkScript = String.format("CREATE TEMPORARY VIEW %s USING parquet OPTIONS(path '%s')",tmpTableName, config.getPathFrom());

        System.out.println(sparkScript);

        spark.sql(sparkScript);

        Dataset<Row> data = spark.sql("SELECT * FROM "+tmpTableName);

        data.show();

        String usualCols = String.join(","
                , Utils.getColsAndNullNoPartitions(
                        tableCols,
                        Arrays.asList(data.schema().fieldNames()),
                        Utils.getColumnNameOnly(config.getPartitions())
                )

        );

        System.out.println(usualCols);


        String partitionsCols= String.join(", ",Utils.getColumnNameOnly(config.getPartitions()));

        System.out.println("Write data to Hive Table");

        String hiveScript = String.format(
                "INSERT INTO %s.%s PARTITION(%s) SELECT %s,%s FROM %s"
                , config.getBaseTo()
                ,config.getTableTo()
                ,partitionsCols
                ,usualCols
                ,partitionsCols
                ,tmpTableName
        );

        System.out.println(hiveScript);

        spark.sql(hiveScript);
    }
}
