package com.ifmo.lesson5;

public class Oval extends Shape{
    private double r1;
    private double r2;
    public Oval(double r1, double r2){
        this.r1=r1;
        this.r2=r2;
    }
    @Override
    public double area(){
        return r1*r2*Math.PI;
    }
}
