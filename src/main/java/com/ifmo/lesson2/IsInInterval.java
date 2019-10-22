package com.ifmo.lesson2;

import com.sun.source.tree.IfTree;

import java.util.Random;

public class IsInInterval {
    /*
     Создать программу, которая будет проверять попало ли случайно выбранное из отрезка
    [5;155] целое число в интервал (25;100) и сообщать результат на экран.Примеры работы
    программы:
    Число 113 не содержится в интервале (25,100) Число 72 содержится в интервале (25,100) 
    Число 25 не содержится в интервале (25,100) Число 155 не содержится в интервале (25,100) 
     */
    public static void main(String[] args) {
        int rnd = randomInt();

        String inInterval = isInInterval(rnd);

        // TODO implement
        System.out.println(inInterval);
    }

    public static int randomInt() {
        // TODO implement
        Random rnd = new Random((System.currentTimeMillis()));
        return 5 + rnd.nextInt(155-5+1);
    }

    public static String isInInterval(int rnd) {
        // TODO implement
        if (rnd > 25 && rnd < 100) {
            return ("Число " + rnd + " содержится в интервале (25,100)");
        } else {
            return ("Число " + rnd + " не содержится в интервале (25,100)");
        }
    }

}
