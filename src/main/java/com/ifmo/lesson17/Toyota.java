package com.ifmo.lesson17;

class Toyota implements Car {


    @Override
    public int power() {
        return 100;
    }

    @Override
    public int maxSpeed() {
        return 240;
    }
}
