/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius.createJson;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import ru.radius.JsonToHiveTable;

import java.io.IOException;
import java.io.Serializable;

public class JsonCreator implements Serializable {

    public void jsonCreator(String dirTo){
        SparkSession spark = JsonToHiveTable.spark;

        Dataset<Row> data = spark.read().parquet("/tmp/usersDB/users");
        data.show();
        Path path = new Path(dirTo);

        try {
        FileSystem fs = FileSystem.get(spark.sparkContext().hadoopConfiguration());
            if(fs.exists(path))
                fs.delete(path, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        data.write().json(dirTo);
    }

    public static void main(String[] args) {
        new JsonCreator().jsonCreator("/KyloUsers");
    }

}
