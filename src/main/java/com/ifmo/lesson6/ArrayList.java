package com.ifmo.lesson6;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * Этот класс должен реализовывать следующие методы: add(), get(), remove() и iterator() из интерфейса List.
 * Если при выполнении add() в массиве нет свободных элементов, то создать новый - вдвое больше,
 * скопировать в него все значения из старого и + 1, который сейчас добавляется.
 * Удаление должно сдвинуть все элементы влево, если это требуется.
 * Например, если список с такими элементами:
 * |0|1|2|3|4|5|
 * Удаляем элемент по индексу 2:
 * |0|1|_|3|4|5|
 * Перемещаем все элементы влево:
 * |0|1|3|4|5|_|
 * Теперь при итерации по ним после 1 будет идти сразу 3, как в связном списке.
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int count=0;
    private T[] values;

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива по умолчанию.
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива,
     * равного {@code initialSize}.
     *
     * @param initialSize Начальный размер внутреннего массива.
     */
    public ArrayList(int initialSize) {
        values = (T[]) new Object[initialSize];
        //values = new T[initialSize];
    }

    /** {@inheritDoc} */
    @Override
    public void add(T val) {
        // TODO implement.
        if (count==values.length-1){
            resize(values.length*2);
        }
        values[count++]=val;

    }
    private void resize(int newLenght){
        T[] newValues = (T[])new Object[newLenght];
        System.arraycopy(values, 0, newValues, 0, count);
        values=newValues;
    }
    /** {@inheritDoc} */
    @Override
    public T get(int i) {
        if (i>=values.length)
            return null;
        return values[i];
    }

    /** {@inheritDoc} */
    @Override
    public T remove(int i) {
        T ret = values[i];
       for(int j=i; j<count; j++){
            values[j]=values[j+1];
        }
        values[count]=null;
        count--;
        return ret;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }
    private class ArrayListIterator<T> implements Iterator<T> {
        private ArrayList<T> arrayList;
        private int count;
        // private Object object;

        public ArrayListIterator(ArrayList arrayList){
            this.arrayList=arrayList;
            //object=arrayList.get(0);
        }
        @Override
        public boolean hasNext(){
            return arrayList.get(count) != null;
        }
        @Override
        public T next(){
            //if (hasNext()){
            //count++;
            return arrayList.get(count++);
            //}
            //return null;
        }
    }
}
