package com.ifmo.lesson8;

public class Main {
    public static void main(String[] args) {
        StringAppendable stringAppendable = new StringAppendable(", ");
        stringAppendable.append("lala").append("lolo").append("lili");
        System.out.println(stringAppendable);

        StringAppendable str = new StringAppendable(", ");
        str.append("lolo").append("lele").append("lulu");
        System.out.println(str);

       // DoubleAppendable dblapp = new DoubleAppendable(new Minus());
       // dblapp.append(10.5).append(5.2);
       // System.out.println(dblapp);
    }

}
