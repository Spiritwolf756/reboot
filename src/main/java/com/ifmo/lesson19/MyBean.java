package com.ifmo.lesson19;

@Version(value = "0.1", minor = 1)
public class MyBean {
    private final int ver = 42;
    private String description;


    public MyBean() {
    }

    @Version("1.2")
    public int getVer() {
        return ver;
    }

    public String getDescription() {
        return description;
    }

    @Version("0")
    public void setDescription(@Version("1.1") String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "ver=" + ver +
                ", description='" + description + '\'' +
                '}';
    }
}
