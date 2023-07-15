package com.example.arunawijesinghe.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.arunawijesinghe.model.User;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //Insert user data
    public void insert(User user) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.ID, user.getId());
        contentValue.put(DatabaseHelper.EMAIL, user.getEmail());
        contentValue.put(DatabaseHelper.NAME, user.getName());
        contentValue.put(DatabaseHelper.DOB, user.getDob());
        contentValue.put(DatabaseHelper.GENDER, user.getGender());
        contentValue.put(DatabaseHelper.COMPANY, user.getCompany());
        contentValue.put(DatabaseHelper.POSITION, user.getPosition());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
//       database.replace(DatabaseHelper.TABLE_NAME,null,contentValue);
        Log.e("task", "user details saved");
    }

    //fetch from db
    public Cursor fetch() {
        String[] columns = new String[]{
                DatabaseHelper.ID,
                DatabaseHelper.EMAIL,
                DatabaseHelper.NAME,
                DatabaseHelper.DOB,
                DatabaseHelper.GENDER,
                DatabaseHelper.COMPANY,
                DatabaseHelper.POSITION,
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
