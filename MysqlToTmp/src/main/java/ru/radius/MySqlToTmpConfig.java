/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;


public class MySqlToTmpConfig {
    private String hostDB;
    private String portDB;

    private String userDB;
    private String passDB;

    private String fromDB;
    private String fromTable;

    private String tmpDir;
    private String primaryKeys;


    public MySqlToTmpConfig(String hostDB,
                            String portDB,
                            String userDB,
                            String passDB,
                            String fromDB,
                            String fromTable,
                            String tmpDir,
                            String primaryKeys) {
        this.hostDB = hostDB;
        this.portDB = portDB;
        this.userDB = userDB;
        this.passDB = passDB;
        this.fromDB = fromDB;
        this.fromTable = fromTable;
        this.tmpDir = tmpDir;
        this.primaryKeys = primaryKeys;
    }

    public static MySqlToTmpConfig getConfig(String[] args) throws Exception {
        if(args.length < 8)
            throw new Exception("Args is not correct");
        String hostDB = args[0];
        String baseTo = args[1];

        String userDB = args[2];
        String passDB = args[3];

        String fromDB = args[4];
        String fromTable = args[5];

        String tmpDir = args[6];
        String primaryKeys = args[7];

        return new MySqlToTmpConfig(hostDB,baseTo,userDB, passDB, fromDB, fromTable, tmpDir,primaryKeys);
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


    public String getPrimaryKeys() {
        return primaryKeys;
    }

    public String getHostDB() {
        return hostDB;
    }

    public String getPortDB() {
        return portDB;
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


    public void setHostDB(String hostDB) {
        this.hostDB = hostDB;
    }

    public void setPortDB(String portDB) {
        this.portDB = portDB;
    }
}
