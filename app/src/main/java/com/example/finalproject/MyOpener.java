package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpener extends SQLiteOpenHelper {

    protected final static String DATABASE_NAME = "BBCNewsReader";
    protected final static int VERSION_NUM = 2;
    public final static String TABLE_USER = "USER";
    public final static String COL_NAME = "NAME";
    public final static String COL_EMAIL = "EMAIL";
    public final static String COL_MDP = "MDP";
    public final static String COL_AGE = "AGE";
    public final static String COL_SAVED_NEWS = "SAVED_NEWS";
    public final static String COL_ID = "_id";

    SQLiteDatabase db;

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

    public void open() {
        if (db == null || !db.isOpen()) {
            db = this.getWritableDatabase();
        }
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public long addUser(String name, String email, String mdp, int age) {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_EMAIL, email);
        values.put(COL_MDP, mdp);
        values.put(COL_AGE, age);

        return db.insert(TABLE_USER, null, values);
    }

    public void deleteUsers() {
        open();
        db.delete(TABLE_USER, null, null);
    }
    public void deleteUser(long id) {
        open();
        db.delete(TABLE_USER, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public boolean checkUser(String username, String mdp) {
        open();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COL_NAME + "=? AND " + COL_MDP + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, mdp});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

}
