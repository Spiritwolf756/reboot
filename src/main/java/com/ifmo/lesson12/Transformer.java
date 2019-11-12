package com.ifmo.lesson12;

import java.util.List;

public interface Transformer<T,R> {
    R transform (T val);
}
