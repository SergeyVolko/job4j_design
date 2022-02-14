package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    private static String msgAnswer(String str) {
        String[] res = str.split(" ")[1].split("=");
        return res.length < 2 ? "" : res[1];
    }
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.\r\n".getBytes());
                    String str = in.readLine();
                    String answer = msgAnswer(str);
                    boolean isClose = "Exit".equals(msgAnswer(str));
                    if (!isClose) {
                        out.write(("Hello".equals(answer) ? answer : "What").getBytes());
                    }
                    for ( ; str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                    if (isClose) {
                        server.close();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IOException in log example", e);
        }
    }
}
