package com.ifmo.lesson6;

import java.util.Iterator;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList<T> implements List<T>, Stack<T>, Queue<T> {
    /** Ссылка на первый элемент списка. */
    private Item<T> head;

    /** {@inheritDoc} */
    @Override
    public void add(T val) {
        if (head==null){
            head= new Item<T>(val);
            return;
        }
        Item<T> obj = head;
        while (obj.next!=null){
            obj=obj.next;
        }
        obj.next=new Item<T>((val));
    }

    /** {@inheritDoc} */
    @Override
    public T take() {
        return pop();
    }

    /** {@inheritDoc} */
    @Override
    public T get(int i) {
        Item<T> obj = head;
        int count=0;
        while(obj!=null){
            if (count==i){
                return obj.value;
            }
            obj=obj.next;
            count++;
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public T remove(int i) {
        Item<T> obj = head;
        if (obj==null){
            return null;
        }
        if (i==0){
            T value = obj.value;
            obj=obj.next;
            return value;
        }
        int count=0;
        while (obj.next!=null){
            if (count==i-1){
                T nextValue = obj.next.value;
                obj.next=obj.next.next;
                return nextValue;
            }
            obj=obj.next;
            count++;
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {
        return new LinkedListIterator(head);
    }

    /** {@inheritDoc} */
    @Override
    public void push(T value) {
        Item<T> tmp = head;
        head = new Item<T>(value);
        head.next=tmp;
    }

    /** {@inheritDoc}
     * @return*/
    @Override
    public T pop() {
        if (head==null)
            return null;
        Item<T> tmp = head;
        head = head.next;
        return tmp.value;   //???
    }
    private class LinkedListIterator implements Iterator<Object> {
        private Item<T> item;
        //private int count;

        public LinkedListIterator(Item<T> head){
            this.item=head;
        }
        @Override
        public boolean hasNext(){
            return item!= null;
        }
        @Override
        public T next(){
            T tmp = item.value;
            item = item.next;
            return tmp;
        }
    }
 /*   private class Item<T> {
        /** Значение элемента.
        T value;

        /** Ссылка на следующий элемент. 
        Item<T> next;


         * Инициализирует элемент со значением
         * {@code value}.
         *
         * @param value Значение, которое будет сохранено
         *              в этом элементе.

        Item(T value) {
            this.value = value;
        }
        @Override
        public String toString(){
            return value.toString();
        }
    }
    */
}
