package com.example.applicationmobile.Vue;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobile.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra("position", 0);

        // Utilisez la position pour déterminer quel fichier XML charger
        switch(position) {
            case 0:
                setContentView(R.layout.activity_matiers);
                break;
            case 1:
                setContentView(R.layout.activity_etudiant);
                break;
            case 2:
                setContentView(R.layout.activity_classe);
                break;
            case 3:
                setContentView(R.layout.activity_prof);
                break;
            default:
                setContentView(R.layout.activity_defaults);
                break;
        }
    }
}

