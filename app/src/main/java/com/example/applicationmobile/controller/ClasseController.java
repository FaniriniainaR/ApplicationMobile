package com.example.applicationmobile.controller;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.applicationmobile.handler.ClasseHandler;
import com.example.applicationmobile.handler.ClasseMatiereHandler;
import com.example.applicationmobile.model.Etudiant;
import com.example.applicationmobile.model.Matiere;
import com.example.applicationmobile.model.Classe;
import com.example.applicationmobile.model.Prof;

import java.util.ArrayList;
import java.util.List;

public class ClasseController {
    private ClasseHandler classeHandler;
    private ClasseMatiereHandler classeMatiereHandler;
    public ClasseController (Context context) {

        this.classeHandler = new ClasseHandler(context);
        this.classeMatiereHandler = new ClasseMatiereHandler(context);
    }

    //ajouter une nouvelle ligne dans la table classe
    public long ajouter (ContentValues values) {
        this.classeHandler.open();
        long nbrLigneAjouter = this.classeHandler.insert(values);
        this.classeHandler.close();
        return nbrLigneAjouter;
    }
    public long addClasse(String filiere, int nbrEtudiant) {
        ContentValues values = new ContentValues();
        values.put("classeFiliere", filiere);
        values.put("nbrEtudiant", nbrEtudiant);

        return this.ajouter(values);
    }

    public boolean verifierInsertion(String filiere, int nbrEtudiant) {
        this.classeHandler.open();

        // Récupérer la classe que vous venez d'insérer
        Cursor cursor = this.classeHandler.getReadableDatabase().rawQuery("SELECT * FROM Classe WHERE classeFiliere = ? AND nbrEtudiant = ?", new String[] {filiere, String.valueOf(nbrEtudiant)});

        // Vérifier si la requête a renvoyé des résultats
        if (cursor.moveToFirst()) {
            // Si la requête a renvoyé des résultats, cela signifie que les données ont été correctement insérées
            cursor.close();
            this.classeHandler.close();
            System.out.print("Mandeha");
            return true;
        } else {
            // Si la requête n'a pas renvoyé de résultats, cela signifie que les données n'ont pas été correctement insérées
            cursor.close();
            this.classeHandler.close();
            System.out.print("Tsy Mandeha");
            return false;
        }
    }


    @SuppressLint("Range")
    public void printClasses() {
        this.classeHandler.open();

        String selectQuery = "SELECT 1;";
        Cursor cursor = this.classeHandler.getReadableDatabase().rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Log.d("Classe", "Database connection successful");
        } else {
            Log.d("Classe", "Database connection failed");
        }

        cursor.close();
        this.classeHandler.close();
    }




    //suppression à partir d'un id, tsy type int le id fa string
    public int supprimer (String classe_ID) {
        String [] whereArgs = {classe_ID};
        this.classeHandler.open();
        int nbrLigneSupprime = this.classeHandler.delete("classe_ID",whereArgs);
        this.classeHandler.close();
        return nbrLigneSupprime;
    }

    public int editer (ContentValues values, String classe_id) {
        String [] whereArgs = {classe_id};
        this.classeHandler.open();
        int nbrLigneEdite = this.classeHandler.update(values,"classe_ID",whereArgs);
        this.classeHandler.close();
        return nbrLigneEdite;
    }


    public long ajouterMatiere (ContentValues values) {
        this.classeMatiereHandler.open();
        long nbrLigneAjouter = this.classeMatiereHandler.insert(values);
        this.classeMatiereHandler.close();
        return nbrLigneAjouter;
    }
    //suppression à partir d'un id, tsy type int le id fa string
    public int supprimerMatiere (String classe_ID, int matiere_ID) {
        String [] whereArgs = {classe_ID, String.valueOf(matiere_ID)};
        String whereClause = "classe_ID = ? AND matiere_ID = ?";
        this.classeMatiereHandler.open();
        int nbrLigneSupprime = this.classeMatiereHandler.delete(whereClause, whereArgs);
        this.classeMatiereHandler.close();
        return nbrLigneSupprime;
    }

    public int editerMatiere (ContentValues values, String classe_ID) {
        String [] whereArgs = {classe_ID};
        this.classeHandler.open();
        int nbrLigneEdite = this.classeHandler.update(values,"classe_ID",whereArgs);
        this.classeHandler.close();
        return nbrLigneEdite;
    }

    public List<Classe> getClasses () {
        this.classeHandler.open();
        Cursor results = this.classeHandler.query(null, null, null, null);
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

        this.classeHandler.close();
        return classes;
    }

    public List<Matiere> getMatieres (String classe_ID) {
        this.classeHandler.open();
        Cursor results = this.classeHandler.getMatieresByClasse(classe_ID);
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

        this.classeHandler.close();
        return matieres;
    }

    public List<Etudiant> getEtudiants (String classe_ID) {
        this.classeHandler.open();
        Cursor results = this.classeHandler.getEtudiantsByClasse(classe_ID);
        List<Etudiant> etudiants = new ArrayList<>();

        if (results != null && results.moveToFirst()) {
            do {
                Etudiant etudiant = new Etudiant();
                // es colonnes sont 0 pour matiere_ID, 1 pour libelle, 2 pour nip_prof, selon l'ordre que l'on a créé les tables
                etudiant.NIE = results.getString(0);
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

        this.classeHandler.close();
        return etudiants;
    }

    public List<Prof> getProfs (String classe_ID) {
        this.classeHandler.open();
        Cursor results = this.classeHandler.getProfsByClasse(classe_ID);
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

        this.classeHandler.close();
        return profs;
    }

}
