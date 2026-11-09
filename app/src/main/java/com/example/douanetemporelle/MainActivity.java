package com.example.douanetemporelle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.RS_edit1);
        etNaissance = findViewById(R.id.RS_edit2);
        etDestination = findViewById(R.id.RS_edit3);
        rgMotif = findViewById(R.id.RS_Motif);
        btnInspecter = findViewById(R.id.RS_button);

        // 3 - عند الضغط على زر "Inspecter le profil"
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecterProfil();
            }
        });
    }


    private void inspecterProfil() {

        // bch na7i les espaces
        String nom = etNom.getText().toString().trim();
        String strNaissance = etNaissance.getText().toString().trim();
        String strDestination = etDestination.getText().toString().trim();

        // bch na3ml verfication 3ali les champs
        if (nom.isEmpty()) {
            etNom.setError("Nom obligatoire");
            etNom.requestFocus();
            return;
        }
        if (strNaissance.isEmpty()) {
            etNaissance.setError("Année obligatoire");
            etNaissance.requestFocus();
            return;
        }
        if (strDestination.isEmpty()) {
            etDestination.setError("Année obligatoire");
            etDestination.requestFocus();
            return;
        }

        // bch nrod les anne des entier
        int anneeNaissance = Integer.parseInt(strNaissance);
        int anneeDestination = Integer.parseInt(strDestination);

        // les anne entre 1000 et 9999
        if (anneeNaissance < 1000 || anneeNaissance > 9999) {
            etNaissance.setError("Année invalide (ex: 1990)");
            etNaissance.requestFocus();
            return;
        }
        if (anneeDestination < 1000 || anneeDestination > 9999) {
            etDestination.setError("Année invalide (ex: 2050)");
            etDestination.requestFocus();
            return;
        }


        // raison mt3 voyage
        String motif = recupererMotif();
        if (motif == null) {
            Toast.makeText(this, "Choisissez un motif de voyage", Toast.LENGTH_SHORT).show();
            return;
        }

        // bch na3ml creation mt3 intent
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // bch nzid 4 chiffre mil carte etudiant donc bch n3ayet l cle
        intent.putExtra(cles.nomVoyageur(), nom);
        intent.putExtra(cles.anneeNaissance(), anneeNaissance);
        intent.putExtra(cles.anneeDestination(), anneeDestination);
        intent.putExtra(cles.motif(), motif);

        // 12 - فتح النشاط الثاني
        startActivity(intent);
    }

    // recuperation mt3 motif
    private String recupererMotif() {
        int idChoisi = rgMotif.getCheckedRadioButtonId();

        if (idChoisi == R.id.RS_Tourisme) {
            return "Tourisme";
        } else if (idChoisi == R.id.RS_Modi) {
            return "Modification du passé";
        } else if (idChoisi == R.id.RS_Fuite) {
            return "Fuite";
        }
        return null;
    }
}