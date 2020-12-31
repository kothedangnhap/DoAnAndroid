package com.example.myapplication;
import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.SignUp;
import com.example.myapplication.Signin;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnSingIn,btnSingUp;
    TextView txtSlogan;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

//
    }

    private void addEvents() {

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "please waiting...", Toast.LENGTH_SHORT).show();
                Intent SignUp = new Intent(MainActivity.this, com.example.myapplication.SignUp.class);
                startActivity(SignUp);
            }
        });
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignIn = new Intent(MainActivity.this, Signin.class);
                startActivity(SignIn);
            }
        });
    }

    private void addControls() {
        btnSingIn = findViewById(R.id.btnSignIn);
        btnSingUp = findViewById(R.id.btnSignup);
        txtSlogan = findViewById(R.id.txtSlogan);

    }
}