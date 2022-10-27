package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Book> listBook = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        setInitialData();

        BookAdapter bookAdapter = new BookAdapter(this, R.id.listitem, listBook);

        list.setAdapter(bookAdapter);

    }

    private void setInitialData(){
        listBook.add(new Book("Алиса в стране чудес","Льюис Кэрролл",4));
        listBook.add(new Book("Двадцать тысяч лье под водой","Жюль Верн",0));
        listBook.add(new Book("Дракула","Брэм Стокер",0));
        listBook.add(new Book("Война миров","Герберт Уэллс",0));
        listBook.add(new Book("Дверь в лето","Роберт Хайнлайн",5));
        listBook.add(new Book("Звездный десант","Роберт Хайнлайн",0));
        listBook.add(new Book("Нейромант","Уильям Гибсон",0));
        listBook.add(new Book("2001: Космическая Одиссея","Артур Кларк",0));
        listBook.add(new Book("Парк Юрского периода","Майкл Крайтон",0));
        listBook.add(new Book("Машина времени","Герберт Уэллс",0));
        listBook.add(new Book("1984","Джордж Оруэлл ",0));
        listBook.add(new Book("451 градус по Фаренгейту","Рэй Брэдбери",0));
        listBook.add(new Book("Солярис","Станислав Лем",0));
        listBook.add(new Book("Марсианские хроники","Рэй Брэдбери",0));
        listBook.add(new Book("Ожерелье планет Эйкумены","Урсула Ле Гуин",0));
        listBook.add(new Book("Дюна","Фрэнк Герберт ",0));
    }

}