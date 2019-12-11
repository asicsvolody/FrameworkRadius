/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlToHiveConfig {
    private String hostDB;
    private String portDB;

    private String userDB;
    private String passDB;

    private String fromDB;
    private String fromTable;

    private String tmpDir;
    private String hiveDirTo;

    private String primaryKeys;

    private  List<String> partitions;

    public MysqlToHiveConfig(String hostDB,
                             String portDB,
                             String userDB,
                             String passDB,
                             String fromDB,
                             String fromTable,
                             String tmpDir,
                             String hiveDirTo,
                             String primaryKeys,
                             List<String> partitions) {
        this.hostDB = hostDB;
        this.portDB = portDB;
        this.userDB = userDB;
        this.passDB = passDB;
        this.fromDB = fromDB;
        this.fromTable = fromTable;
        this.tmpDir = tmpDir;
        this.hiveDirTo = hiveDirTo;
        this.primaryKeys = primaryKeys;
        this.partitions = partitions;
    }


    //localhost 3306 root bhbyf.hnftdf usersDB users /tmp/tmpDir /hiveDir user_id user_age/int

    public static MysqlToHiveConfig getConfig(String[] args) throws Exception {
        if(args.length < 9)
            throw new Exception("Args is not correct");
        String hostDB = args[0];
        String baseTo = args[1];

        String userDB = args[2];
        String passDB = args[3];

        String fromDB = args[4];
        String fromTable = args[5];

        String tmpDir = args[6];
        String hiveDirTo = args[7];
        String primaryKeys = args[8];


        List<String> partitions = Arrays.stream(Arrays.copyOfRange(args, 9, args.length))
                .map(v -> String.join(" ",v.split("/",2)))
                .collect(Collectors.toList());
        return new MysqlToHiveConfig(hostDB,baseTo,userDB, passDB, fromDB, fromTable, tmpDir, hiveDirTo,primaryKeys, partitions);
    }

    public String getUserDB() {
        return userDB;
    }

    public String getPassDB() {
        return passDB;
    }

    public String getFromDB() {
        return fromDB;
    }

    public String getFromTable() {
        return fromTable;
    }

    public String getTmpDir() {
        return tmpDir;
    }

    public String getHiveDirTo() {
        return hiveDirTo;
    }

    public String getPrimaryKeys() {
        return primaryKeys;
    }

    public String getHostDB() {
        return hostDB;
    }

    public String getPortDB() {
        return portDB;
    }

    public List<String> getPartitions() {
        return partitions;
    }

    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }

    public void setPassDB(String passDB) {
        this.passDB = passDB;
    }

    public void setFromDB(String fromDB) {
        this.fromDB = fromDB;
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable;
    }

    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir;
    }

    public void setHiveDirTo(String hiveDirTo) {
        this.hiveDirTo = hiveDirTo;
    }

    public void setHostDB(String hostDB) {
        this.hostDB = hostDB;
    }

    public void setPortDB(String portDB) {
        this.portDB = portDB;
    }
}
