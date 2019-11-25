package com.ifmo.lesson18.Sensor;


import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private int temperature;
    public Sensor(){
        temperature=0;
    }
    public Sensor(int temperature){
        this.temperature=temperature;
    }
    private List<Listener> listeners = new ArrayList<>();

    public void subscribe(Listener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(int temperature) {
        for (Listener listener : listeners) {
            listener.publish(temperature);
        }
    }
    public void setTemperature(int temperature){
        if (this.temperature!=temperature){
            this.temperature=temperature;
            notifyListeners(temperature);
        }
    }
}
