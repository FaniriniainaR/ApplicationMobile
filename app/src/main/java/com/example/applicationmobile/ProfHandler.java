package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ProfHandler extends AbstractHandler {

    public ProfHandler(Context context) {
        super(context);
    }

    @Override
    public long insert(ContentValues values) {
        return database.insert("Prof", null, values);
    }

    @Override
    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return database.update("Prof", values, whereClause, whereArgs);
    }

    @Override
    public int delete(String whereClause, String[] whereArgs) {
        return database.delete("Prof", whereClause, whereArgs);
    }


    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query("Prof", projection, selection, selectionArgs, null, null, sortOrder);
    }
}
