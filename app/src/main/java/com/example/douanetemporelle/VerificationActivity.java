package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        TextView txtNom = findViewById(R.id.nom_voy_ot);
        TextView txtAnneeNaiss = findViewById(R.id.annee_naiss_ot);
        TextView txtDestination = findViewById(R.id.destination_ot);
        TextView txtAgeTemp = findViewById(R.id.age_temp_ot);
        TextView txtMotif = findViewById(R.id.motif_ot);
        Button btnApprouver = findViewById(R.id.btn_approuv_ot);
        Button btnRefuser = findViewById(R.id.btn_refu_ot);

        // Récupération mte3 des données eli jeyin mel Activité 1 bel Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra(cles.nomVoyageur());
        int anneeNaiss = intent.getIntExtra(cles.anneeNaissance(), 2000);
        int anneeDest = intent.getIntExtra(cles.anneeDestination(), 2050);
        String motif = intent.getStringExtra(cles.motif());

        // Calcul mte3 l'age temporel
        int ageTemporel = anneeDest - anneeNaiss;

        // Affichage mte3 les informations sur l'interface passeport
        txtNom.setText("Nom: " + (nom != null ? nom : ""));
        txtAnneeNaiss.setText("Annee de naissance: " + anneeNaiss);
        txtDestination.setText("Destination: " + anneeDest);
        txtAgeTemp.setText("Age Temporel: " + ageTemporel);
        txtMotif.setText("Motif: " + (motif != null ? motif : ""));

        // Vérification de la condition : Motif 'Modification du passé' ET différence est un nombre premier
        if ("Modification du passé".equals(motif) && estNombrePremier(ageTemporel)) {
            btnApprouver.setEnabled(false); // sakart l bouton
        }

        // l action mte3 el bouton approuver le saut temporel
        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(VerificationActivity.this, activite3.class);
                resultIntent.putExtra(cles.verdict(), true);
                resultIntent.putExtra(cles.nomVoyageur(), nom);
                startActivity(resultIntent);
            }
        });

        // Action mte3 el bouton refu du saut
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(VerificationActivity.this, activite3.class);
                resultIntent.putExtra(cles.verdict(), false);
                resultIntent.putExtra(cles.nomVoyageur(), nom);
                startActivity(resultIntent);
            }
        });
    }

    // Fonction bech ntesti beha ken nbre premier wala lee
    private boolean estNombrePremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}