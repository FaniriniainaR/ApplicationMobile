package com.example.applicationmobile.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.applicationmobile.handler.ProfHandler;
import com.example.applicationmobile.model.Matiere;
import com.example.applicationmobile.model.Prof;
import com.example.applicationmobile.model.Classe;
import java.util.ArrayList;
import java.util.List;

public class ProfController {
    private ProfHandler profHandler;
    public ProfController (Context context) {
        this.profHandler = new ProfHandler(context);
    }

    public long ajouter (ContentValues values) {
        this.profHandler.open();
        long id_new = this.profHandler.insert(values);
        this.profHandler.close();
        return id_new;
    }

    public int editer (String NIP, ContentValues values) {
        String [] whereArgs = {NIP};
        this.profHandler.open();
        int id_edite = this.profHandler.update(values, "NIP", whereArgs);
        this.profHandler.close();
        return id_edite;
    }

    public int supprimer (String NIP) {
        String [] whereArgs = {NIP};
        this.profHandler.open();
        int id_supprime = this.profHandler.delete("NIP", whereArgs);
        this.profHandler.close();
        return id_supprime;
    }

    public List<Prof> getProfs () {
        this.profHandler.open();
        Cursor results = this.profHandler.query(null, null, null, null);
        List<Prof> profs = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            do {
                Prof prof = new Prof();
                // es colonnes sont 0 pour matiere_ID, 1 pour libelle, 2 pour nip_prof, selon l'ordre que l'on a créé les tables
                prof.NIP= results.getString(0);
                prof.nom = results.getString(1);
                prof.prenom = results.getString(2);

                profs.add(prof);
            } while (results.moveToNext());
        }

        if (results != null) {
            results.close();
        }

        this.profHandler.close();
        return profs;
    }

    public List<Matiere> getMatieres (String NIP) {
        this.profHandler.open();
        Cursor results = this.profHandler.getMatieresByProf(NIP);
        List<Matiere> matieres = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            do {
                Matiere matiere = new Matiere();
                // es colonnes sont 0 pour matiere_ID, 1 pour libelle, 2 pour nip_prof, selon l'ordre que l'on a créé les tables
                matiere.matiere_ID= results.getInt(0);
                matiere.libelle = results.getString(1);
                matiere.prof_NIP = results.getString(2);

                matieres.add(matiere);
            } while (results.moveToNext());
        }

        if (results != null) {
            results.close();
        }

        this.profHandler.close();
        return matieres;
    }

    public List<Classe> getClasses (String NIP) {
        this.profHandler.open();
        Cursor results = this.profHandler.getClassesByProf(NIP);
        List<Classe> classes = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            do {
                Classe classe = new Classe();
                // es colonnes sont 0 pour matiere_ID, 1 pour libelle, 2 pour nip_prof, selon l'ordre que l'on a créé les tables
                classe.classe_ID= results.getString(0);
                classe.filiere = results.getString(1);
                classe.nbrEtudiant = results.getInt(2);

                classes.add(classe);
            } while (results.moveToNext());
        }

        if (results != null) {
            results.close();
        }

        this.profHandler.close();
        return classes;
    }
}
