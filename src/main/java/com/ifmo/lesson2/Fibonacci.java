package com.ifmo.lesson2;

public class Fibonacci {
    /*
     Выведите на экран первые 11 членов последовательности Фибоначчи. Напоминаем, что
     первый и второй члены последовательности равны единицам, а каждый следующий — сумме
     двух предыдущих.
     */
    public static void main(String[] args) {
        // TODO implement
        System.out.printf("11");
        int first=1;
        int second=1;
        int actual;
        for(int i =3; i<=11; i++){
            actual=first+second;
            first=second;
            second=actual;
            System.out.printf(Integer.toString(actual));
        }
    }
}
