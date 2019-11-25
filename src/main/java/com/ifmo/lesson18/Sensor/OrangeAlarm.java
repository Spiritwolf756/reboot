package com.ifmo.lesson18.Sensor;

public class OrangeAlarm  implements Listener, Alarm {
    @Override
    public void alarm() {
        System.out.println("ОРАНЖЕВАЯ тревога!");
    }

    @Override
    public void publish(int temperature) {
        if (temperature>=110){
            alarm();
        }
    }
}
