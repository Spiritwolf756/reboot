package com.ifmo.lesson5;

public class Shape {
    public Shape(){

    }
    public double area(){
        return 0;
    }
    private static double summ(LinkedList shapes){
        if (shapes==null){
            return 0;
        }
        double summArea=0;
        int i=0;
        while(shapes.get(i)!=null){
            summArea+=shapes.get(i).area();
            i++;
        }
        return summArea;
    }
}
