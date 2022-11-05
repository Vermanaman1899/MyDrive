import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public ClientHandler(Socket socket) throws IOException {
        this.socket=socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void run(){
        System.out.println("Client connected! Waiting for requests...");
        while (true){
            try {

            }

            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
