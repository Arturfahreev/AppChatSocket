import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        ClientThread clientThread = new ClientThread(new Socket("localhost", 8189));
        new Thread(clientThread).start();

    }
}

class ClientThread implements Runnable {
    Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner console = new Scanner(System.in);
            Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
            new Thread(() -> {
                while (true) {
                    System.out.println(scanner.nextLine());
                }
            } ).start();

            while (true) {
                //System.out.println("< Write message... >");
                printWriter.println(console.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}