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
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Book book = (Book) obj;
        if (author.equals(book.author) && title.equals(book.title))
            return true;
        return false;
    }
}

