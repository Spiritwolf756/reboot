package com.ifmo.lesson16.print.Commands;

import java.io.Serializable;
import java.util.Date;

public class ServerTime implements Command, Serializable {
    private Date time;
    public void setTime(Date time){
        this.time=time;
    }
    @Override
    public String toString(){
        return "Теущее время сервера: " + time.toString();
    }
}
