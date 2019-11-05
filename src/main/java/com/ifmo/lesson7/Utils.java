package com.ifmo.lesson7;

import com.ifmo.lesson6.List;

import java.util.function.Predicate;

public final class Utils {
    private Utils(){

    }
    public static Object find(List list, Predicate predicate){
        for (Object obj: list){
            if (predicate.test(obj)){
                return obj;
            }
        }

        return null;
    }
}
