package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected to server on port 8080");

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF("Kannan here, Connecting from the client side!");
            System.out.println("Message sent to server");

            dos.close();
            socket.close();
            System.out.println("Connection closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
