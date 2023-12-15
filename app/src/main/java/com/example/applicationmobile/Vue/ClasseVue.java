package com.example.applicationmobile.Vue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;  // Import Intent
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobile.AddProfessorActivity;
import com.example.applicationmobile.R;
import com.example.applicationmobile.controller.ClasseController;
import com.example.applicationmobile.model.Classe;

import java.util.ArrayList;
import java.util.List;

public class ClasseVue extends AppCompatActivity {

    private ClasseController classeController;
    private EditText editTextFilier, editTextNom, editTextPrenom;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe);

        // Initialize UI elements
       /* editTextFilier = findViewById(R.id.editTextNIP);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);*/

        Button addButton = findViewById(R.id.addButton);

        // Initialize ProfController to manage the database
        classeController = new ClasseController(this);
        classeController.addClasse("L3IRD", 35);
        classeController.addClasse("L2IRD", 35);
        classeController.addClasse("L1IRD", 35);

        classeController.printClasses();
        classeController.verifierInsertion("L3IRD", 35);



        List<Classe> classes = classeController.getClasses();

        // Create a list to hold the filiers
        List<String> filiers = new ArrayList<>();

        // Extract the filier from each Classe
        for (Classe classe : classes) {
            filiers.add(classe.getClasseFilier());
        }

        // Create an ArrayAdapter using the list of filiers
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filiers);

        // Set the adapter for the ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        // Ajouter un professeur
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfActivity", "Button Ajouter clicked");
                // Navigate to the AddProfessorActivity when the "Ajouter" button is clicked
                Intent intent = new Intent(ClasseVue.this, AddProfessorActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateListView() {
        ArrayAdapter<Classe> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classeController.getClasses());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

   /* private void clearEditTextFields() {
        editTextNIP.setText("");
        editTextNom.setText("");
        editTextPrenom.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermer la base de données lorsque l'activité est détruite
        classeController.close();
    }*/
}
