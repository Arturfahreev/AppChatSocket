import java.io.IOException;
import java.net.Socket;

public class MainClient3 {
    public static void main(String[] args) throws IOException {
        ClientThread clientThread = new ClientThread(new Socket("localhost", 8189));
        new Thread(clientThread).start();
    }
}
