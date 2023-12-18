package com.example.applicationmobile.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.applicationmobile.R;
import com.example.applicationmobile.model.*;
import java.util.List;

public class GenericAdapter<T> extends ArrayAdapter<T> {

    private int layoutResourceId;

    public GenericAdapter(Context context, int layoutResourceId, List<T> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResourceId, parent, false);
        }

        // Vous devez adapter cela en fonction de la structure de vos classes
        if (item instanceof Classe) {
            bindClasseData((Classe) item, convertView);
        } else if (item instanceof Etudiant) {
            bindEtudiantData((Etudiant) item, convertView);
        } else if (item instanceof Prof) {
            bindProfData((Prof) item, convertView);
        } else if (item instanceof Matiere) {

        }

        return convertView;
    }

    // Ajoutez des méthodes bind spécifiques pour chaque type si nécessaire
    private void bindClasseData(Classe classe, View view) {
        TextView classe_Id = view.findViewById(R.id.textViewClasse_Id);
        TextView filiere = view.findViewById(R.id.textViewFiliere);
        TextView nbrEtudiant = view.findViewById(R.id.textViewNbr);

        classe_Id.setText(classe.classe_ID);
        filiere.setText(classe.filiere);
        nbrEtudiant.setText(String.valueOf(classe.nbrEtudiant));
    }

    private void bindEtudiantData(Etudiant etudiant, View view) {
        // Logique pour lier les données d'un étudiant à la vue
        TextView NIE = view.findViewById(R.id.textViewNIE);
        TextView nom = view.findViewById(R.id.textViewEtdNom);
        TextView prenom = view.findViewById(R.id.textViewEtdPrenom);

        NIE.setText(etudiant.NIE);
        nom.setText(etudiant.nom);
        prenom.setText(etudiant.prenom);
    }

    private void bindProfData(Prof prof, View view) {
        // Logique pour lier les données d'un professeur à la vue
    }
}
