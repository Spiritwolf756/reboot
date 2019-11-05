package com.ifmo.lesson8;

public class Main {
    public static void main(String[] args) {
        StringAppendable stringAppendable = new StringAppendable(", ");
        stringAppendable.append("lala").append("lolo");
        System.out.println(stringAppendable);
    }
}
