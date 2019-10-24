package com.ifmo.lesson5;

public class Triangle extends Shape{
    private double a;
    private double b;
    private double c;
    private double p;
    public Triangle(double a, double b, double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    @Override
    public double area(){
        p=0.5 * (a+b+c);
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

}
