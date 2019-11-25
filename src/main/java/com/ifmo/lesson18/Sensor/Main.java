package com.ifmo.lesson18.Sensor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.subscribe(new GreenAlarm());
        sensor.subscribe(new RedAlarm());
        sensor.subscribe(new OrangeAlarm());
        while (true) {
            test(sensor);
        }


    }

    static public void test(Sensor sensor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите температуру");

        sensor.setTemperature(scanner.nextInt());


    }
}
