package com.ifmo.lesson1;

public class ThreeDigitsSum {
    public static void main(String[] args) {
        int n = 123;

        int sum = sum(n);

        System.out.println(sum);
    }

    public static int sum(int n) {
        int h = n / 100;
        int m = (n - h * 100) / 10;
        int l = n % 10;

        return h + m + l;
    }
}
