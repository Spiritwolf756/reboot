package com.ifmo.lesson19;

public class MyClass {
    @Exclude
    public  int i;
    private String str;
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
