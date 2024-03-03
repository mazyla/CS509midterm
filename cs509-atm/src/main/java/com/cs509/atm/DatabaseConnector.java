package com.cs509.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/atm_db";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Team1centaurbRak#me";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
