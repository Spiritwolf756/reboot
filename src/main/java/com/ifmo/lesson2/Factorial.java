package com.ifmo.lesson2;

import java.util.Scanner;

public class Factorial {
    /*
     Создайте программу, вычисляющую факториал натурального числа n, которое
     пользователь введёт с клавиатуры.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long factorial = factorial(n);

        System.out.println(factorial);
    }

    public static long factorial(int n) {
        // TODO implement
        if (n==0){
            return 1;
        }else if(n>0){
            long fac=1;
            for (int i = 1; i <= n; i++){
                fac=fac*i;
            }
            return fac;
        }
        return 0;

    }
}
