package com.ifmo.lesson8;

import com.ifmo.lesson6.Operation;

public class AbstractNumberAppendable<T> implements Appendable<T>{
    protected Number value;
    private Operation op;

    void setOperation(Operation op){
        this.op=op;
    }
    public  AbstractNumberAppendable append(T value){
        this.value=(this.value==null) ? value : op.calculate(this.value,value);
        return this;
    }


}