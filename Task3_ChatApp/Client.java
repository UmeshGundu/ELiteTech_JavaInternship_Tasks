import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        new ReadThread(socket).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = input.readLine()) != null) {
            out.println(line);
        }
    }
}

class ReadThread extends Thread {
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
