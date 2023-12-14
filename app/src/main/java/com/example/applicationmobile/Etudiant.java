package com.example.applicationmobile;

public class Etudiant {
    private String NIE, nom, prenom, classe_ID, dateNaissance, adresse;
    public String getNIE () {
        return this.NIE;
    }
    public void setNIE (String NIE) {
        this.NIE = NIE;
    }

    public String getNom () {
        return this.nom;
    }
    public void setNom (String nom) {
        this.nom = nom;
    }

    public String getPrenom () {
        return this.prenom;
    }
    public void setPrenom (String prenom) {
        this.prenom = prenom;
    }

    public String getClasse_ID () {
        return this.classe_ID;
    }
    public void setClasse_ID (String classe_ID) {
        this.classe_ID = classe_ID;
    }

    public String getDateNaissance () {
        return this.dateNaissance;
    }
    public void setDateNaissance (String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse () {
        return this.adresse;
    }
    public void setAdresse (String adresse) {
        this.adresse = adresse;
    }

}
