import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MainServer {
    public static List<String> messages = new ArrayList<>(100);
    public static List<Socket> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8189);

        while (true) {
            System.out.println("Server is waiting new connection...");
            Socket socket = server.accept();
            ClientSocketThread clientSocketThread = new ClientSocketThread(socket);
            clients.add(socket);
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
        String message = "";
        try (Scanner scanner = new Scanner(clientSocket.getInputStream());
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {
            int i = 0;
            while (true) {
                message = scanner.nextLine() + " [FROM " + clientSocket.getPort() + "]";
                MainServer.messages.add(message);
                System.out.println(message);
                for (Socket s : MainServer.clients) {
                    if (s == clientSocket) continue;
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
                    out.println(message);
                }

                //printWriter.println("Iteration from Server " + i);
                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
