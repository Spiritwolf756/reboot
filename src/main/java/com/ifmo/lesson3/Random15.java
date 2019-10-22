package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Random;

public class Random15 {
    /*
     Создайте массив из 15 случайных целых чисел из отрезка [0;9]. Выведите массив на экран.
     Подсчитайте сколько в массиве чётных элементов и выведете это количество на экран на
     отдельной строке.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        // TODO implement
        System.out.println(Arrays.toString(randomNumbers));
        int evens = evens(randomNumbers);

        // TODO implement
        System.out.println(evens);
    }

    public static int[] randomNumbers() {
        // TODO implement
        int[] arrint = new int[15];
        Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i<arrint.length; i++){
            arrint[i]=rnd.nextInt(9+1);
        }
        return arrint;
    }

    private static int evens(int[] arr) {
        // TODO implement
        int count=0;
        for (int i : arr){
            if (i>0 && i%2==0){
                count++;
            }
        }
        return count;
    }
}
