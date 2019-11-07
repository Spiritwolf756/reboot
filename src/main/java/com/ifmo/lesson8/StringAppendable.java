package com.ifmo.lesson8;

public class StringAppendable extends AbstractStringAppendable implements Appendable<String> {
    public StringAppendable(String separator){
        super.setSeparator(separator);
    }
    @Override
    public StringAppendable append(String string) {
        super.append(string);
        return this;
    }
    @Override
    public String toString(){
        return value;
    }
}
