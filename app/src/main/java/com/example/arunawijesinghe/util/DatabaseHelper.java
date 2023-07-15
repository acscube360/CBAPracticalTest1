package com.example.arunawijesinghe.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "USER_DETAILS";

    // Table columns
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String COMPANY = "company";
    public static final String POSITION = "position";

    // Db name
    static final String DB_NAME = "TESTAPP.DB";

    // database version
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " ( " +
            ID + " TEXT," +
            EMAIL + " TEXT," +
            NAME + " TEXT," +
            DOB + " TEXT," +
            GENDER + " TEXT," +
            COMPANY + " TEXT," +
            POSITION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
