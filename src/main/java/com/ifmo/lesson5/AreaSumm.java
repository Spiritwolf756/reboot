package com.ifmo.lesson5;


public class AreaSumm {
    public static void main(String[] args) {
        LinkedList shapes = new LinkedList();
        shapes.add(new Circle(5));
        shapes.add(new Oval(3,7));
        shapes.add(new Rectangle(5.4,3));
        shapes.add(new Square(6.5));

        System.out.println(totalArea(shapes));
    }
    public static double totalArea(LinkedList shapes){
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
