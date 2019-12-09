package com.ifmo.lesson24;

import java.io.*;
import java.net.*;

public class UDPclient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public UDPclient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendMsg(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }
    public void sendFile(File file) throws IOException {
        try (InputStream src = new FileInputStream(file);
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = src.read(buf)) > 0) {
                bout.write(buf, 0, len);
                DatagramPacket packet = new DatagramPacket(bout.toByteArray(), len, address, 4445);
                socket.send(packet);
            }
        }
     //   buf = file.getBytes();
    /*    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());

     */
      //  return received;
    }

    public void close() {
        socket.close();
    }
}
