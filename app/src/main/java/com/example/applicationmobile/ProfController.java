package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicationmobile.helper.DatabaseHelper;

public class ProfController {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ProfController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertProf(String nip, String nom, String prenom) {
        ContentValues values = new ContentValues();
        values.put("NIP", nip);
        values.put("nom", nom);
        values.put("prenom", prenom);

        return database.insert("Prof", null, values);
    }

    public Cursor getAllProfs() {
        return database.query("Prof", null, null, null, null, null, null);
    }

    public int updateProf(String nip, String nom, String prenom) {
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);

        return database.update("Prof", values, "NIP=?", new String[]{nip});
    }

    public int deleteProf(String nip) {
        return database.delete("Prof", "NIP=?", new String[]{nip});
    }
}
