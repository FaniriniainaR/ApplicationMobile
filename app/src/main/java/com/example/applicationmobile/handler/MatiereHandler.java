package com.example.applicationmobile.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.applicationmobile.handler.AbstractHandler;

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

    public Cursor getProfsByMatiere(int matiere_ID) {
        String query = "SELECT Prof.* " +
                "FROM Prof " +
                "WHERE Prof.NIP = ?";
        String[] selectionArgs = {String.valueOf(matiere_ID)};
        return database.rawQuery(query, selectionArgs);
    }

    public Cursor getClassesByMatiere(int matiere_ID) {
        String query = "SELECT Classe.classe_ID, Classe.classeFiliere FROM Classe " +
                "JOIN ClasseMatiere ON Classe.classe_ID = ClasseMatiere.classe_ID " +
                "JOIN Matiere ON ClasseMatiere.matiere_ID = Matiere.matiere_ID " +
                "WHERE Matiere.matiere_ID = ?";
        String[] selectionArgs = {String.valueOf(matiere_ID)};
        return database.rawQuery(query, selectionArgs);
    }
}
