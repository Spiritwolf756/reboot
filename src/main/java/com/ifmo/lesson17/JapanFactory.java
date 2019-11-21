package com.ifmo.lesson17;

public class JapanFactory extends Factory {

    @Override
    public Car create() {
        return new Toyota();
    }
}
