package com.example.applicationmobile.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.applicationmobile.handler.ClasseMatiereHandler;
import com.example.applicationmobile.handler.MatiereHandler;
import com.example.applicationmobile.model.Classe;
import com.example.applicationmobile.model.Matiere;

import java.util.ArrayList;
import java.util.List;

public class MatiereController {
    private MatiereHandler matiereHandler;
    private ClasseMatiereHandler classeMatiereHandler;
    public MatiereController (Context context) {
        this.matiereHandler = new MatiereHandler(context);
        this.classeMatiereHandler = new ClasseMatiereHandler(context);
    }
    public long ajouter (ContentValues values) {
        this.matiereHandler.open();
        long nbrLigneAjouter = this.matiereHandler.insert(values);
        this.matiereHandler.close();
        return nbrLigneAjouter;
    }

    public int editer (int matiere_ID, ContentValues values) {
        String [] whereArgs = {String.valueOf(matiere_ID)};
        this.matiereHandler.open();
        int nbrLigneEdite = this.matiereHandler.update(values, "matiere_ID", whereArgs);
        this.matiereHandler.close();
        return nbrLigneEdite;
    }

    public int supprimer (int matiere_ID) {
        String [] whereArgs = {String.valueOf(matiere_ID)};
        this.matiereHandler.open();
        int nbrLigneSupprime = this.matiereHandler.delete("matiere_ID", whereArgs);
        this.matiereHandler.close();
        return nbrLigneSupprime;
    }

    //eto no apetaka le table de jointure entre Matiere et classe, vu que c'est une relation plusieus-plusieurs
    //refa any amle activity anle matiere elah ohatra de mi ajoute classe anakiray (id anle matiere si id anle
    // classe no ataonlah param anaty Values)
    public long ajouterClasse (ContentValues values) {
        this.classeMatiereHandler.open();
        long nbrLigneAjouter = this.classeMatiereHandler.insert(values);
        this.classeMatiereHandler.close();
        return nbrLigneAjouter;
    }

    // pareil pour editerClasse, ny param anla eto ny id anle matiere sy le classe, atao anaty values le id anle classe ho modifiena
    public int editerClasse (ContentValues values, int matiere_ID) {
        String [] whereArgs = {String.valueOf(matiere_ID)};
        this.classeMatiereHandler.open();
        int nbrLigneEdite = this.classeMatiereHandler.update(values, "matiere_ID", whereArgs);
        this.classeMatiereHandler.close();
        return nbrLigneEdite;
    }

    //toujours le même principe, supprimer l'enregistre
    public int supprimerClasse (int matiere_ID, String classe_ID) {
        String [] whereArgs = {String.valueOf(matiere_ID), classe_ID};
        String whereClause = "matiere_ID = ? AND classe_ID = ?";
        this.classeMatiereHandler.open();
        int nbrLigneSupprime = this.classeMatiereHandler.delete(whereClause, whereArgs);
        this.classeMatiereHandler.close();
        return nbrLigneSupprime;
    }
    public List<Matiere> getMatieres () {
        this.matiereHandler.open();
        Cursor results = this.matiereHandler.query(null, null, null, null);
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

        this.matiereHandler.close();
        return matieres;
    }

    public List<Classe> getClasses (int matiere_ID) {
        this.matiereHandler.open();
        Cursor results = this.matiereHandler.getClassesByMatiere(matiere_ID);
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

        this.matiereHandler.close();
        return classes;
    }


}
