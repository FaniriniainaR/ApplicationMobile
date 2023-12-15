/*package com.example.applicationmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;  // Import Intent
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfActivity extends AppCompatActivity {

    private ProfController profController;
    private EditText editTextNIP, editTextNom, editTextPrenom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe);

        // Initialize UI elements
        editTextNIP = findViewById(R.id.editTextNIP);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);

        Button addButton = findViewById(R.id.addButton);

        // Initialize ProfController to manage the database
        profController = new ProfController(this);
        profController.open();

        // Afficher tous les professeurs dans une ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, (List) profController.getAllProfs());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Ajouter un professeur
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfActivity", "Button Ajouter clicked");
                // Navigate to the AddProfessorActivity when the "Ajouter" button is clicked
                Intent intent = new Intent(ProfActivity.this, AddProfessorActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, (List) profController.getAllProfs());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void clearEditTextFields() {
        editTextNIP.setText("");
        editTextNom.setText("");
        editTextPrenom.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermer la base de données lorsque l'activité est détruite
        profController.close();
    }
}
*/