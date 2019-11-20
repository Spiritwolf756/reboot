package com.ifmo.lesson16.print;

import javax.print.DocFlavor;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private boolean access;
    private String ip;

    User(String name, String ip) {
        this.name = name;
        this.ip=ip;
        access = true;
    }
    User(String ip) {
        this.ip=ip;
        access = true;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAccess() {
        return access;
    }

    @Override
    public String toString() {
        String status = (access) ? " разблокирован" : " заблокирован";
        return "Пользователь" + name + status;
    }
}
