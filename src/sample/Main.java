package sample;

public class Main {

    public static void main(String[] args) {
        int port = 9090;
        Server server = new Server(port);
        server.start();
    }

}
