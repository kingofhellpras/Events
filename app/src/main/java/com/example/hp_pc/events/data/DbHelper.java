package com.example.hp_pc.events.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.hp_pc.events.data.EventContract.UsersEntry.COLUMN_EMAIL;
import static com.example.hp_pc.events.data.EventContract.UsersEntry.COLUMN_PASS;
import static com.example.hp_pc.events.data.EventContract.UsersEntry.USER_TABLE;

public class DbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " + EventContract.EventEntry.TABLE_NAME + "("
                + EventContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventContract.EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL, "
                + EventContract.EventEntry.COLUMN_EVENT_DESC + " TEXT NOT NULL); ";

        final String CREATE_TABLE_ATT = "CREATE TABLE " + EventContract.AttendeesEntry.TABLE_NAME + "("
                + EventContract.AttendeesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventContract.AttendeesEntry.COLUMN_ATTENDEES_NAME + " TEXT NOT NULL, "
                + EventContract.AttendeesEntry.COLUMN_ATTENDEES_EVENT + " TEXT NOT NULL, "
                + EventContract.AttendeesEntry.COLUMN_ATTENDEES_GENDER + " INTEGER NOT NULL,"
                + EventContract.AttendeesEntry.COLUMN_ATTENDEES_STATUS + " INTEGET NOT NULL);";


        final String CREATE_TABLE_USERS = "CREATE TABLE " + EventContract.UsersEntry.TABLE_NAME + "("
                + EventContract.UsersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_PASS + " TEXT NOT NULL); ";


        // Execute the SQL statement
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ATT);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
