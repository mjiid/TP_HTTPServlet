package org.servlet.p2p_lottery.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/lotterydb?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    // Private constructor to prevent instantiation
    private dbConnect() {
    }

    // Method to obtain a database connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            // Handle connection acquisition failure
            e.printStackTrace(); // Consider logging the exception
        }
        return connection;
    }

    // Method to close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle connection closure failure
                e.printStackTrace(); // Consider logging the exception
            } finally {
                connection = null;
            }
        }
    }
}
