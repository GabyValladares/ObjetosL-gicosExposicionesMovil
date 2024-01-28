package com.ggvc.practicaobjetoslogicosmoviliipa2023;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class validacion extends AppCompatActivity {
    EditText txtRenovacion, txtContaminacion, txtMatriculacion, txtMultas, txtTotalMatricula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion);


        txtRenovacion = findViewById(R.id.txt_renovacion);
        txtContaminacion = findViewById(R.id.txt_contaminacion);
        txtMatriculacion = findViewById(R.id.txt_ingresocedula);
        txtMultas = findViewById(R.id.txt_pagomultas);
        txtTotalMatricula = findViewById(R.id.txt_toalmatricula);

        Intent intent = getIntent();
        String numeroCedula = intent.getStringExtra("numeroCedula");
        String nombre = intent.getStringExtra("nombre");
        String numeroPlaca = intent.getStringExtra("numeroPlaca");
        String anioFabricacion = intent.getStringExtra("anioFabricacion");
        String color = intent.getStringExtra("color");
        String tipoVehiculo = intent.getStringExtra("tipoVehiculo");
        String valorVehiculo = intent.getStringExtra("valorVehiculo");
        String multas = intent.getStringExtra("multas");
        String marca = intent.getStringExtra("marca");

        TextView txtNombre = findViewById(R.id.txt_nombreCliente);
        TextView txtCedula = findViewById(R.id.txt_cedula33);

        txtNombre.setText(nombre);
        txtCedula.setText(numeroCedula);

        double renovacion = 0;
        if (numeroCedula != null && numeroCedula.startsWith("1") && numeroPlaca != null && numeroPlaca.contains("I")) {
            double sueldoBasico = 435;
            renovacion = 0.05 * sueldoBasico;
        }
        txtRenovacion.setText(String.valueOf(renovacion));

        double contaminacion = 0;
        if (anioFabricacion != null && !anioFabricacion.isEmpty() && Integer.parseInt(anioFabricacion) < 2010) {
            contaminacion = 0.02 * (2023 - Integer.parseInt(anioFabricacion));
        }
        txtContaminacion.setText(String.valueOf(contaminacion));

        double porcentajeMatriculacion = 0;

        if ("Toyota".equals(marca) && "Jeep".equals(tipoVehiculo)) {
            porcentajeMatriculacion = 0.08;
        } else if ("Toyota".equals(marca) && "Camioneta".equals(tipoVehiculo)) {
            porcentajeMatriculacion = 0.12;
        } else if ("Suzuki".equals(marca) && "Vitara".equals(tipoVehiculo)) {
            porcentajeMatriculacion = 0.10;
        } else if ("Suzuki".equals(marca) && "Automóvil".equals(tipoVehiculo)) {
            porcentajeMatriculacion = 0.09;
        }

        double matriculacion = porcentajeMatriculacion * Double.parseDouble(valorVehiculo);
        txtMatriculacion.setText(String.valueOf(matriculacion));

        // Lógica de multas
        double multasAmount = 0;
        boolean tieneMultas = Boolean.parseBoolean(multas);
        if (tieneMultas) {
            double sueldoBasico = 435;
            multasAmount = 0.25 * sueldoBasico;
        }
        txtMultas.setText(String.valueOf(multasAmount));

        double total = renovacion + contaminacion + matriculacion + multasAmount;
        txtTotalMatricula.setText(String.valueOf(total));

        // Configurar el botón de regreso
        Button btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar el layout del diálogo
                View dialogView = LayoutInflater.from(validacion.this).inflate(R.layout.dialog_rating, null);

                // Configurar el RatingBar
                RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);

                // Crear el AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(validacion.this);
                builder.setView(dialogView);
                builder.setTitle("Calificar");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtener la calificación del RatingBar
                        float calificacion = ratingBar.getRating();
                        // Poner la calificación en el Intent para pasarla de vuelta a la actividad anterior
                        Intent intent = new Intent();
                        intent.putExtra("calificacion", calificacion);
                        setResult(RESULT_OK, intent);
                        // Finalizar la actividad actual y volver a la actividad anterior
                        finish();
                    }
                });
                builder.setNegativeButton("Cancelar", null);

                // Mostrar el AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}