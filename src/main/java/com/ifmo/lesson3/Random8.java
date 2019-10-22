package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Random;

public class Random8 {
    /*
    Создайте массив из 8 случайных целых чисел из отрезка [1;10]. Выведите массив на экран
    в строку. Замените каждый элемент с нечётным индексом на ноль. Снова выведете массив на
    экран на отдельной строке.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        // TODO implement
        System.out.println(Arrays.toString(randomNumbers));

        int[] replacedWithZeros = replaceWithZeros(randomNumbers);

        // TODO implement
        System.out.println(Arrays.toString(replacedWithZeros));
    }

    public static int[] randomNumbers() {
        // TODO implement
        int[] arrint = new int[8];
        Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i<arrint.length; i++){
            arrint[i]=1 + rnd.nextInt(10-1+1);
        }
        return arrint;
    }

    public static int[] replaceWithZeros(int[] randomNumbers) {
        // TODO implement
        for (int i=1; i<randomNumbers.length; i+=2){
            randomNumbers[i]=0;
        }
        return randomNumbers;
    }
}
