package com.ifmo.lesson18.Sensor;

public class RedAlarm implements Listener, Alarm {
    @Override
    public void alarm() {
        System.out.println("КРАСНАЯ тревога!");
    }

    @Override
    public void publish(int temperature) {
        if (temperature >= 140) {
            alarm();
        }
    }
}
