package com.example.soteriasecurityapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    public TextInputEditText etLoginEmail;
    public TextInputEditText etLoginPassword;
    public TextView tvRegisterHere, tvForgotPassword;
    public Button btnLogin;
    public ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public static User user;

    /**
     * création de l'interface pour le Login activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user = new User("username", "mdp", "usrnm");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//méthode findViewById permet d'associer les éléments du xml au code
        progressBar = findViewById(R.id.progressBarLogIn);
        etLoginEmail = findViewById(R.id.etLoginEmail);//création du TextView pour le email
        etLoginPassword = findViewById(R.id.etLoginPass);//création du TextView pour le mot de passe
        tvRegisterHere = findViewById(R.id.tvRegisterHere);//création du TextView si on ne s'est jamais enregistré
        btnLogin = findViewById(R.id.btnLogin);//création du Bouton pour le Login
        tvForgotPassword = findViewById(R.id.forgotPassword);
        mAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(view -> {
            loginUser(view);
        });

        tvRegisterHere.setOnClickListener(view -> {//événement permettant le changement d'activité lors du click sur le bouton(création d'un nouveau compte )
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

        });

        tvForgotPassword.setOnClickListener(view -> {//événement permettant le changement d'activité lors du click sur le bouton(activité lors de l'oubli du mot de passe)
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
            progressBar.setVisibility((View.INVISIBLE));
        });

        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Cette méthode permet la connexion de l'utilisateur à l'application
     * en s'assurant que le format de ce que rentre l'utilisateur
     * soient corrects
     */
    private void loginUser(View view) {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) { //Empêche l'utilisateur de ne pas remplir la case d'email
            etLoginEmail.setError("Veuillez entrer un email");
            etLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) { //Empêche l'utilisateur de ne pas mettre de mot de passe
            etLoginPassword.setError("Veuillez entrer un mot de passe");
            etLoginPassword.requestFocus();
        } else {


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();

                        user.setEmail(email);
                        getId();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                , ActivityOptions.makeClipRevealAnimation
                                        (view,0,0,1,1).toBundle());

                    } else {
                        Toast.makeText(LoginActivity.this, "Erreur de connexion: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    /**
     * Cette méthode retourne l'ID de l'utilisateur qui s'est connecté
     */
    public void getId() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        reference.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                String tabId[];
                tabId = user.getEmail().split("@");
                user.setId(tabId[0]);

            }

        });


    }


}
