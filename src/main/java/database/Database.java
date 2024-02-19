package database;

import java.sql.*;

public class Database {
    // Establish connection
    private static String url = "jdbc:mysql://localhost:3306/yourdatabase";
    private static String username = "root";
    private static String password = "";

    public Connection getConnection() {
       // Connection connection = null;
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
