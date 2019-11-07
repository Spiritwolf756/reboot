package com.ifmo.lesson8;

import com.ifmo.lesson6.Operation;

public class Minus<T extends Number> implements Operation{
    Number a;
    Number b;
    Minus(T a, T b){
        this.a=a;
        this.b=b;
    }
    Number val
    public T calculate(T a, T b){
        T i = a - b;
        return i;
    }
}
