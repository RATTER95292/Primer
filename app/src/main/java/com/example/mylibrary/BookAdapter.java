package com.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class BookAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private int layout;
    private List<Book> bookList;


    public BookAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);

        this.bookList = bookList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent,false);

        TextView nameView = view.findViewById(R.id.namebook);
        TextView avtorView = view.findViewById(R.id.avtorname);

        RatingBar rating = view.findViewById(R.id.rating);

        Book book = bookList.get(position);

        nameView.setText(book.getNameBook());
        avtorView.setText(book.getAvtorName());
        rating.getRating(book.getRating());

        return view;

    }
}

