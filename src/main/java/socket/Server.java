package socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for a client to connect on port 8080...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected successfully from "
                    + socket.getInetAddress().getHostAddress());

            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String message = dis.readUTF();

            System.out.println("Message from client: " + message);

            dis.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connection closed. Server shutting down.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
