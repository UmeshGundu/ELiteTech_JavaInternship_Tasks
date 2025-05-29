import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static Set<Socket> clients = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started on port 1234");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            new ClientHandler(clientSocket, clients).start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private Set<Socket> clients;

    public ClientHandler(Socket socket, Set<Socket> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out;
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                for (Socket s : clients) {
                    if (!s.equals(socket)) {
                        out = new PrintWriter(s.getOutputStream(), true);
                        out.println("Message: " + inputLine);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
