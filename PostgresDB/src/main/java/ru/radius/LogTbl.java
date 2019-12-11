/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LogTbl {

    private Connection connection;

    private static LogTbl instance;

    public static LogTbl getInstance() throws SQLException {
        LogTbl localInstance = instance;
        if(localInstance == null){
            synchronized (LogTbl.class){
                localInstance = instance;
                if(localInstance == null){
                    localInstance = instance = new LogTbl();
                }
            }
        }
        return  localInstance;
    }

    private LogTbl() throws SQLException {
        this.connection = PostgresConnector.getInstance().getConnection();
    }

    public boolean write(int idTable, String dateBegin, String dateEnd, String status, String message, int rowsProcessed) throws SQLException {

        String sql = String.format("INSERT INTO hist.log_table VALUES((SELECT max(id) from hist.log_table) + 1 ,%s,'%s','%s','%s','%s',%s)",
                idTable,
                dateBegin,
                dateEnd,
                status,
                message,
                rowsProcessed
                );
        System.out.println(sql);
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate(sql);
        stmt.close();
        return res > 0;
//        PostgresConnector.getInstance().getConnection().commit();
    }

    public static void main(String[] args) throws SQLException {
        boolean res = LogTbl.getInstance().write(1, "1912-12-12 12:12:12", "1921-12-12 12:12:12", "end", "messge", 12);
        System.out.println(res);

        System.out.println();

    }









}
