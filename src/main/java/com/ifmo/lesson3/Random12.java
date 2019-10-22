package com.ifmo.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Random12 {
    /*
    Создайте массив из 12 случайных целых чисел из отрезка [-15;15]. Определите какой
    элемент является в этом массиве максимальным и сообщите индекс его последнего
    вхождения в массив.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        // TODO implement

        int max = max(randomNumbers);
        int maxLastIndex = lastIndexOf(randomNumbers, max);

        // TODO implement
        System.out.println(maxLastIndex);
    }

    public static int[] randomNumbers() {
        // TODO implement
        int[] arrint = new int[12];
        Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i<arrint.length; i++){
            arrint[i]=-15 + rnd.nextInt(15+15+1);
        }
        return arrint;
    }

    public static int max(int[] randomNumbers) {
        // TODO implement
        int max=0;
        for (int randomNumber : randomNumbers) {
            if (max < randomNumber) {
                max = randomNumber;
            }
        }
        return max;
    }

    public static int lastIndexOf(int[] randomNumbers, int max) {
        // TODO implement
        for (int i=randomNumbers.length-1; i>=0; i--){
            if (max==randomNumbers[i]){
                return i;
            }
        }
        return -1;
    }
}
