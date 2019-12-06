package com.ifmo.lesson20.http2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private static final String RESPONSE = """
                                            HTTP/1.1 200 OK\r
                                            Content-type: text/html; charset=utf-8\r
                                            Connection: keep-alive\r
                                            Content-Length: %s\r
                                            \r
                                            \r""";

    private static final String BODY = """
                                        <html>
                                                <body>
                                                    <h1>Thread name: %s</h1>
                                                </body>
                                            </html>
                                        """;

    public static void main(String[] args) throws IOException {
        int requests = 0;

        try (ServerSocket ssocket = new ServerSocket(8080)) {
            System.out.println("Started on 8080");
            while (true) {
                Socket socket = ssocket.accept();

                System.out.println("New connection!");

                new Thread(new Reader(socket)).start();
            }
        }
    }

    private static class Reader implements Runnable {
        private final Socket socket;

        private Reader(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (socket) {
                final InputStream in = socket.getInputStream();
                final OutputStream out = socket.getOutputStream();

                while (!Thread.currentThread().isInterrupted()) {
                    final String request = readFully(in);

                    System.out.println(request);

                    final String respBody = BODY.formatted(Thread.currentThread().getName());
                    final byte[] bodyBytes = respBody.getBytes(StandardCharsets.UTF_8);

                    final String response = RESPONSE.formatted(bodyBytes.length) + respBody;

                    out.write(response.getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
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
