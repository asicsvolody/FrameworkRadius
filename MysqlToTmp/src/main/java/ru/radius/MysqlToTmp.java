/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SparkSession;

import java.io.*;

public class MysqlToTmp {

    private static String PASSWORD_FILE_PATH_DIR = "./";

    private static Path HADOOP_PASSWORD_FILE = new Path("/sqoopPasswords/sqoop.password");
    public static SparkSession spark;

    static{
        String warehouseLocation = new File("./metastore_db").getAbsolutePath();
        spark = SparkSession
                .builder()
                .appName("kyloRadius")
                .config("spark.master", "local")
                .config ("hive.exec.dynamic.partition", "true")
                .config ("hive.exec.dynamic.partition.mode", "nonstrict")
                .config("hive.txn.manager", "org.apache.hadoop.hive.ql.lockmgr.DbTxnManager")
                .config("hive.support.concurrency", "true")
                .config("hive.enforce.bucketing", "true")
//                .config("hive.metastore.uris", "jdbc:mysql://localhost:3306/metastore?serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL")
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .config("spark.hadoop.fs.default.name", "hdfs://localhost:8020")
                .config("spark.hadoop.fs.defaultFS", "hdfs://localhost:8020")
                .config("spark.hadoop.fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName())
                .config("spark.hadoop.fs.hdfs.server", org.apache.hadoop.hdfs.server.namenode.NameNode.class.getName())
                .config("spark.hadoop.conf", org.apache.hadoop.hdfs.HdfsConfiguration.class.getName())
                .enableHiveSupport()
                .getOrCreate();
        spark.sparkContext().setLogLevel("WARN");

    }


    private MySqlToTmpConfig config;

    public MysqlToTmp(MySqlToTmpConfig config) {
        this.config = config;
    }


    public void exportDataToTmp() throws IOException, InterruptedException {
        System.out.println("Sqoop get task import");

        FileSystem fs = FileSystem.get(spark.sparkContext().hadoopConfiguration());

        Path tmpDir = new Path(config.getTmpDir());
        if(fs.exists(tmpDir)){
            fs.delete(tmpDir, true);
            System.out.println("Delete tmp dir: "+tmpDir.toString());
        }
        fs.mkdirs(tmpDir);
        System.out.println("Tmp dir have been created");

        createPassword(config.getPassDB());


        System.out.println("Sqoop run process");
        String command = String.format("sqoop import " +
                        "--connect jdbc:mysql://%s:%s/%s?serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL " +
                        "--username %s " +
                        "--password-file %s " +
                        "--table %s " +
                        "--target-dir %s " +
                        "--split-by %s " +
                        "--as-parquetfile"
                ,config.getHostDB()
                ,config.getPortDB()
                ,config.getFromDB()
                ,config.getUserDB()
                ,HADOOP_PASSWORD_FILE.toString()
                ,config.getFromTable()
                ,config.getTmpDir()
                ,config.getPrimaryKeys()
        );
        System.out.println(command);

        Process process = Runtime.getRuntime().exec(command);

        System.out.println("Waiting of end sqoop process");

        process.waitFor();

        writeProcessMessageStream(process);

        System.out.println(
                "sqoop" +
                        ((process.exitValue() == 0)?"Sqoop successfully":"Sqoop error")
        );


        System.out.println("Delete password: " + HADOOP_PASSWORD_FILE.toString());

        if(fs.exists(HADOOP_PASSWORD_FILE)){
            fs.delete(HADOOP_PASSWORD_FILE, true);
        }
    }

    public void createPassword(String password) throws IOException {

        File passwordFile = new File(PASSWORD_FILE_PATH_DIR+ "/" + HADOOP_PASSWORD_FILE.getName());
        System.out.println("Wold be created password file: "+ passwordFile.getPath());
        FileSystem fs = FileSystem.get(spark.sparkContext().hadoopConfiguration());


        try(FileWriter fw = new FileWriter(passwordFile)) {
            fw.write(password);
        }

        if(passwordFile.exists()){
            System.out.println("DB-password have been creat");
        }

        if(!fs.exists(HADOOP_PASSWORD_FILE.getParent())) {
            fs.mkdirs(HADOOP_PASSWORD_FILE.getParent());
            System.out.println("Creating password directory "+HADOOP_PASSWORD_FILE.getParent().toString());

        }

        if(fs.delete(HADOOP_PASSWORD_FILE,true)){
            System.out.println("old password have been delete");
        }

        System.out.println("Copy password to hdfs : "+ HADOOP_PASSWORD_FILE.toString());
        fs.copyFromLocalFile(new Path(passwordFile.toString()), HADOOP_PASSWORD_FILE.getParent());
        if(fs.exists(HADOOP_PASSWORD_FILE)){
            System.out.println("password have been load to hdfs "+ HADOOP_PASSWORD_FILE.toString());
        }

        System.out.println("Delete password from local file system");
        passwordFile.deleteOnExit();
    }

    public void writeProcessMessageStream(Process process){
        String line;
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while((line = input.readLine()) != null){
                System.out.println(line);
            }
            input.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new MysqlToTmp(MySqlToTmpConfig.getConfig(args))
                .exportDataToTmp();
    }
}
