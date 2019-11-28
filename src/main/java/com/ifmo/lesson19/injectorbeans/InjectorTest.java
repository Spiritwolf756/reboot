package com.ifmo.lesson19.injectorbeans;

import com.ifmo.lesson19.injector.Injector;

public class InjectorTest {
    public static void main(String[] args) {
        Injector injector = new Injector();

        final TestedBean testedBean = injector.create("com.ifmo.lesson19.injectorbeans.TestedBean");

        System.out.println(testedBean);

        testedBean.getList().add("a");

        System.out.println(testedBean);

        final TestedBean testedBean2 = injector.create(TestedBean.class.getName());

        System.out.println(testedBean2);
    }
}
