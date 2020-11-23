package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "login";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "fName";
    public static final String COLUMN_LAST_NAME = "lName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE " +
            TABLE_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FIRST_NAME + " TEXT, " +
            COLUMN_LAST_NAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_PASSWORD + " TEXT );";

    // Initialize the database object.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database with the database creation statement.
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS '" + TABLE_USERS + "'");
        onCreate(db);
    }


    public boolean addUser(UserData users){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_FIRST_NAME,users.getFirstName());
        contentValues.put(COLUMN_LAST_NAME,users.getLastName());
        contentValues.put(COLUMN_EMAIL,users.getEmail());
        contentValues.put(COLUMN_PASSWORD,users.getPassword());

        long result=db.insert(TABLE_USERS,null, contentValues);

        if (result==-1){
            return false;
        } else {
            return true;
        }

    }

    public String getLoginData(String email, String password)
    {
        SQLiteDatabase sql = this.getReadableDatabase();
        String query=" select count(*) from " + TABLE_USERS + " where email ='"+email+"' and password='"+password+"'";
        Cursor cursor =sql.rawQuery(query,null);
        cursor.moveToFirst();
        String count = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
        return count;

    }
}