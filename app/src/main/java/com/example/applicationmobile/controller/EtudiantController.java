package com.example.applicationmobile.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.applicationmobile.handler.EtudiantHandler;
import com.example.applicationmobile.model.Etudiant;
import com.example.applicationmobile.model.Prof;

import java.util.ArrayList;
import java.util.List;

public class EtudiantController {
    private EtudiantHandler etudiantHandler;
    public EtudiantController (Context context) {
        this.etudiantHandler = new EtudiantHandler(context);
    }

    public long ajouter (ContentValues values) {
        this.etudiantHandler.open();
        long id_new = this.etudiantHandler.insert(values);
        this.etudiantHandler.close();
        return id_new;
    }

    public int Supprimer (String NIE) {
        String [] whereArgs = {NIE};
        this.etudiantHandler.open();
        int id_new = this.etudiantHandler.delete("NIE", whereArgs);
        this.etudiantHandler.close();
        return id_new;
    }

    public int editer (String NIE, ContentValues values) {
        String [] whereArgs = {NIE};
        this.etudiantHandler.open();
        int id_edite = this.etudiantHandler.update(values,"NIE", whereArgs);
        this.etudiantHandler.close();
        return id_edite;
    }

    public List<Etudiant> getEtudiants () {
        this.etudiantHandler.open();
        Cursor results = this.etudiantHandler.query(null, null, null, null);
        List<Etudiant> etudiants = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            do {
                Etudiant etudiant = new Etudiant();
                // es colonnes sont 0 pour matiere_ID, 1 pour libelle, 2 pour nip_prof, selon l'ordre que l'on a créé les tables
                etudiant.NIE= results.getString(0);
                etudiant.nom = results.getString(1);
                etudiant.prenom = results.getString(2);
                etudiant.classe_ID = results.getString(3);
                etudiant.dateNaissance = results.getString(4);
                etudiant.adresse = results.getString(5);

                etudiants.add(etudiant);
            } while (results.moveToNext());
        }

        if (results != null) {
            results.close();
        }

        this.etudiantHandler.close();
        return etudiants;
    }

}
