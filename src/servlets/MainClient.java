package servlets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

//            BufferedReader scanner = new BufferedReader(new InputStreamReader(inputStream));
            Scanner sc = new Scanner(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream);

//            new Thread(() -> {
//                while (true) {
//                    if ((sc.hasNextLine())) {
//                        System.out.println(sc.nextLine());
//                    }
//                }
//            } ).start();

            String command = "POST / HTTP/1.1\n" +
                    "Host: localhost\n\n" +
                    "BODY\n";
            printWriter.print(command);
            printWriter.flush();

//            String str;
//            while (scanner.ready()) {
//                System.out.println(scanner.readLine());
//            }

            while ((sc.hasNextLine())) {
                System.out.println(sc.nextLine());
                System.out.println("in while scanner");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
