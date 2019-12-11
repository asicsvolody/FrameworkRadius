/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablesTbl {
    private Connection connection;

    private static TablesTbl instance;

    public static TablesTbl getInstance() throws SQLException {
        TablesTbl localInstance = instance;
        if(localInstance == null){
            synchronized (TablesTbl.class){
                localInstance = instance;
                if(localInstance == null){
                    localInstance = instance = new TablesTbl();
                }
            }
        }
        return  localInstance;
    }

    private TablesTbl() throws SQLException {
        this.connection = PostgresConnector.getInstance().getConnection();
    }


    public boolean addTable(Table table) throws SQLException {

        int idTable = getNextId();
        String sql = String.format("INSERT INTO hist.tables VALUES(%s,%s,'%s','%s','%s','%s','%s')",
                idTable,
                table.getIdSource(),
                table.getSrcSchema(),
                table.getSrcTable(),
                PostgreSqlUtils.dateNow(),
                table.getLastLogFile(),
                table.getLastLogFileLoc2()
        );
        System.out.println(sql);

        Statement stmt = connection.createStatement();

        stmt.execute("BEGIN");
        int res = stmt.executeUpdate(sql);
        for (Column column : table.getColumns()) {
            sql = String.format("INSERT INTO hist.columns VALUES(" +
                            "(SELECT max(id) from hist.columns) + 1,%s,'%s',%s,'%s',%s,%s)",
                    idTable,
                    column.getName(),
                    column.getPosition(),
                    column.getType(),
                    column.isPk(),
                    column.getNumPartColumn()
                    );
            System.out.println(sql);
            res +=stmt.executeUpdate(sql);
        }

        stmt.execute("COMMIT");
        stmt.close();
        return res > 1;
    }

    private int getNextId() throws SQLException {
        String sql = "SELECT max(id) FROM hist.tables";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int res = 0;
        if(rs.next()){
            res =  rs.getInt(1);
        }
        stmt.close();
        rs.close();
        return ++res;
    }



    public static void main(String[] args) throws SQLException {
//        boolean res = TablesTbl.getInstance().addTable(1, "usersDB", "users","last_log_file", "Last_log_file_two");
//        System.out.println(res);
//
//        System.out.println();

    }
}
