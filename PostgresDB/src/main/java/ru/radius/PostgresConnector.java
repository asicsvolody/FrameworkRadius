/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnector {

    public static void main(String[] args) throws SQLException {
        PostgresConnector.getInstance().getConnection();
        System.out.println(PostgresConnector.getInstance().isDbConnected());
    }


    private final String JDBC = "org.postgresql.Driver";

    private Connection conn;

    private String dbUrl =  "jdbc:postgresql://localhost:5432/hist?ssl='true'&sslfactory=org.postgresql.ssl.NonValidatingFactory";

    private String dbUser = "vladimir";

    private String dbPass = "bhbyf.hnftdf";

    private static PostgresConnector instance;

    public static PostgresConnector getInstance(){
        PostgresConnector localInstance = instance;
        if(localInstance == null){
            synchronized (PostgresConnector.class){
                localInstance = instance;
                if(localInstance == null){
                    localInstance = instance = new PostgresConnector();
                }
            }
        }
        return  localInstance;
    }



    public boolean isDbConnected() {
        final String CHECK_SQL_QUERY = "SELECT 1";
        boolean isConnected = false;
        try {
            conn.prepareStatement(CHECK_SQL_QUERY).execute();
            isConnected = true;
        }
        catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    private PostgresConnector() {
    }

    public Connection initConnection(String host, String port, String schema, String user, String pass) throws SQLException {
        dbUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl='true'&sslfactory=org.postgresql.ssl.NonValidatingFactory",host,port, schema);
        dbUser = user;
        dbPass = pass;
        return getConnection();
    }

    public Connection getConnection() throws SQLException {

        if (conn == null || (conn != null && !isDbConnected())) {
            try {
                Class.forName(JDBC);
            }catch (ClassNotFoundException e) {
                System.out.println("Error to connection to MySql");
                throw new SQLException("Error to connection to MySql!");
            }
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            conn.setAutoCommit(true);
            return conn;
        }
        return conn;
    }

    public void closeConnection() {
        if (conn == null) {
            return;
        }
        try {
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
