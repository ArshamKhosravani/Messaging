package sample;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread {
    private final Socket socketClient;
    private final Server server;
    private OutputStream outputStream;

    public ServerWorker(Server server, Socket socketClient) {
        this.server = server;
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
        super.run();
        try {
            InputStream inputStream = socketClient.getInputStream();
            OutputStream outputStream = socketClient.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = in.readLine()) != null) {
                String[] commands = StringUtils.split(line);
                if ((commands != null) && commands.length > 0) {
                    String cmd = commands[0];
                    if ("msg".equalsIgnoreCase(cmd)) {
                        handleMsg(line);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleMsg(String line) throws IOException {
        String[] msgLine = StringUtils.split(line, null, 3);
        System.out.println(msgLine[2]); // test

        String sender = msgLine[1];
        String body = msgLine[2];

    }

    private void send(String onlineMsg) throws IOException {
        outputStream.write(onlineMsg.getBytes());
    }

}


