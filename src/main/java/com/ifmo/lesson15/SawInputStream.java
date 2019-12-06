package com.ifmo.lesson15;

import java.io.IOException;
import java.io.InputStream;

/**
 * Реализация входящего потока, котрая генерирует данные в виде пилы
 * указанной длины и аплитуды.
 * Например:
 * Амплитуда 5, тогда вывод будет:
 * 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0...
 */
public class SawInputStream extends InputStream {
    private final int amplitude;
    private final long length;
    private long position;
    private int station;

    public SawInputStream(int amplitude, long length) {
        this.amplitude = amplitude;
        this.length = length;
    }

    @Override
    public int read() throws IOException {
        if (position>length)
            return -1;
        if (station==amplitude)
            station=0;
        position++;
        return station++;
    }
}
