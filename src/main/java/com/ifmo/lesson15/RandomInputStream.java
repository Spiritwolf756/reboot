package com.ifmo.lesson15;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Реализация потока ввода, которая генерирует случайные данные
 * указанной длины.
 */
//заполняет случайными байтами (возвращает случайный байт или заполняет массив ими)
public class RandomInputStream extends InputStream {
    private final Random random;
    private final long length;

    public RandomInputStream(Random random, long length) {
        this.random = random;
        this.length = length;
    }

    @Override
    public int read() throws IOException {
        // TODO implement
        return 0;
    }
}
