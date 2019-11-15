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

        System.out.println(arrayList.remove(0));
        //arrayList.remove(1);
        //arrayList.remove(1);
     //   for (Object obj : arrayList)
       //     System.out.println(obj);
        //System.out.println(arrayList.get(1));
    }
    public static void testLinkedList(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(4);

        linkedList.get(3);
    for (Object item : linkedList){
        System.out.println(item);
    }



    }
}
