package com.example.ejercicio8_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicio8_firebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private String nombre, email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance(); //Se inicializa

        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = binding.nombre.getText().toString().trim();
                email = binding.correo.getText().toString().trim();
                pass = binding.password.getText().toString().trim();

                if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Hay campos vacíos", Toast.LENGTH_LONG).show();
                }else{
                    registro(email, pass);
                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = binding.nombre.getText().toString().trim();
                email = binding.correo.getText().toString().trim();
                pass = binding.password.getText().toString().trim();

                if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Hay campos vacíos", Toast.LENGTH_LONG).show();
                }else{
                    login(email, pass);
                }
            }
        });
    }

    private void login(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "SESIÓN INICIADA", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.putExtra("name", nombre);
                    intent.putExtra("correo", email);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this, "USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void registro(String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();
                }else {
                    
                    if (task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(MainActivity.this, "USUARIO YA EXISTE", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "El usuario no se puede registrar", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });



    }
}