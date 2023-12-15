package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicationmobile.handler.DatabaseHelper;

public class MatiereController {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public MatiereController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertMatiere(String libelle, String profNIP) {
        ContentValues values = new ContentValues();
        values.put("libelle", libelle);
        values.put("prof_NIP", profNIP);

        return database.insert("Matiere", null, values);
    }

    public Cursor getAllMatieres() {
        return database.query("Matiere", null, null, null, null, null, null);
    }

    public int updateMatiere(int matiereId, String libelle, String profNIP) {
        ContentValues values = new ContentValues();
        values.put("libelle", libelle);
        values.put("prof_NIP", profNIP);

        return database.update("Matiere", values, "matiere_ID=?", new String[]{String.valueOf(matiereId)});
    }

    public int deleteMatiere(int matiereId) {
        return database.delete("Matiere", "matiere_ID=?", new String[]{String.valueOf(matiereId)});
    }
}
