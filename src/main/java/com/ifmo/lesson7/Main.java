package com.ifmo.lesson7;

import com.ifmo.lesson6.LinkedList;
import com.ifmo.lesson6.List;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
    /*    List list = new LinkedList();

        for (int i=0; i<10; i++){
            list.add("MyString"+i);
        }

        Object findValue = Utils.find(list, new Predicate() {
            @Override
            public boolean test(Object o) {
                return o.toString().endsWith("5");
            }
        });
        System.out.println(findValue);
    }

     */
        Library library = new Library(3);
        Book b1 = new Book("Dmitrii", "MyFirstBook");
        Book b2 = new Book("Dmitrii", "MySecondBook");
        Book b3 = new Book("Evgenii", "MyFirstBook");

        library.put(b1, 2);
        library.put(b2, 4);
        library.put(b3, 3);
        library.put(b3, 7);

        library.take(b1, 3);
    }

}
