package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                ServerWorker worker = new ServerWorker(this, socket);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
