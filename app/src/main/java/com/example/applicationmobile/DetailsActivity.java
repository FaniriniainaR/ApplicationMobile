package com.example.applicationmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}

