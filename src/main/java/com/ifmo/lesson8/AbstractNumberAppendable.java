package com.ifmo.lesson8;



public class AbstractNumberAppendable<T extends Number> implements Appendable<T>{
    protected T value;
    private Operation<T> op;


    public  AbstractNumberAppendable append(T value){
        this.value=(this.value==null) ? value : op.calculate(this.value,value);
        return this;
    }
    public T value(){
        return value;
    }
    void setOperation(Operation op){
        this.op=op;
    }

}
