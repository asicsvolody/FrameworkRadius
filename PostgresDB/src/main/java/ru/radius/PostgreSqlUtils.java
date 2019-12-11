/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import org.joda.time.DateTime;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlUtils {

    public static void addTableWithColumns(int idSource, String schema, String table, String lastLogFile, String lastLogFileLoc2 ) throws SQLException {

        Connection connection = PostgresConnector.getInstance().getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("BEGIN");
        String sql = "";


        stmt.execute("COMMIT");


    }


    public static String dateNow(){
        DateTime date = new DateTime();
        return date.toString("yyyy-MM-dd HH:mm:ss");
    }
}
