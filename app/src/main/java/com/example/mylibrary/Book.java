package com.example.mylibrary;

import java.io.Serializable;

public class Book implements Serializable {
    String nameBook;
    String avtorName;
    float rating;

    public Book(String nameBook, String avtorName, float rating) {
        this.nameBook = nameBook;
        this.avtorName = avtorName;
        this.rating = rating;
    }

    public String getNameBook() {
        return this.nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAvtorName() {
        return this.avtorName;
    }

    public void setAvtorName() {
        this.avtorName = avtorName;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}


