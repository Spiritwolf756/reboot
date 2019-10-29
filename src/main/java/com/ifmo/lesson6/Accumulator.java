package com.ifmo.lesson6;

public class Accumulator {
    private double value;
    private Operation op;
    public Accumulator(double a,Operation op){
        value=a;
        this.op=op;
    }
    public double accumulate(double a){
        value=op.calculate(value, a);
        return value;
    }
}
