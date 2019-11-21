package com.ifmo.lesson17;

public abstract class Factory {
    public static Factory getFactory(String str){

        if ("Japan".equals(str)){
            return new JapanFactory();
        }
        return null;
    }

    public abstract Car create();
}
