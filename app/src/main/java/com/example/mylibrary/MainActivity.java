package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_CODE_RATING = 1;
    final static int REQUEST_CODE_ADDBOOK = 0;
    int position;

    ListView list;
    ArrayList<Book> listBook = new ArrayList<Book>();
    BookAdapter bookAdapter;

    // Создаем переменные для работы с БД
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        databaseHelper = new DBHelper(getApplicationContext());

        setInitialData();

        bookAdapter = new BookAdapter(this, R.layout.listitem, listBook);

        list.setAdapter(bookAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                position = i;
                Intent  intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra(Book.class.getSimpleName(),listBook.get(i));
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data == null){return;}

        Book book = (Book)data.getSerializableExtra(Book.class.getSimpleName());

        listBook.get(position).setRating(book.getRating());

        bookAdapter.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setInitialData(){
        if(listBook != null) listBook.clear();

        db = databaseHelper.getReadableDatabase();

        userCursor = db.rawQuery("select * from " + DBHelper.TABLE,null );

        while(userCursor.moveToNext()){
            String name = userCursor.getString(1);
            String avtor = userCursor.getString(2);
            Float rating = userCursor.getFloat(3);
            listBook.add(new Book(name, avtor,rating));
        }

        userCursor.close();
        db.close();
    }
    void updateData(Book book, int id)
    {

        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.RATING, book.getRating());
        db.update(DBHelper.TABLE, cv, DBHelper.COLUMN_ID + "=" + String.valueOf(id+1),null);
        db.close();
    }

    void insertData(Book book){

        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAMEBOOK, book.getNameBook());
        cv.put(DBHelper.COLUMN_AVTORNAME, book.getAvtorName());
        cv.put(DBHelper.RATING, book.getRating());

        db.insert(DBHelper.TABLE, null, cv);
        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivityForResult(intent, REQUEST_CODE_ADDBOOK);
                return true;
            case R.id.up:
                Collections.sort(listBook, new Comparator<Book>() {
                    @Override
                    public int compare(Book book, Book t1) {
                        if(book.getRating() - t1.getRating() > 0) return 1;
                        else if (book.getRating() - t1.getRating() < 0) return -1;
                        else return 0;
                    }
                });
            case R.id.down:
                Collections.sort(listBook, new Comparator<Book>() {
                    @Override
                    public int compare(Book book, Book t1) {
                        if(book.getRating() - t1.getRating() > 0) return -1;
                        else if (book.getRating() - t1.getRating() < 0) return 1;
                        else return 0;
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }
}

