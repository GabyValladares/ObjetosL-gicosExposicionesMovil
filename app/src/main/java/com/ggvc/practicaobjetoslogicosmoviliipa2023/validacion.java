package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class validacion extends AppCompatActivity {
    EditText txtRenovacion, txtContaminacion, txtMatriculacion, txtMultas, txt_toalmatricula, txtNombre, txtCedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion);

        txtRenovacion = findViewById(R.id.txt_renovacion);
        txtContaminacion = findViewById(R.id.txt_contaminacion);
        txtMatriculacion = findViewById(R.id.txt_ingresocedula);
        txtMultas = findViewById(R.id.txt_pagomultas);
        txt_toalmatricula = findViewById(R.id.txt_toalmatricula);
        txtNombre = findViewById(R.id.txt_nombreCliente);
        txtCedula = findViewById(R.id.txt_cedula33);


        Intent intent = getIntent();
        String numeroCedula = Objects.requireNonNull(intent.getStringExtra("numeroCedula"));
        String nombre = Objects.requireNonNull(intent.getStringExtra("nombre"));
        String numeroPlaca = Objects.requireNonNull(intent.getStringExtra("numeroPlaca"));
        String anioFabricacion = Objects.requireNonNull(intent.getStringExtra("anioFabricacion"));
        String color = Objects.requireNonNull(intent.getStringExtra("color"));
        String tipoVehiculo = Objects.requireNonNull(intent.getStringExtra("tipoVehiculo"));
        String valorVehiculo = Objects.requireNonNull(intent.getStringExtra("valorVehiculo"));
        String multas = Objects.requireNonNull(intent.getStringExtra("multas"));
        String marca = Objects.requireNonNull(intent.getStringExtra("marca"));

        txtNombre.setText(nombre);
        txtCedula.setText(numeroCedula);

        double renovacion = 0;
        if (numeroCedula.startsWith("1") && numeroPlaca.contains("I")) {
            double sueldoBasico = 435;
            renovacion = 0.05 * sueldoBasico;
        }
        txtRenovacion.setText(String.valueOf(renovacion));

        double contaminacion = 0;
        if (!anioFabricacion.isEmpty() && Integer.parseInt(anioFabricacion) < 2010) {
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

        double multasAmount = 0;
        boolean tieneMultas = Boolean.parseBoolean(multas);
        if (tieneMultas) {
            double sueldoBasico = 435;
            multasAmount = 0.25 * sueldoBasico;
        }
        txtMultas.setText(String.valueOf(multasAmount));

        double total = renovacion + contaminacion + matriculacion + multasAmount;
        txt_toalmatricula.setText(String.valueOf(total));

        Button btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = LayoutInflater.from(validacion.this).inflate(R.layout.dialog_rating, null);
                RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
                AlertDialog.Builder builder = new AlertDialog.Builder(validacion.this);
                builder.setView(dialogView);
                builder.setTitle("Calificar");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float calificacion = ratingBar.getRating();
                        Intent intent = new Intent();
                        intent.putExtra("calificacion", calificacion);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los EditText
                String nombre = txtNombre.getText().toString();
                String cedula = txtCedula.getText().toString();
                String pagarMatricula = txtMatriculacion.getText().toString(); // Asegúrate de obtener el valor correcto de renovación
                String pagarMultas = txtMultas.getText().toString();
                String matriculacion = txtRenovacion.getText().toString();
                String pagoContaminacion = txtContaminacion.getText().toString();
                String total = txt_toalmatricula.getText().toString(); // Usar el nombre correcto del EditText


                String url = "http://192.168.101.8/movil/webapi.php?op=ingresarD" +
                        "&nom=" + nombre +
                        "&ced=" + cedula +
                        "&pm=" + pagarMatricula +
                        "&pmultas=" + pagarMultas +
                        "&matr=" + matriculacion +
                        "&pagoCo=" + pagoContaminacion +
                        "&total=" + total;


                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(validacion.this, "Error al enviar los datos", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(validacion.this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(validacion.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}
