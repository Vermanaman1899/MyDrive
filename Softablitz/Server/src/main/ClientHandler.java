package main;

import Request.AppRequest;
import Request.LoginRequest;
import Request.SignupRequest;
import Services.DatabaseService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import static Request.RequestType.LOGIN_REQUEST;
import static Request.RequestType.SIGNUP_REQUEST;

public class ClientHandler implements Runnable{
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public ClientHandler(Socket socket) throws IOException {
        this.socket=socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        System.out.println("Client connected! Waiting for requests...");
        while (true) {
            try {
                AppRequest request = (AppRequest) objectInputStream.readObject();
                System.out.println("Received request");
                switch (request.getRequestType()) {
                    case LOGIN_REQUEST -> {
                        System.out.println("Client wants to login!");
                        LoginRequest loginRequest = (LoginRequest) request;
                        System.out.println("Email: " + loginRequest.getEmail());
                        objectOutputStream.writeObject(DatabaseService.loginUser(loginRequest));
                        objectOutputStream.flush();
                    }
                    case SIGNUP_REQUEST -> {
                        System.out.println("Client wants to signup!");
                        SignupRequest signupRequest = (SignupRequest) request;
                        System.out.println("Email: " + signupRequest.getEmail());
                        objectOutputStream.writeObject(DatabaseService.createUser(signupRequest));
                        objectOutputStream.flush();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Client disconnected!");
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (Exception ignored) {

        }
    }
}
