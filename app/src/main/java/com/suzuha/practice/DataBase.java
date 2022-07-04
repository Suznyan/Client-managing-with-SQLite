package com.suzuha.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Client_List";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myList";
    private static final String ID_COL = "id";
    private static final String URL = "url";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String GENDER = "gender";
    private static final String NATIONALITY = "nationality";

    public DataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = " CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + URL + " TEXT,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT,"
                + GENDER + " TEXT,"
                + NATIONALITY + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void ADD(String url, String name, String email, String password, String gender, String nationality) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(URL, url);
        values.put(NAME, name);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        values.put(GENDER, gender);
        values.put(NATIONALITY, nationality);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteClient(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "email=?", new String[]{email});
        db.close();
    }

    public ArrayList<Client> readList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Client> al_Client = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                al_Client.add(new Client(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return al_Client;
    }
}
