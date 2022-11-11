package com.example.mylibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "bookstore.db";//названия базы данных
    private static final int DB_VERSION = 1;//версия базы данных
    static final String TABLE = "books";// назавание таблицы бд
    //названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMEBOOK =  "nameBook";
    public static final String COLUMN_AVTORNAME = "avtorname";
    public static final String RATING = "rating";

    //конструктор
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books ("+ COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMEBOOK +" TEXT, "+ COLUMN_AVTORNAME + " TEXT, " + RATING + " REAL);");

        initialData(db);
    }

    void initialData(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+ TABLE + " (" + COLUMN_NAMEBOOK
                +", " + COLUMN_AVTORNAME + ", " + RATING + ") VALUES ('Алиса в стране в чудес', 'Льюис Кэрролл',4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
