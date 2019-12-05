/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSUtils {
    public static void deleteDir(Path path) throws IOException {
        FileSystem fs = FileSystem.get(ParquetToHiveTable.spark.sparkContext().hadoopConfiguration());
        if(fs.exists(path))
            System.out.println("Delete dir From");
            fs.delete(path, true);
    }
}
