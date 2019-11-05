package com.ifmo.lesson6.Remove;

import com.ifmo.lesson6.ArrayList;

import java.util.Iterator;

public class ArrayListIterator implements Iterator<Object> {
    private ArrayList arrayList;
    private int count;
   // private Object object;

    public ArrayListIterator(ArrayList arrayList){
        this.arrayList=arrayList;
        //object=arrayList.get(0);
    }
    @Override
    public boolean hasNext(){
        return arrayList.get(count) != null;
    }
    @Override
    public Object next(){
        //if (hasNext()){
            //count++;
            return arrayList.get(count++);
        //}
        //return null;
    }
}
