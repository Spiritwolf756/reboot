package com.ifmo.lesson16.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final String RESPONSE = """
                                            HTTP/1.0 200 OK
                                            Content-type: text/html; charset=utf-8

                                            <html>
                                                <body>
                                                    <h1>Requests: %s</h1>
                                                </body>
                                            </html>
                                            """;

    public static void main(String[] args) throws IOException {
        int requests = 0;

        try (ServerSocket ssocket = new ServerSocket(8080)) {
            System.out.println("Started on 8080");
            while (true) {
                try (final Socket socket = ssocket.accept()) {
                    final OutputStream out = socket.getOutputStream();
                    final InputStream in = socket.getInputStream();

                    final String request = readFully(in);

                    System.out.println(request);

                    out.write(RESPONSE.formatted(++requests).getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }

    private static String readFully(InputStream in) throws IOException {
        byte[] buf = new byte[1024];

        StringBuilder sb = new StringBuilder();

        for (int len; (len = in.read(buf)) > 0;) {
            final String req = new String(buf, 0, len);
            sb.append(req);

            // Нужно понять, что это конец запроса, иначе поток заблокируется на чтении.
            // Закомментируйте это условие и программа повиснет.
            if (req.endsWith("\r\n\r\n"))
                break;
        }

        return sb.toString();
    }
}
