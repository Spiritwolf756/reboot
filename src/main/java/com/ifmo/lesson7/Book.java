package com.ifmo.lesson7;

public class Book {
    String author;
    String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
    @Override
    public int hashCode() {
        int hash = 13;
        for (int i =1; i<=author.length(); i++ ){
            hash=hash*3+author.charAt(i-1);
        }
        return hash;
    }
    //надо перезаписать equals
    /*
    @Override
        public boolean equals(){

    }
    */
}

