package com.example.ejercicio8_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ejercicio8_firebase.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String nombre = bundle.getString("name");
            String correo = bundle.getString("correo");

            binding.txtnombre.setText("Bienvenido " + nombre);
            binding.txtcorreo.setText("Tu correo es " + correo);
        }
    }
}