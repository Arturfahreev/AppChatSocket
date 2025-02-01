import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8189);

        while (true) {
            System.out.println("Server is waiting new connection...");
            Socket socket = server.accept();
            ClientSocketThread clientSocketThread = new ClientSocketThread(socket);
            Thread thread = new Thread(clientSocketThread);
            thread.start();
        }
    }
}

class ClientSocketThread implements Runnable {
    Socket clientSocket;

    public ClientSocketThread (Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        System.out.println("ClientSocket " + clientSocket.toString() + " is received");
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(clientSocket.getInputStream());
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {
            int i = 0;
            while (true) {
                System.out.println(scanner.nextLine());
                printWriter.println("Iteration from Server " + i);
                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
