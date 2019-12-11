/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ColumnsTbl {
    private Connection connection;

    private static ColumnsTbl instance;

    public static ColumnsTbl getInstance() throws SQLException {
        ColumnsTbl localInstance = instance;
        if(localInstance == null){
            synchronized (ColumnsTbl.class){
                localInstance = instance;
                if(localInstance == null){
                    localInstance = instance = new ColumnsTbl();
                }
            }
        }
        return  localInstance;
    }

    private ColumnsTbl() throws SQLException {
        this.connection = PostgresConnector.getInstance().getConnection();
    }

    public boolean addColumn(int idTable, String name, int position, String type, boolean pk, Integer numPartColumn) throws SQLException {

        String sql = String.format("INSERT INTO hist.columns VALUES((SELECT max(id) from hist.columns) + 1 ,%s,'%s',%s,'%s',%s,%s)",
                idTable,
                name,
                position,
                type,
                pk,
                numPartColumn
        );
        System.out.println(sql);
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate(sql);
        stmt.close();
        return res > 0;
//        PostgresConnector.getInstance().getConnection().commit();
    }

    public static void main(String[] args) throws SQLException {
        boolean res = ColumnsTbl.getInstance().addColumn(1, "user_name", 1, "int",true, null);
        System.out.println(res);

        System.out.println();

    }



}
