package com.example.applicationmobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra("position", 0);

        // Utilisez la position pour déterminer quel fichier XML charger
        switch(position) {
            case 0:
                setContentView(R.layout.matiers_details);
                break;
            case 1:
                setContentView(R.layout.etudiant_details);
                break;
            case 2:
                setContentView(R.layout.classe_details);
                break;
            case 3:
                setContentView(R.layout.prof_details);
                break;
            default:
                setContentView(R.layout.defaults_details);
                break;
        }

        // Remplissez le ListView avec les données de la base de données
        ListView listView = (ListView) findViewById(R.id.listView);
        // Remplacez cette liste par vos données de la base de données
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}

