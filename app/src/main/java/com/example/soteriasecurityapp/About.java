package com.example.soteriasecurityapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    /**
     * Méthode qui sert à la création d'une activité soit d'une nouvelle page
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);// appel au fichier xml pour la création de l'interface graphique
    }
}