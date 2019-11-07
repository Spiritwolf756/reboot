package com.ifmo.lesson8;

public class AbstractStringAppendable implements Appendable<String>{
    protected String value;
    private String separator;
    public AbstractStringAppendable append(String string){
        value=(value==null)?string:value+separator+string;
        return this;
    }
    void setSeparator(String str){
        this.separator=str;
    }
}
