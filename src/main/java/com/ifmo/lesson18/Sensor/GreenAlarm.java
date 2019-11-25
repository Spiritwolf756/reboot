package com.ifmo.lesson18.Sensor;

public class GreenAlarm implements Listener, Alarm {
    @Override
    public void publish(int temperature){
        if (temperature>=70){
            alarm();
        }
    }
    @Override
    public void alarm(){
        System.out.println("ЗЕЛЕНАЯ тревога!");
    }
}
