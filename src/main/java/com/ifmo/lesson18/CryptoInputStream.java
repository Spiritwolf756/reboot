package com.ifmo.lesson18;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoInputStream extends FilterInputStream {
    /**
     * Создаёт новый {@link CryptoInputStream}.
     * При чтении применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из входящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param in Поток ввода.
     * @param key Ключ шифрования.
     */
    int count = 0;
    byte[] key;

    public CryptoInputStream(InputStream in, byte[] key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b != -1) {
            if (count == key.length)
                count = 0;
            b = (b ^ key[count]);
            count++;
        }
        count = 0;
        return b;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int numBytes = in.read(b, off, len);
        if (numBytes <= 0)
            return numBytes;
        for (int i = 0; i < numBytes; i++) {
            b[off + i] = (byte) ((b[off + i] ^ key[count])&0xFF);
            count++;
            if (count==key.length)
                count=0;
        }
        return numBytes;

    }
}
