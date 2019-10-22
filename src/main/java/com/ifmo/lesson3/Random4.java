package com.ifmo.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Random4 {
    /*
    Создайте массив из 4 случайных целых чисел из отрезка [10;99], выведите его на экран в
    строку. Определить и вывести на экран сообщение о том, является ли массив строго
    возрастающей последовательностью.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        // TODO implement
        System.out.println(Arrays.toString(randomNumbers));
        if (isIncreasingSequence(randomNumbers)){
            System.out.println("Указанный массив является строго возрастающей последовательстью");
        }else {
            System.out.println("Указанный массив не является строго возрастающей последовательсностью");
        }
    }

    public static int[] randomNumbers() {
        // TODO implement
        int[] arrint = new int[4];
        Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i<arrint.length; i++){
            arrint[i]=getRND(rnd);
        }
        return arrint;
    }
    private static int getRND(Random rnd){
        return 10 + rnd.nextInt(99-10+1);
    }

    public static boolean isIncreasingSequence(int[] randomNumbers) {
        // TODO implement
        int[] arrcopy = Arrays.copyOf(randomNumbers,4);
        Arrays.sort(arrcopy);
        return Arrays.equals(randomNumbers, arrcopy);
    }
}
