package com.example.douanetemporel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activite3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite3);
       //bech nekhou les donnes w nhothom f les variables
        LinearLayout fond = findViewById(R.id.AH_main);
        TextView tvMessage = findViewById(R.id.AH_message);
        Button btnNouveau = findViewById(R.id.AH_nvvoyageur);

        Intent intent = getIntent();

        // bech naqra l verdict mtee l boutton ili bech yjini min activites 2
        boolean approuve = intent.getBooleanExtra(
                cles.key(cles.MATRICULE,cles.VERDICT), false);

        //bech nekhou lesem w nhotou f variable nom
        String nom = intent.getStringExtra(
                cles.key(cles.MATRICULE, cles.NOM));

        //bech nchouf si approuve true le background ywali vert sion rouge
        if (approuve) {
            fond.setBackgroundResource(R.drawable.fondvert); //bech nbadel lbackground vet
            tvMessage.setText("Bon voyage " + nom); //bech nhot message f zone mtee lmessage fil activite
        } else { // وإلا (إذا كان القرار رفضاً)
            fond.setBackgroundResource(R.drawable.fondrouge); //bech nbadel lbackground rouge
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        //waqt nenzel ala boutton nouveau voyageur
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(this, MainActivity.class); //aamlt intent jdid bech yraj3ami l activites 2
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);// bech nsaker les activites ili fil fouk lmainactivites lkol w  bech naam activites jdida fiha chy(formulaire vide)
            startActivity(retour); //bech narjaa lel activites 1
            finish();
        });
    }
}