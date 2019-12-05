/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.util.ArrayList;

public class MysqlToHiveTable {

    private MysqlToHiveConfig config;

    public MysqlToHiveTable(MysqlToHiveConfig config) {
        this.config = config;
    }

    public void mysqlToHiveTable() throws Exception {
        System.out.println("Export data from mysql.server to tmp dir "+ config.getTmpDir());
        String[] mysqlToTmpArgs = new String[]{
                config.getHostDB(),
                config.getPortDB(),
                config.getUserDB(),
                config.getPassDB(),
                config.getFromDB(),
                config.getFromTable(),
                config.getTmpDir(),
                config.getPrimaryKeys()
        };

        MysqlToTmp.main(mysqlToTmpArgs);

        System.out.println("Export data from tmp to hive table");

        ArrayList<String> parquetToHiveTableList= new ArrayList<>();
        parquetToHiveTableList.add(config.getTmpDir());
        parquetToHiveTableList.add(config.getFromDB());
        parquetToHiveTableList.add(config.getFromTable());
        parquetToHiveTableList.addAll(config.getPartitions());

        ParquetToHiveTable.main(parquetToHiveTableList.toArray(new String[0]));
    }
    public static void main(String[] args) throws Exception {
        new MysqlToHiveTable(MysqlToHiveConfig.getConfig(args))
                .mysqlToHiveTable();
    }
}
