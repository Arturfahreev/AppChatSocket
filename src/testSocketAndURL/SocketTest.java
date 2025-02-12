package testSocketAndURL;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        try (Socket socket = new Socket("httpforever.com", 80)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);

            String command = "GET / HTTP/1.1\n" +
                    "Host: httpforever.com\n\n";
            printWriter.print(command);
            printWriter.flush();

            while ((scanner.hasNextLine())) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
