package main;

import java.net.*;
import java.sql.*;

public class Main {
    static int port=3000;
    static Connection MyConnection;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            getConnection(); //To connect to the database
            while (true) {
                Socket socket = server.accept();
                ClientHandler handler=new ClientHandler(socket);
                new Thread(handler).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (MyConnection != null)
            return MyConnection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            MyConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/softablitz", "root", "naman@mysql123");
            System.out.println("Database Connected");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return MyConnection;
    }
}