package com.ifmo.lesson2;

import java.util.Scanner;

public class Dividers {
    /*
    Выведите на экран все положительные делители натурального числа, введённого
    пользователем с клавиатуры.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        // TODO implement

        for (int del=1; del<=n; del++){
            if (n%del==0){
                System.out.println(del);
            }
        }
    }
}
