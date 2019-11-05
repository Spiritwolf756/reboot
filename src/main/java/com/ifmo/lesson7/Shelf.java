package com.ifmo.lesson7;


public class Shelf {
    Book book;
    int quantity;

    public Shelf(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
    @Override
    public int hashCode() {
        int hash = 13;
        for (int i =1; i<=book.author.length(); i++ ){
            hash=hash*3+book.author.charAt(i-1);
        }
        return hash;
    }
}
