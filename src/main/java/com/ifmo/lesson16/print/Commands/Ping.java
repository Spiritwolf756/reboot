package com.ifmo.lesson16.print.Commands;

import java.io.Serializable;
import java.sql.Time;

public class Ping implements Command, Serializable {
    private long timesend;
    private long timearrived;
    public Ping(){
        timesend = System.currentTimeMillis();
    }
    public long getTimesend(){
        return timesend;
    }
    public long getTimearrived(){
        return timearrived;
    }
    public void setTimearrived(long timearrived){
        this.timearrived=timearrived;
    }
    public long duration(){
        return timearrived-timesend;
    }
}
