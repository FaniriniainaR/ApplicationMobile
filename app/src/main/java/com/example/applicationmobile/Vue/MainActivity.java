package com.example.applicationmobile.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.applicationmobile.R;
import com.example.applicationmobile.Vue.Classe.ListeClasse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add("Matière");
        items.add("Étudiant");
        items.add("Classe");
        items.add("Professeur");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView listView = (ListView) findViewById(R.id.lvItems);
        if (listView != null) {
            listView.setAdapter(itemsAdapter); // Assurez-vous que 'adapter' est correctement défini
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 0:
                        setContentView(R.layout.activity_matiers);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, ListeClasse.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent = new Intent(MainActivity.this, ListeClasse.class);
                        startActivity(intent);
                        break;
                    case 3:
                        setContentView(R.layout.activity_prof);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
