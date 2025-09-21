package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for a client to connect...");
            System.out.println("Server is ready to chat...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Setup to read from console
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String messageReceived = "";
            String messageToSend = "";

            while (true) {
                //Waiting for client message
                messageReceived = in.readUTF(); // blocks until client sends something
                if (messageReceived.equals("bye")) {
                    System.out.println("Client ended the chat.");
                    break;
                }
                System.out.println("Client says: " + messageReceived);

                //Ask server user to type a reply
                System.out.print("You (server): ");
                messageToSend = br.readLine();

                //send reply to client
                out.writeUTF(messageToSend);
                out.flush(); // force send immediately from the buffer
            }

            br.close();
            out.close();
            in.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server shut down.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
