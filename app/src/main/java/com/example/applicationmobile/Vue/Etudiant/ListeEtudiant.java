package com.example.applicationmobile.Vue.Etudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applicationmobile.R;
import com.example.applicationmobile.controller.EtudiantController;
import com.example.applicationmobile.helper.GenericAdapter;
import com.example.applicationmobile.model.Classe;
import com.example.applicationmobile.model.Etudiant;

import java.util.List;

public class ListeEtudiant extends AppCompatActivity {

    private EtudiantController etudiantController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etudiantController = new EtudiantController(this);
        Intent intent = getIntent();
        String classe_ID = intent.getStringExtra("Classe_ID");
        setContentView(R.layout.activity_liste_etudiant);
        updateListView(classe_ID);
    }

    private void updateListView(String classe_ID) {
        List<Etudiant> listeEtudiant = etudiantController.getEtudiants(classe_ID);
        GenericAdapter<Etudiant> etudiantAdapter = new GenericAdapter<>(this, R.layout.list_item_etudiant, listeEtudiant);
        ListView listView = findViewById(R.id.listeEtudiant);
        listView.setAdapter(etudiantAdapter);

        if (listeEtudiant == null || listeEtudiant.isEmpty()) {
            Toast.makeText(this, "La liste d'étudiants est vide", Toast.LENGTH_SHORT).show();
        }
    }
}