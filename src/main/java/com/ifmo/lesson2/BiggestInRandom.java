package com.ifmo.lesson2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiggestInRandom {
    /*
     Создать программу, выводящую на экран случайно сгенерированное трёхзначное
     натуральное число и его наибольшую цифру.Примеры работы программы:
     В числе 208 наибольшая цифра 8.
     В числе 774 наибольшая цифра 7.
     В числе 613 наибольшая цифра 6.
     */
    public static void main(String[] args) {
        int rnd = threeDigitRandom();

        String largestDigit = largestDigit(rnd);

        System.out.println(largestDigit);
    }

    public static int threeDigitRandom() {
        // TODO implement
        Random rnd = new Random((System.currentTimeMillis()));
        return 100 + rnd.nextInt(999-100+1);
    }

    public static String largestDigit(int rnd) {
        // TODO implement

        int max = 0;

        List<Integer> allDigitals = new ArrayList<>();

        for (char ch : String.valueOf(rnd).toCharArray()){
            allDigitals.add(Character.getNumericValue(ch));
        }

        for (int each : allDigitals){
            if (max < each){
                max=each;
            }
        }
        return "В числе " + rnd + " наибольшая цифра " + max + ".";
    }
}
