package com.ifmo.lesson6;

public class Item<T> {
    /**
     * Значение элемента.
     */
    T value;

    /**
     * Ссылка на следующий элемент.
     */
    Item<T> next;

    /**
     * Инициализирует элемент со значением
     * {@code value}.
     *
     * @param value Значение, которое будет сохранено
     *              в этом элементе.
     */
    Item(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

