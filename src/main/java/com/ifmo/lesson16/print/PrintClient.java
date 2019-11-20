package com.ifmo.lesson16.print;

import com.ifmo.lesson16.print.Commands.Ban;
import com.ifmo.lesson16.print.Commands.Ping;
import com.ifmo.lesson16.print.Commands.ServerTime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class PrintClient {

    private SocketAddress serverAddr;

    private String name = null;

    private Scanner scanner;

    User user;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) throws SocketException {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
        user = new User(getAddresses());
    }

    private void start() throws IOException, ClassNotFoundException {

        do {
            System.out.println("Enter your name: ");
            name = scanner.nextLine();
            user.setName(name);
        } while (authorization() < 1);


        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;
            else if ("/nick".equals(msg)) {
                System.out.println("Enter new name:");

                name = scanner.nextLine();

                continue;
            } else if ("/myaddr".equals(msg)) {
                printAddresses();

                continue;
            } else if ("/ping".equals(msg)) {
                ping();
            } else if ("/server_time".equals(msg)) {
                serverTime();
            } else if ("/ban ".equals(msg.substring(0, 5))) {
                ban(new Ban(msg.substring(5), true));
            } else if ("/unban ".equals(msg.substring(0, 7))) {
                ban(new Ban(msg.substring(7), true));
            } else {
                buildAndSendMessage(msg);
            }
        }
    }

    private void ban(Ban ban) throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);
            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
                objOut.writeObject(ban);

                Ban response = (Ban) objIn.readObject();
                System.out.println(response);
            }
        }

    }

    private int authorization() throws IOException, ClassNotFoundException {
        if (name == null)
            return 0;
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);
            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
                objOut.writeObject(user);

                int response = (int) objIn.readObject();

                if (response == 0) {
                    System.out.println("Пользователь с данным именем уже авторизован");
                    return 0;
                }
                if (response == 1) {
                    System.out.println("Вы успешно авторизовались");
                    return 1;
                }
                if (response == -1) {
                    System.out.println("Доступ с Вашего IP заблокирован");
                    return -1;
                }
            }

        }
        return 0;
    }

    private void serverTime() throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());

                objOut.writeObject(new ServerTime());

                ServerTime response = (ServerTime) objIn.readObject();

                System.out.println(response);
            }
        }
    }

    private void ping() throws IOException, ClassNotFoundException {
        long duration = 0;
        for (int i = 0; i < 5; i++) {
            try (Socket sock = new Socket()) {
                sock.connect(serverAddr);


                try (OutputStream out = sock.getOutputStream()) {
                    ObjectOutputStream objOut = new ObjectOutputStream(out);
                    ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());

                    objOut.writeObject(new Ping());

                    Ping response = (Ping) objIn.readObject();

                    System.out.println(response.duration());
                    duration += response.duration();
                    objOut.flush();
                }
            }
        }
        System.out.println("Your ping is " + duration / 5 + " ms");
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private String getAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                return i.getHostAddress();
            }
        }
        return null;
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);

                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        client.start();
    }
}
