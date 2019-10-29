package com.ifmo.lesson6;

public class Main {
    public static void main(String[] args) {
        //Accumulator accumulator = new Accumulator(10, new Plus());
        //System.out.println(accumulator.accumulate(15));
        //System.out.println(accumulator.accumulate(50));
        testArrayList();
    }
    private static void testArrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        for (Object obj : arrayList)
            System.out.println(obj);
        System.out.println(arrayList.get(1));
    }
}
