package com.ifmo.lesson2;

import java.util.ArrayList;
import java.util.List;

public class IntsOrdering {
    /*
     В три переменные a, b и c явно записаны программистом три целых попарно неравных
     между собой числа. Создать программу, которая переставит числа в переменных таким
     образом, чтобы при выводе на экран последовательность a, b и c оказалась строго
     возрастающей.

     Числа в переменных a, b и c: 3, 9, -1
     Возрастающая последовательность: -1, 3, 9

     Числа в переменных a, b и c: 2, 4, 3
     Возрастающая последовательность: 2, 3, 4

     Числа в переменных a, b и c: 7, 0, -5
     Возрастающая последовательность: -5, 0, 7
     */
    public static void main(String[] args) {
        int a = 3, b = 9, c = -1;

        String ordering = ordering(a, b, c);

        System.out.println(ordering);
    }

    public static String ordering(int a, int b, int c) {
        // TODO implement
        int key;
        int[] sort = new int[3];
        sort[0]=a;
        sort[1]=b;
        sort[2]=c;
        for (int i=0; i<sort.length; i++){
            for (int j=i+1; j<sort.length; j++){
                if (sort[j]<sort[i]){
                    key=sort[i];
                    sort[i]=sort[j];
                    sort[j]=key;
                }
            }
        }
        return "Числа в переменных a, b и c: " + a + ", " + b + ", " + c + "\n" +
                "Возрастающая последовательность: " + sort[0] + ", " + sort[1] + ", " + sort[2];
    }
}
