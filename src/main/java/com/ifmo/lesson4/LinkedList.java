package com.ifmo.lesson4;

import java.util.List;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList {
    /** Ссылка на первый элемент списка. */
    private Item head;

    /**
     * Добавляет значение в конец списка.
     *
     * @param val Значение, которое будет добавлено.
     */
    public void add(Object val) {
        // TODO implement
        if (head==null){
            head=new Item(val);
            return;
        }
        Item obj = head;
        while (obj.next!=null){
            obj=obj.next;
        }
        obj.next=new Item((val));
    }

    /**
     * Извлекает значение из списка по индексу.
     *
     * @param i Индекс значения в списке.
     * @return Значение, которое находится по индексу
     * или {@code null}, если не найдено.
     */
    public Object get(int i) {
        // TODO implement
        Item obj = head;
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

    /**
     * Удаляет значение по индексу и возвращает
     * удаленный элемент.
     *
     * @param i Индекс, по которому будет удален элемент.
     * @return Удаленное значение или {@code null}, если не найдено.
     */
    public Object remove(int i) {
        // TODO implement
        Item obj = head;
        if (obj==null){
            return null;
        }
        if (i==0){
            Object value = obj.value;
            obj=obj.next;
            return value;
        }
        int count=0;
        while (obj.next!=null){
            if (count==i-1){
                Object nextValue = obj.next.value;
                obj.next=obj.next.next;
                return nextValue;
            }
            obj=obj.next;
            count++;
        }
        return null;
    }
    public Item getItem(int i) {
        // TODO implement
        Item obj = head;
        int count=0;
        while(obj!=null){
            if (count==i){
                return obj;
            }
            obj=obj.next;
            count++;
        }
        return null;
    }
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println(list.remove(4));

        //System.out.println(list.get(1));
    }
}
