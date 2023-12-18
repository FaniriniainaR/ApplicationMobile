package com.example.applicationmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddProfessorActivity extends AppCompatActivity {

    private ProfController profController;
    private EditText editTextNIP, editTextNom, editTextPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profs);

        // Initialize UI elements
        editTextNIP = findViewById(R.id.editTextClasse_ID);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);

        Button saveButton = findViewById(R.id.saveButton);

        // Initialize ProfController to manage the database
        profController = new ProfController(this);
        profController.open();

        // Set up a click listener for the "Enregistrer" (Save) button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered values from EditText fields
                String nip = editTextNIP.getText().toString();
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();

                // Insert the professor into the database
                long result = profController.insertProf(nip, nom, prenom);

                // Check the result of the insertion
                if (result != -1) {
                    // Success
                    Toast.makeText(AddProfessorActivity.this, "Professeur ajouté avec succès", Toast.LENGTH_SHORT).show();

                    // Optionally, you can go back to the professor list activity or finish this activity
                    // Example: finish();
                } else {
                    // Failure
                    Toast.makeText(AddProfessorActivity.this, "Échec de l'ajout du professeur", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        profController.close();
    }
}
