package com.example.applicationmobile.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicationmobile.handler.AbstractHandler;

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

    public SQLiteDatabase getReadableDatabase() {
        return this.getReadableDatabase();
    }

    @Override
    public int delete(String whereClause, String[] whereArgs) {
        return database.delete("Classe", whereClause, whereArgs);
    }

    @Override
    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query("Classe", projection, selection, selectionArgs, null, null, sortOrder);
    }

    public Cursor getMatieresByClasse(String classeID) {
        String query = "SELECT Matiere.* FROM Matiere " +
                "JOIN MatiereClasse ON Matiere.matiere_ID = MatiereClasse.matiere_ID " +
                "JOIN Classe ON MatiereClasse.classe_ID = Classe.classe_ID " +
                "WHERE Classe.classe_ID = ?";
        String[] selectionArgs = {classeID};
        return database.rawQuery(query, selectionArgs);
    }

    public Cursor getEtudiantsByClasse(String classeID) {
        String query = "SELECT Etudiant.* FROM Etudiant " +
                "JOIN Classe ON Etudiant.classe_ID = Classe.classe_ID " +
                "WHERE Classe.classe_ID = ?";
        String[] selectionArgs = {classeID};
        return database.rawQuery(query, selectionArgs);
    }

    public Cursor getProfsByClasse(String classeID) {
        String query = "SELECT DISTINCT Prof.* FROM Prof " +
                "JOIN Matiere ON Prof.NIP = Matiere.NIP " +
                "JOIN ClasseMatiere ON Matiere.matiere_ID = ClasseMatiere.matiere_ID " +
                "JOIN Classe ON ClasseMatiere.classe_ID = Classe.classe_ID " +
                "WHERE Classe.classe_ID = classeID";
        String[] selectionArgs = {classeID};
        return database.rawQuery(query, selectionArgs);
    }
}
