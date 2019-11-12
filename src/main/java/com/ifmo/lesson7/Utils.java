package com.ifmo.lesson7;

import com.ifmo.lesson6.List;

import java.util.function.Predicate;

public final class Utils {
    private Utils(){

    }
    public static Object find(Predicate predicate, List list){
        for (Object obj: list){
            if (predicate.test(obj)){
                return obj;
            }
        }

        return null;
    }
    public static List filter(Predicate predicate, List list){

        return null;
    }
}
