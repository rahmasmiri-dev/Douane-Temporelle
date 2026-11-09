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
    // bch ne5o les donne mt3i
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.RS_edit1);
        etNaissance = findViewById(R.id.RS_edit2);
        etDestination = findViewById(R.id.RS_edit3);
        rgMotif = findViewById(R.id.RS_Motif);
        btnInspecter = findViewById(R.id.RS_button);

        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecterProfil();
            }
        });
    }
    //bch na3ml verification 3la les donne ili 3andi
    private void inspecterProfil() {
        String nom = etNom.getText().toString().trim();
        String strNaissance = etNaissance.getText().toString().trim();
        String strDestination = etDestination.getText().toString().trim();

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

        int anneeNaissance;
        int anneeDestination;
        try {
            anneeNaissance = Integer.parseInt(strNaissance);
            anneeDestination = Integer.parseInt(strDestination);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Années invalides", Toast.LENGTH_SHORT).show();
            return;
        }
        // lezem les anne entre 1000 et 9999
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


        //recuperation motif
        String motif = recupererMotif();
        if (motif == null) {
            Toast.makeText(this, "Choisissez un motif de voyage", Toast.LENGTH_SHORT).show();
            return;
        }
        //creation mt3 intent
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        // les donne bch nb3athhom
        intent.putExtra(cles.nomVoyageur(), nom);
        intent.putExtra(cles.anneeNaissance(), anneeNaissance);
        intent.putExtra(cles.anneeDestination(), anneeDestination);
        intent.putExtra(cles.motif(), motif);

        startActivity(intent);
    }
    // 9a3da ndevelopper fil fct ili sta3maltha llrecuperation mt3 motif
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