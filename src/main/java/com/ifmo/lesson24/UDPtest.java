package com.ifmo.lesson24;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPtest {
    UDPclient client;
    UDPserver server;

    public static void main(String[] args) throws IOException {
        UDPtest udptest = new UDPtest();
        udptest.setup();
        udptest.sendfile();
        udptest.down();

    }
    public void setup() throws SocketException, UnknownHostException {
        server = new UDPserver();
        server.start();
        client = new UDPclient();
    }

    public void sendmsg() throws IOException {
        client.sendMsg("Test");

    }
    public void sendfile() throws IOException {
        File file = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\in.txt");
        client.sendFile(file);

    }
    public void down() throws IOException {
       // server.close();
        client.close();
    }
}