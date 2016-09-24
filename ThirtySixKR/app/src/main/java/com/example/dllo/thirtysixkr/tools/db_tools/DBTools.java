package com.example.dllo.thirtysixkr.tools.db_tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBTools {

    private final SQLiteDatabase database;
    private Cursor cursor;

    public DBTools(Context context) {
        MyHelper helper = new MyHelper(context, DBValues.SQL_NAME, null, 1);
        database = helper.getWritableDatabase();
    }

    public void insertDB(String content) {
        ArrayList<String> contents = queryALLDB();
        if (contents.contains(content)) {

        } else {
            ContentValues values = new ContentValues();
            values.put(DBValues.TABLE_HISTORY_CONTENT, content);
            database.insert(DBValues.TABLE_NAME, null, values);
        }

    }

    public void deleteAllDB() {
        database.delete(DBValues.TABLE_NAME, null, null);
    }

    public ArrayList queryALLDB() {
        ArrayList<String> contents = new ArrayList<>();
        cursor = database.query(DBValues.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String content = cursor.getString(cursor.getColumnIndex(DBValues.TABLE_HISTORY_CONTENT));
                contents.add(content);
            }
        }
        return contents;
    }

}
