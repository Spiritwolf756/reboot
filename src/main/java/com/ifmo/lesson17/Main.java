package com.ifmo.lesson17;

public class Main {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory("Japan");
        Car car = factory.create();


    }
}
