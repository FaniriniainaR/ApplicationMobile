package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MatiereHandler extends AbstractHandler {

    public MatiereHandler(Context context) {
        super(context);
    }

    @Override
    public long insert(ContentValues values) {
        return database.insert("Matiere", null, values);
    }

    @Override
    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return database.update("Matiere", values, whereClause, whereArgs);
    }

    @Override
    public int delete(String whereClause, String[] whereArgs) {
        return database.delete("Matiere", whereClause, whereArgs);
    }


    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query("Matiere", projection, selection, selectionArgs, null, null, sortOrder);
    }
}
