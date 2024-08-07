package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpener extends SQLiteOpenHelper {

    protected final static String DATABASE_NAME = "BBCNewsReader";
    protected final static int VERSION_NUM = 1;
    public final static String TABLE_USER = "USER";
    public final static String COL_NAME = "NAME";
    public final static String COL_EMAIL = "EMAIL";
    public final static String COL_MDP = "MDP";
    public final static String COL_AGE = "AGE";
    public final static String COL_SAVED_NEWS = "SAVED_NEWS";
    public final static String COL_ID = "_id";

    public MyOpener(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_USER + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " text," +
                        COL_EMAIL + " text," +
                        COL_MDP + " text," +
                        COL_AGE + " integer," +
                        COL_SAVED_NEWS + " text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
