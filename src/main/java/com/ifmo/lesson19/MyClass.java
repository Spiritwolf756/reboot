package com.ifmo.lesson19;

public class MyClass {
    private String str;
    @Exclude
    public    int i;
    public MyClass(){
        str="строка";
        i=100;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
