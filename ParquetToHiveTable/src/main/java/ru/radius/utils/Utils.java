/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius.utils;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import ru.radius.ParquetToHiveTable;
import ru.radius.config.ParquetToHiveTableConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.spark.sql.types.DataTypes.*;

public class Utils {

    public static String SEPARATOR = "/";

    public static List<String> getFormattingColsFromDir(ParquetToHiveTableConfig config) throws Exception {


        System.out.println("Spark read schema from dir "+ config.getPathFrom());
        StructType schema = ParquetToHiveTable.spark
                .read()
                .parquet(config.getPathFrom() + SEPARATOR + "*.parquet").schema();

        System.out.println(schema.toString());

        List<String> colsList = getFormattingCols(schema);
        System.out.println("Checking portions fields");

        checkPartitions(colsList, config.getPartitions());

        return colsList;
    }



    public static List<String> getFormattingCols(StructType type) {
        return Arrays.stream(type.fields())
                .map(v -> v.name().toLowerCase() + " " + convertSparkTypeToHiveTypeStr(v.dataType()))
                .collect(Collectors.toList());
    }

    private static String convertSparkTypeToHiveTypeStr(DataType type){
        if (BinaryType.equals(type)) {
            return "binary";
        } else if (BooleanType.equals(type)) {
            return "boolean";
        } else if (ByteType.equals(type)) {
            return "tinyint";
        } else if (CalendarIntervalType.equals(type)) {
            return "interval";
        } else if (DateType.equals(type)) {
            return "date";
        } else if (DoubleType.equals(type)) {
            return "double";
        } else if (FloatType.equals(type)) {
            return "float";
        } else if (IntegerType.equals(type)) {
            return "int";
        } else if (LongType.equals(type)) {
            return "bigint";
        } else if (NullType.equals(type)) {
            return "null";
        } else if (ShortType.equals(type)) {
            return "smallint";
        } else if (StringType.equals(type)) {
            return "string";
        } else if (TimestampType.equals(type)) {
            return "timestamp";
        }
        return null;
    }

    public static void checkPartitions(List<String> columnsList,  List<String> partitions) throws Exception {
        Optional<String> colOpt = partitions
                .stream()
                .filter(v -> !columnsList.contains(v))
                .findAny();
        if(colOpt.isPresent())
            throw new Exception ("There is not partition columns");
    }

    public static List<String> getColsAndNullNoPartitions(List<String> tableColumnNames, List<String> dataColumnNames,  List<String> partitions) {
        return tableColumnNames.stream()
                .filter(v -> !partitions.contains(v))
                .map(v -> (dataColumnNames.contains(v)) ? v : "null as " + v)
                .collect(Collectors.toList());
    }

    public static List<String> getColumnNameOnly(List<String> colArr){
        return colArr.stream().map(v -> v.split("\\s")[0]).collect(Collectors.toList());

    }
}
