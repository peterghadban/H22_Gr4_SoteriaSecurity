package com.example.soteriasecurityapp;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    public TextInputEditText etRegEmail;
    public TextInputEditText etRegPassword;
    public TextView tvLoginHere;
    public Button btnRegister;
    public ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public FirebaseDatabase zDatabase;
    public DatabaseReference zRef;
    public TextInputEditText etRegId;
    public String email;
    public String password;
    public String id;
    public static User user;
    public static String id1, email1;


    public void setPassword(String password) {
        this.password = password;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //On relie toutes les variables de la classe à celles du xml
        progressBar = findViewById(R.id.progressBarRegister);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPass);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);
        zRef = FirebaseDatabase.getInstance().getReference();
        etRegId = findViewById(R.id.etRegId);
        mAuth = FirebaseAuth.getInstance();


        id1 = id;
        email1 = email;


        tvLoginHere.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, LoginActivity.class), ActivityOptions.
                    makeClipRevealAnimation(view, 0, 0, 1, 1).toBundle());
        });

        btnRegister.setOnClickListener(view -> {
            createUser(view); //Création de l'utilisateur lorsqu'on appuie sur le bouton
        });


        progressBar.setVisibility(View.INVISIBLE);

    }

    /**
     * Cette méthode crée un utilisateur en demandant un email, id et mot de passe
     * @param view
     */
    public void createUser(View view) {
        email = etRegEmail.getText().toString();
        password = etRegPassword.getText().toString();
        id = etRegId.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else {

            user = new User(email, password, id);


            zRef.child("users").child(id).setValue(user);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {


                        zDatabase.getReferenceFromUrl(
                                "https://fir-server-d06bf-default-rtdb.firebaseio.com/")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "User has been registered",
                                            Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Failed to register user",
                                            Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });


                        startActivity(new Intent
                                        (RegisterActivity.this, LoginActivity.class),
                                ActivityOptions.makeClipRevealAnimation(view, 0, 0, 1, 1).toBundle());
                        //.putExtra("Info", user)
                        //progressBar.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to register user" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

}

