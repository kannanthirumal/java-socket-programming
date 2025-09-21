package socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Step 1: Create a ServerSocket listening on port 8080
            // ServerSocket is like a "door" that waits for clients to connect.
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("✅ Server started. Waiting for a client to connect on port 8080...");

            // Step 2: Accept a client connection (this line BLOCKS until a client connects)
            // Once a client connects, a new Socket object is created for communication.
            Socket socket = serverSocket.accept();
            System.out.println("🔗 Client connected successfully from "
                    + socket.getInetAddress().getHostAddress());

            // Step 3: Get input stream from the socket (to read data sent by client)
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Step 4: Read UTF-encoded string message from client
            String message = dis.readUTF();

            // Step 5: Print the received message
            System.out.println("📩 Message from client: " + message);

            // Step 6: Close resources (good practice to prevent memory leaks)
            dis.close();
            socket.close();       // close client socket
            serverSocket.close(); // close server socket
            System.out.println("❎ Connection closed. Server shutting down.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
