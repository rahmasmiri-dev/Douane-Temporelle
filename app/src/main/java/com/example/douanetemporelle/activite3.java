package com.example.douanetemporelle;

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
        //bech naaml laison bin xml w variables en java bech najm naaml modification
        LinearLayout fond = findViewById(R.id.AH_main);
        TextView tvMessage = findViewById(R.id.AH_message);
        Button btnNouveau = findViewById(R.id.AH_nvvoyageur);

        Intent intent = getIntent();

        // bech naqra l valeur mtee l boutton ili bech yjini min activites 2
        boolean approuve = intent.getBooleanExtra(
                cles.verdict(),false);

        //bech nekhou lesem min lintent ili matricule mteou hiya edhika weli bech tasna3heli lmethode key en faire la concatenation w nhotou f variable nom
        String nom = intent.getStringExtra(
                cles.nomVoyageur());

        //bech nchouf si approuve true le background ywali vert sion rouge
        if (approuve) {
            fond.setBackgroundResource(R.drawable.fondvert); //bech nbadel lbackground vet
            tvMessage.setText("Bon voyage " + nom); //bech nhot message f zone mtee lmessage fil activite
        } else {
            fond.setBackgroundResource(R.drawable.fondrouge);
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        //waqt nenzel ala boutton nouveau voyageur
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(this, MainActivity.class); //aamlt intent jdid bech yraj3ami l activites 1
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);// bech nsaker les activites ili fil fouk lmainactivites lkol w  bech naam activites jdida fiha chy(formulaire vide)
            startActivity(retour); //bech narjaa lel activites 1
            finish();
        });
    }
}