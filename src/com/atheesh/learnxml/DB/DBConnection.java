package com.atheesh.learnxml.DB;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    private static java.sql.Connection connection;

    private DBConnection() {

        MyProperties myProperties = new MyProperties();

        String dbURL = myProperties.dbName;
        String user = myProperties.userName;
        String password = myProperties.password;


        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException ex) {
            System.out.println("CreateConnection class : error in 30 : " + ex);
        }
    }

    public static java.sql.Connection getConnection() {
        if (connection == null) {
            dbConnection = new DBConnection();
            System.out.println("new connection created.");
        } else {
            System.out.println("connection is not null");
        }
        return connection;

    }


}
