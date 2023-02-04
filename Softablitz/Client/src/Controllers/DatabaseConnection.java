package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;

public class DatabaseConnection {

    static Connection con = null;

    public static Connection getConnection() {
        if (con != null)
            return con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softablitz", "root", "naman@mysql123");
            System.out.println("Database Connected");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}