package com.example.applicationmobile.Vue.Classe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.applicationmobile.R;
import com.example.applicationmobile.controller.ClasseController;

public class AjouterClasse extends AppCompatActivity {
    private ClasseController classeController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classeController = new ClasseController(this);
        setContentView(R.layout.activity_ajouter_classe);
        EditText editTextClasse_ID = findViewById(R.id.editTextClasse_ID);
        EditText editTextClasseFiliere = findViewById(R.id.editTextClasseFiliere);
        EditText editTextNbrEtudiant = findViewById(R.id.editTextNbrEtudiant);

        Button btnAjouter = findViewById(R.id.buttonAddClasse);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  ContentValues values = new ContentValues();
                  String classe_ID = editTextClasse_ID.getText().toString();
                  String filiere = editTextClasseFiliere.getText().toString();
                  int nbrEtudiant = Integer.parseInt (editTextNbrEtudiant.getText().toString());
                  values.put("classe_ID", classe_ID);
                  values.put("classeFiliere", filiere);
                  values.put("nbrEtudiant", nbrEtudiant);

                  long ln = classeController.ajouter(values);
                  if (ln>0) {
                      Intent intent = new Intent(AjouterClasse.this, ListeClasse.class);
                      startActivity(intent);
                  }
              }
          }

        );
    }
}