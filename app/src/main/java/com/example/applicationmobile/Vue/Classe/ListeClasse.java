package com.example.applicationmobile.Vue.Classe;

import android.content.Intent;  // Import Intent
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobile.AddProfessorActivity;
import com.example.applicationmobile.R;
import com.example.applicationmobile.Vue.Etudiant.ListeEtudiant;
import com.example.applicationmobile.controller.ClasseController;
import com.example.applicationmobile.model.Classe;
import com.example.applicationmobile.helper.GenericAdapter;

import java.util.List;

public class ListeClasse extends AppCompatActivity {

    private ClasseController classeController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classeController = new ClasseController(this);
        setContentView(R.layout.activity_liste_classe);
        ListView listView = findViewById(R.id.listClasse);
        updateListView();
        Button addButton = findViewById(R.id.btnAjouter);
        // Ajouter un écouteur de clic sur la ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'objet Classe correspondant à l'élément cliqué
                Classe classeCliquee = (Classe) parent.getItemAtPosition(position);

                // Récupérer l'id de la classe
                String classeId = classeCliquee.classe_ID;

                // Passer l'id de la classe à l'activité suivante
                Intent intent = new Intent(ListeClasse.this, ListeEtudiant.class);
                intent.putExtra("classe_ID", classeId);
                startActivity(intent);
            }
        });



        // Ajouter un professeur
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfActivity", "Button Ajouter clicked");
                // Navigate to the AddProfessorActivity when the "Ajouter" button is clicked
                Intent intent = new Intent(ListeClasse.this, AjouterClasse.class);
                startActivity(intent);
            }
        });

    }

    private void updateListView() {
        List<Classe> listeClasse = classeController.getClasses();
        GenericAdapter<Classe> classeAdapter = new GenericAdapter<>(this, R.layout.list_item_classe, listeClasse);
        ListView listView = findViewById(R.id.listClasse);
        listView.setAdapter(classeAdapter);

        if (listeClasse == null || listeClasse.isEmpty()) {
            Toast.makeText(this, "La liste des classes est vide", Toast.LENGTH_SHORT).show();
        }
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
