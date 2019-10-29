package com.ifmo.lesson6;

public class Main {
    public static void main(String[] args) {
        Accumulator accumulator = new Accumulator(10, new Plus());
        System.out.println(accumulator.accumulate(15));
        System.out.println(accumulator.accumulate(50));
    }
}
