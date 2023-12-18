package com.example.applicationmobile.helper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "gestion_ecole_db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_ETUDIANT =  "CREATE TABLE Etudiant (\n" +
            "    NIE TEXT PRIMARY KEY,\n" +
            "    nom TEXT,\n" +
            "    prenom TEXT,\n" +
            "    classe_ID TEXT,\n" +
            "    dateNaissance TEXT,\n" +
            "    adresse TEXT,\n" +
            "    FOREIGN KEY (classe_ID) REFERENCES Classe(classe_ID)\n" +
            ");\n"
            ;
    private static final String CREATE_TABLE_CLASSE = "CREATE TABLE Classe (\n" +
            "    classe_ID TEXT PRIMARY KEY,\n" +
            "    classeFiliere TEXT,\n" +
            "    nbrEtudiant INTEGER\n" +
            ");\n"
            ;
    private static final String CREATE_TABLE_PROF = "CREATE TABLE Prof (\n" +
            "    NIP TEXT PRIMARY KEY,\n" +
            "    nom TEXT,\n" +
            "    prenom TEXT\n" +
            ");\n"
            ;
    private static final String CREATE_TABLE_MATIERE = "CREATE TABLE Matiere (\n" +
            "    matiere_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    libelle TEXT,\n" +
            "    prof_NIP TEXT,\n" +
            "    FOREIGN KEY (prof_NIP) REFERENCES Prof(NIP)\n" +
            ");\n"
            ;

    private static  final String CREATE_TABLE_JOINTURE_CLASSE_MATIERE = "CREATE TABLE ClasseMatiere (\n" +
            "    classe_ID TEXT,\n" +
            "    matiere_ID INTEGER,\n" +
            "    PRIMARY KEY (classe_ID, matiere_ID),\n" +
            "    FOREIGN KEY (classe_ID) REFERENCES Classe(classe_ID),\n" +
            "    FOREIGN KEY (matiere_ID) REFERENCES Matiere(matiere_ID)\n" +
            ");\n"
            ;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROF);
        db.execSQL(CREATE_TABLE_MATIERE);
        db.execSQL(CREATE_TABLE_CLASSE);
        db.execSQL(CREATE_TABLE_JOINTURE_CLASSE_MATIERE);
        db.execSQL(CREATE_TABLE_ETUDIANT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ClasseMatiere");
        db.execSQL("DROP TABLE IF EXISTS Matiere");
        db.execSQL("DROP TABLE IF EXISTS Prof");
        db.execSQL("DROP TABLE IF EXISTS Classe");
        db.execSQL("DROP TABLE IF EXISTS Etudiant");
        onCreate(db);
    }
}
