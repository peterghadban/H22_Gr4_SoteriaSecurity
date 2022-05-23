package com.example.soteriasecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText username, password, repassword;
    Button signUp, signIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        username.setOnClickListener(this);
        password = findViewById(R.id.password);
        password.setOnClickListener(this);
        repassword = findViewById(R.id.repassword);
        repassword.setOnClickListener(this);
        signUp = findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        signIn = findViewById(R.id.signin);
        signIn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.signin:
                startActivity(new Intent(this,LoginActivity.class));

        }
    }
}
