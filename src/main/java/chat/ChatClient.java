package chat;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try {
            //127.0.0.1 = localhost (same machine)
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Connected to server at 127.0.0.1:8080");

            //Setup streams for communication
            //in  -> read messages from server
            //out -> send messages to server
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //Setup to read from console
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String messageReceived = "";
            String messageToSend = "";

            // STEP 4: Chat loop
            while (true) {
                System.out.print("You (client): ");
                messageToSend = br.readLine();

                //Send the message to server
                out.writeUTF(messageToSend);
                out.flush(); // ensure it’s sent immediately

                if (messageToSend.equals("bye")) {
                    System.out.println("Ending chat...");
                    break;
                }

                //Wait for server’s reply (blocks until server sends)
                messageReceived = in.readUTF();
                System.out.println("Server says: " + messageReceived);
            }

            br.close();
            out.close();
            in.close();
            socket.close();
            System.out.println("Connection closed. Client shutting down.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
