package com.ifmo.lesson19.injectorbeans;

import com.ifmo.lesson19.injector.Inject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestedBean {
    @Inject
    private ArrayList<String> list;

    @Inject(singleton = false)
    private ArrayList<String> list2;

    @Inject(type = LinkedList.class)
    private List<String> linkedList;

    @Inject
    private Dependency dependency;

    @Inject(singleton = false)
    private Dependency dependency2;

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "TestedBean{" +
                "list=" + list +
                ", list2=" + list2 +
                ", linkedList=" + linkedList.getClass() +
                ", dependency=" + dependency +
                ", dependency2=" + dependency2 +
                '}';
    }
}
