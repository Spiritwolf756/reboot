package com.ifmo.lesson24;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPserver extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public UDPserver() throws SocketException {
        socket = new DatagramSocket(4445);
    }
public void close(){
        running = false;
}
    public void run() {
        running = true;
        File file = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\uot.txt");
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                out.write(buf);
                System.out.println(new String(packet.getData(), 0, packet.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String str = new String(packet.getData(), 0, packet.getLength());
            if("-1".equals(str)){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        socket.close();
    }
}

