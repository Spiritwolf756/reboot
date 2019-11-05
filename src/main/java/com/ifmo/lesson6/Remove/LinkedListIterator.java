package com.ifmo.lesson6.Remove;

import com.ifmo.lesson6.Remove.Item;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Object> {
    private Item item;
    private int count;

    public LinkedListIterator(Item head){
        this.item=head;
    }
    @Override
    public boolean hasNext(){
        return item!= null;
    }
    @Override
    public Object next(){
        Object tmp = item.value;
        item = item.next;
        return tmp;
    }
}
