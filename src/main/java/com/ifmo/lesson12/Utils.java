package com.ifmo.lesson12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Utils  {
    @FunctionalInterface
    interface Predicate<T>{
        boolean isValid(T value);
    }
 /*   interface Transform<T, R>{
        R getTransform(T value);
    }

  */

    public static <T,R> List<R> transform(List<T> list, Transformer<T,R> transformer){
        List<R> rtnList = new ArrayList<>(list.size());
        int count = 0;
        for (T t : list) {
        /*
            System.out.println("count: "+ count);
            System.out.println("Входное значение: "+ t);
            System.out.println("выходное значение: "+ transformer.transform(t));
            System.out.println("-----------");
        */
            rtnList.add(count++,transformer.transform(t));
        }
        return rtnList;
    }
    public static void main(String[] args) {
        List<Integer> integers = List.of(0, 1,2,3,4,5,6,7,8,9);
        //List<String> even = filter(integers, i -> i % 2 == 0, Objects::toString);

        //фильтрация до четных чисел

        integers=filter(integers, i -> i % 2 == 0);
       // integers.forEach(System.out::println);

        //числа в строки

        List<String>strings = transform(integers, val -> val.toString());
        for (String x : strings) {
            System.out.println(x.getClass() + ": " + x);
        }

        //числа в степень 2

        List<Integer> int2 = transform(integers, val -> (int) Math.pow(val, 2));
        int2.forEach(System.out::println);

        //проверка работы view

        System.out.println("==========");
        System.out.println("Проверка view");
        System.out.println("==========");
        List<Integer> list1 = new ArrayList<>(10);
        List<Integer> list2 = new ArrayList<>(10);
        List<Integer> list3 = new ArrayList<>(10);

        for (int i=0; i<10; i++){
            list1.add(i, i);
            list2.add(i, i+10);
            list3.add(i, i+20);
        }

        Iterable view = view(value -> value % 2 == 1, value -> value.toString(), list1, list2, list3);

        Iterator<String> iterator = view.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>(list.size());
        for (T item : list){
            if (filter.isValid(item)){
                result.add(item);
            }
        }
        return result;
    }

    public static <T,R> Iterable<R> view  (Predicate<T> filter, Transformer<T,R> transformer, Iterable<T> ... iterables){
        if (iterables.length==0){
            return List.of();
        }
        return new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {

                return new Iterator<R>() {
                    private int pos;
                    private Iterator<T> current;
                    T item=null;
                    @Override
                    public boolean hasNext() {
                        if(current==null){
                            current=iterables[pos].iterator();
                            item=current.next();
                        }
                        while (!filter.isValid(item)) {
                            item = current.next();
                            if (!current.hasNext()) {
                                pos++;

                                if (pos < iterables.length) {
                                    current = iterables[pos].iterator();
                                } else {
                                    return false;
                                }
                            }
                        }
                        return current.hasNext();
                    }

                    @Override
                    public R next() {
                        if (filter.isValid(item)) {
                            T tmp = item;
                            item = current.next();
                            return transformer.transform(tmp);
                        }
                        return null;
                    }
                };


            }
        };

    }
}
