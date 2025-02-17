package servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServlet {
    public static void main(String[] args) throws ServletException, IOException {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port " + serverSocket.getLocalPort());

            while (true) {
                Socket socketClient = serverSocket.accept();
                ReverseServlet reverseServlet = new ReverseServlet();
                ClientHandler clientHandler = new ClientHandler(socketClient, reverseServlet);
                new Thread(clientHandler).start();
            }
        }
    }

    static class ClientHandler implements Runnable {

        Socket socketClient;
        Servlet servlet;

        public ClientHandler(Socket socketClient, Servlet servlet) {
            this.socketClient = socketClient;
            this.servlet = servlet;
        }

        @Override
        public void run() {



        }
    }
}
