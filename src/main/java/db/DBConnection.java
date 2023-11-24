package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
     
    private static DBConnection dbConnection;
    private Connection connection;
    private DBConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","yuniT@166");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException {
        return dbConnection != null ? dbConnection : (dbConnection = new DBConnection());
    }

    public Connection getConnection(){
        return connection;
    }
}
