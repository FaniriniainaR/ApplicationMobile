package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ClasseHandler extends AbstractHandler {

    public ClasseHandler(Context context) {
        super(context);
    }

    @Override
    public long insert(ContentValues values) {
        return database.insert("Classe", null, values);
    }

    @Override
    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return database.update("Classe", values, whereClause, whereArgs);
    }

    @Override
    public int delete(String whereClause, String[] whereArgs) {
        return database.delete("Classe", whereClause, whereArgs);
    }


    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query("Classe", projection, selection, selectionArgs, null, null, sortOrder);
    }
}
