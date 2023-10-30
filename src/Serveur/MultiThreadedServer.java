package Serveur;
import java.io.*;
import java.net.*;
public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int clientNumber = 0;

        try {
            serverSocket = new ServerSocket(1234); // Choose your desired port
            System.out.println("Server started on port 1234");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1234");
            System.exit(-1);
        }

        while (listening) {
            new ServerThread(serverSocket.accept(), clientNumber++).start();
        }

        serverSocket.close();
    }
}

class ServerThread extends Thread {
    private Socket socket = null;
    private int clientNumber;

    public ServerThread(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client# " + clientNumber + " at " + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a welcome message to the client.
            out.println("You are client #" + clientNumber);

            // Process messages from the client
            while (true) {
                String inputLine = in.readLine();
                if (inputLine == null || inputLine.equals("exit")) break; // Exit on 'exit' command
                System.out.println("Client " + clientNumber + " says: " + inputLine);
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
