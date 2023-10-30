package Client;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        String serverName = "10.26.14.97"; 
        int port = 1234; 
        try {
            InetAddress serverAddress = InetAddress.getByName(serverName);
            Socket clientSocket = new Socket(serverAddress, port);

            // For writing to the server
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // For reading from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Sending a message to the server
            out.println("Hello from the client!");

            // Receiving a message from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

