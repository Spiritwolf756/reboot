package com.ifmo.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Fibonacci20 {
    /*
    Создайте массив из 20-ти первых чисел Фибоначчи и выведите его на экран. Напоминаем,
    что первый и второй члены последовательности равны единицам, а каждый следующий —
    сумме двух предыдущих.
     */
    public static void main(String[] args) {
        int[] fibonacciNumbers = fibonacciNumbers();

        // TODO implement
        for (int i : fibonacciNumbers) {
            System.out.print(i);
        }
    }

    public static int[] fibonacciNumbers() {
        // TODO implement
        int[] fibonachi=new int[20];
        fibonachi[0]=1;
        fibonachi[1]=1;
        for(int i =2; i<=19; i++){
            fibonachi[i]=fibonachi[i-2]+fibonachi[i-1];
        }
        return fibonachi;
    }

}
