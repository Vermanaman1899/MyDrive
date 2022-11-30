package main;

import com.mysql.cj.MysqlConnection;

import java.net.*;
import java.sql.*;
import Services.DatabaseConnection;

public class Main {
    static int port = 3000;
//    static Connection MyConnection;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            DatabaseConnection.getConnection(); //To connect to the database
            while (true) {
                Socket socket = server.accept();
                ClientHandler handler = new ClientHandler(socket);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

