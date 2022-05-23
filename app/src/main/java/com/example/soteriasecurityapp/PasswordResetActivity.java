package com.example.soteriasecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class PasswordResetActivity extends AppCompatActivity {

    public TextInputEditText etLoginEmail;
    public Button btnLogin, sendEmail;
    public ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public TextView logIn;

    /**
     *création de l'interface pour la réinitialisation du mot de passe
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);//appel au fichier xml
        logIn = findViewById(R.id.tvLoginHere);
        progressBar = findViewById(R.id.progressBarReset);
        etLoginEmail = findViewById(R.id.email);
        sendEmail = findViewById(R.id.sendEmail);

        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(view -> {
            startActivity(new Intent(PasswordResetActivity.this,LoginActivity.class));
        });

        sendEmail.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            resetPass();//appel à la méthode resetPass pour changer le mot de passe
        });
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Méthode permettant un changement de mot de passe
     */
    private void resetPass() {

        String email = etLoginEmail.getText().toString().trim();

        if (email.isEmpty()) {//vérification pour savoir si un email à été écrit
            etLoginEmail.setError("Email est requis");
            etLoginEmail.requestFocus();

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {//fonction qui s'assure que le email entré est valide
            etLoginEmail.setError("S'il vous plait, entrez un email valide");
            etLoginEmail.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {//mise à jour du nouveau mot de passe dans la base de donnée
            /**
             * Méthode qui sera engendrée lorsque la tâche précédente est complétée soit l'envoie des informations au FireBase
             * @param task mise à jour des données dans FireBase
             */
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(PasswordResetActivity.this,
                            "Check your email to reset password",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(PasswordResetActivity.this, "Try again",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        progressBar.setVisibility(View.INVISIBLE);
    }
}