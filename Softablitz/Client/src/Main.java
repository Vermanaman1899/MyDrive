import java.net.*;
import java.io.*;

public class Main {
    private Socket socket = null;
    private ObjectOutputStream objectOuputStream = null;
    private ObjectInputStream objectInputStream = null;

    public Main(String IPAddress, int port){

        try{
            socket = new Socket(IPAddress, port);
            System.out.println("Connected to server");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main client = new Main("127.0.0.1",3000);
    }
}