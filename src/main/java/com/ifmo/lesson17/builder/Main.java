package com.ifmo.lesson17.builder;

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza
                .Builder("Слоеное", "Чедр")
                .catchup(5)
                .papperoni(100500)
                .build();
        System.out.println(pizza);
    }
}
