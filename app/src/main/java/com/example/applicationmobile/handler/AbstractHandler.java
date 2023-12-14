package com.example.applicationmobile.handler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractHandler {

    protected SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public AbstractHandler(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    // Méthodes abstraites pour les opérations CRUD
    public abstract long insert(ContentValues values);
    public abstract int update(ContentValues values, String whereClause, String[] whereArgs);
    public abstract int delete(String whereClause, String[] whereArgs);
    public abstract Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);
}
