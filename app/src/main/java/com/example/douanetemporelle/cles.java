package com.example.douanetemporelle;

public class cles {

    public static final String MATRICULE = "1234";
    public static final String NOM = "NOM_VOYAGEUR";
    public static final String VERDICT = "VERDICT";

    // fonction cle
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }

    public static String key(String matricule, String nom) {
        return cle(matricule, nom);
    }

    public static String nomVoyageur() {
        return cle(MATRICULE, "NOM_VOYAGEUR");
    }

    public static String anneeNaissance() {
        return cle(MATRICULE, "ANNEE_NAISSANCE");
    }

    public static String anneeDestination() {
        return cle(MATRICULE, "ANNEE_DESTINATION");
    }

    public static String motif() {
        return cle(MATRICULE, "MOTIF");
    }

    public static String verdict() {
        return cle(MATRICULE, "VERDICT");
    }
}