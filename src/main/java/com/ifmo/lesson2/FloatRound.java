package com.ifmo.lesson2;

public class FloatRound {
    /*
    В переменной n хранится вещественное число с ненулевой дробной частью.
    Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
     */
    public static void main(String[] args) {
        float n = -0.6f;

        float rounded = round(n);

        System.out.println(rounded);
    }

    public static float round(float n) {
        // TODO implement
        int i = (int) n;
        float j = (Math.abs(n)-Math.abs(i))*10;

        if (n>=0){
            if (j>=5){
                i++;
            }
        }else {
            if (j>=5){
                i--;
            }
        }
        return i;
    }
}
/*
-1.55
-1.55+1=0.55
-1+1=0
 */