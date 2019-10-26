package com.ifmo.lesson5;

public class FixLoop {
    public static void main(String[] args) {
        com.ifmo.lesson4.LinkedList test=new com.ifmo.lesson4.LinkedList();
        int a =1;
        int b=2;
        int c=3;

        test.add(a);

        test.add(b);
        test.add(c);
        test.getItem(2).next=test.getItem(1);


        System.out.println(fixLoop(test.getItem(0)));
    }
    public static boolean fixLoop(com.ifmo.lesson4.Item item){
        if (item==null){
            return false;
        }
        if (item.next==null){
            return false;
        }
        int i=0;
        for (com.ifmo.lesson4.Item nextItem = item; nextItem!=null; nextItem=nextItem.next, i++){
            com.ifmo.lesson4.Item obj=item;
            for (int j=0; j<i; j++){
                if (nextItem.next==obj){
                    item.next=null;
                    return true;
                }else {
                    obj=obj.next;
                }
            }
        }
        return false;
    }
}
