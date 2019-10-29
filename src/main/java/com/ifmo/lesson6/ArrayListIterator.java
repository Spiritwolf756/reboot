package com.ifmo.lesson6;

import java.util.Iterator;

public class ArrayListIterator implements Iterator<Object> {
    private ArrayList arrayList;
    private int count;
    private Object object;

    public ArrayListIterator(ArrayList arrayList){
        this.arrayList=arrayList;
        object=arrayList.get(0);
    }
    @Override
    public boolean hasNext(){
        if (arrayList.get(count+1)!=null){
            return true;
        }
        return false;
    }
    @Override
    public Object next(){
        if (hasNext()){
            count++;
            return arrayList.get(count);
        }
        return null;
    }
}
