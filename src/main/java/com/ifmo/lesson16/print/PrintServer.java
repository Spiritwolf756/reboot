package com.ifmo.lesson16.print;

import com.ifmo.lesson16.print.Commands.Ban;
import com.ifmo.lesson16.print.Commands.Ping;
import com.ifmo.lesson16.print.Commands.ServerTime;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrintServer {

    private int port;
    private List<String> users = new ArrayList<>();
    private List<String> ipbun = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                } catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                } finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             OutputStream out = sock.getOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
             InputStream in = sock.getInputStream()) {

            Object obj = objIn.readObject();

            if (obj instanceof Ping) {
                Ping ping = (Ping) obj;
                ping.setTimearrived(System.currentTimeMillis());
                objOut.writeObject(ping);
            } else if (obj instanceof ServerTime) {
                ServerTime serverTime = (ServerTime) obj;
                serverTime.setTime(new Date());
                objOut.writeObject(serverTime);
            }else if (obj instanceof Ban){
                if (((Ban) obj).isBan()){
                    ipbun.add(((Ban) obj).getIp());
                }else{
                    ipbun.remove(((Ban) obj).getIp());
                }
                objOut.writeObject(obj);
            } else if (obj instanceof Message) {
                printMessage((Message) obj, host);
            } else if (obj instanceof User) {
                if (ipbun.contains(((User) obj).getIp())) {
                objOut.writeObject(-1);
                } else if (users.contains(((User) obj).getName())) {
                    objOut.writeObject(0);
                } else {
                    users.add(((User) obj).getName());
                    objOut.writeObject(1);
                }
            }
        } catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
