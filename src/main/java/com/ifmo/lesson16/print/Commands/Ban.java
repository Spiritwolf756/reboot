package com.ifmo.lesson16.print.Commands;

import java.io.Serializable;

public class Ban implements Command, Serializable {
    private boolean ban;
    private String ip;

    public Ban(String ip, boolean ban) {
        this.ip = ip;
        this.ban = ban;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
    @Override
    public String toString(){
        String status = (ban) ? " разблокирован" : " заблокирован";
        return "IP " + ip + status;
    }
}
