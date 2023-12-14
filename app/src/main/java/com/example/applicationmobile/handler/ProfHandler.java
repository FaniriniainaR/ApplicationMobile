package com.example.applicationmobile.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.applicationmobile.handler.AbstractHandler;

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

    public Cursor getClassesByProf(String nip) {
        String query = "SELECT DISTINCT Classe.* " +
                "FROM Classe " +
                "JOIN ClasseMatiere ON ClasseMatiere.classe_ID = Classe.classe_ID " +
                "JOIN Matiere ON Matiere.matiere_ID = ClasseMatiere.matiere_ID " +
                "WHERE Matiere.NIP = ?";

        String[] selectionArgs = {nip};

        return database.rawQuery(query, selectionArgs);
    }

    public Cursor getMatieresByProf(String nip) {
        String query = "SELECT Matiere.* " +
                "FROM Matiere " +
                "WHERE Matiere.nip_prof = ?";

        String[] selectionArgs = {nip};

        return database.rawQuery(query, selectionArgs);
    }
}
