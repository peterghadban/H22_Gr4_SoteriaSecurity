package com.example.soteriasecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Account extends AppCompatActivity {

    public Button btnReset;
    public TextView id;
    public TextView userEmail;



    @SuppressLint("SetTextI18n")
    @Override
    /**
     * méthode de création de l'activité pour le bouton account permettant un changement de mot de passe
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        id = findViewById(R.id.idAccount);
        userEmail = findViewById(R.id.emailAccount);
        btnReset = findViewById(R.id.passReset);

        userEmail.setText("Email: "+LoginActivity.user.getEmail());
        id.setText("ID: " + LoginActivity.user.getId());
        id.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        userEmail.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnReset.setOnClickListener(view -> {//événement servant à la réinitialisation du mot de passe lorsqu'on click sur le bouton
            // progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(Account.this, PasswordResetActivity.class));//class Intent appelé pour engendrer une activité sur l'écran
        });


    }
}