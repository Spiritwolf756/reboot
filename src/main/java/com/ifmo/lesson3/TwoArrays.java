package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Random;

public class TwoArrays {
    /*
     Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый, выведите
     массивы на экран в двух отдельных строках. Посчитайте среднее арифметическое элементов
     каждого массива и сообщите, для какого из массивов это значение оказалось больше (либо
     сообщите, что их средние арифметические равны).
     */
    public static void main(String[] args) {
        Random rnd = new Random((System.currentTimeMillis()));

        int[] randomNumbers1 = randomNumbers(rnd);
        int[] randomNumbers2 = randomNumbers(rnd);

        System.out.println(Arrays.toString(randomNumbers1));
        System.out.println(Arrays.toString(randomNumbers2));

        int average1 = average(randomNumbers1);
        int average2 = average(randomNumbers2);

        if(average1>average2){
            System.out.println("Среднее арифмитическое массива randomNumbers1 больше");
        }else if (average1<average2) {
            System.out.println("Среднее арифмитическое массива randomNumbers2 больше");
        }else {
            System.out.println("Средние арифмитические равны");
        }
    }

    public static int[] randomNumbers(Random rnd) {
        int[] arrint = new int[5];
        for (int i = 0; i<arrint.length; i++){
            arrint[i]=rnd.nextInt(5+1);
        }
        return arrint;
    }

    public static int average(int[] randomNumbers) {
        int count=0;
        for (int i : randomNumbers){
            count+=i;
        }
        return count / randomNumbers.length;
    }
}
