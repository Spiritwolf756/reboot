package com.ifmo.lesson8;

public interface Operation<T extends Number> {
    T calculate(T a, T b);
}
