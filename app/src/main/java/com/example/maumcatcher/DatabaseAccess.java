package com.example.maumcatcher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    // 데이터베이스 조건 "기쁨"
    public List<String> getHappy() {
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "기쁨" };
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getAngry() {
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "분노" };
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getDisgust() {
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "불쾌" };
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getSad() {
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "슬픔" };
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getFear() {
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "두려움" };
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
