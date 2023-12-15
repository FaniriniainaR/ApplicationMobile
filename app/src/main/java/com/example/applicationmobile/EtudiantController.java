package com.example.applicationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicationmobile.handler.DatabaseHelper;

public class EtudiantController {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public EtudiantController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertEtudiant(String nie, String nom, String prenom, String classeID, String dateNaissance, String adresse) {
        ContentValues values = new ContentValues();
        values.put("NIE", nie);
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("classe_ID", classeID);
        values.put("dateNaissance", dateNaissance);
        values.put("adresse", adresse);

        return database.insert("Etudiant", null, values);
    }

    public Cursor getAllEtudiants() {
        return database.query("Etudiant", null, null, null, null, null, null);
    }

    public int updateEtudiant(String nie, String nom, String prenom, String classeID, String dateNaissance, String adresse) {
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("classe_ID", classeID);
        values.put("dateNaissance", dateNaissance);
        values.put("adresse", adresse);

        return database.update("Etudiant", values, "NIE=?", new String[]{nie});
    }

    public int deleteEtudiant(String nie) {
        return database.delete("Etudiant", "NIE=?", new String[]{nie});
    }
}
