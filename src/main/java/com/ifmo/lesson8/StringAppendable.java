package com.ifmo.lesson8;

public class StringAppendable implements Appendable<StringAppendable> {
    private String value;
    private String separator;

    public StringAppendable(String separator){
        this.separator=separator;
    }
    public StringAppendable(String separator, String value){
        this.separator=separator;
        this.value=value;
    }
    @Override
    public StringAppendable append(String string) {
        value=(value==null)?string:value+separator+string;
        return this;
    }
    @Override
    public String toString(){
        return value;
    }

}
