package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class Pago_Activity extends AppCompatActivity {
    private TextView txtCancelar;
    private Button btnVolver;
    private double sueldoBasico;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        txtCancelar = findViewById(R.id.txtCancelar);
        btnVolver = findViewById(R.id.btnVolver);

        Intent intent = getIntent();
        if (intent != null) {
            String cedula = intent.getStringExtra("cedula");
            String placa = intent.getStringExtra("placa");
            String fbVehiculo = intent.getStringExtra("fbVehiculo");
            String marca = intent.getStringExtra("marca");
            String tipo = intent.getStringExtra("tipo");
            String valor = intent.getStringExtra("valor");
            String multas = intent.getStringExtra("multas");

            double sueldoBasico = 435;
            double importePlacas = (cedula.startsWith("1") && placa.startsWith("I")) ? sueldoBasico * 0.05 : 0;
            double multaContaminacion = MultaContaminacion(fbVehiculo);
            double valorMatriculacion = ValorMatriculacion(marca, tipo, valor, sueldoBasico, multas);

            double totalPagar = importePlacas + multaContaminacion + valorMatriculacion;

            String resultado = "Importe por renovación de placas: $" + importePlacas + "\n" +
                    "Multa por contaminación: $" + multaContaminacion + "\n" +
                    "Valor de matriculación: $" + valorMatriculacion + "\n" +
                    "Total a pagar: $" + totalPagar;

            txtCancelar.setText(resultado);

            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    private double MultaContaminacion(String fbVehiculo) {
        int anioFabricacion = Integer.parseInt(fbVehiculo);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        int aniosContaminacion = anioActual - anioFabricacion;

        return (aniosContaminacion > 0 && anioFabricacion < 2010) ? sueldoBasico * 0.02 * aniosContaminacion : 0;
    }

    private double ValorMatriculacion(String marca, String tipo, String valor, double sueldoBasico, String multas) {
        double valorVehiculo = Double.parseDouble(valor);
        double valorMatriculacion = 0;

        if (marca.equalsIgnoreCase("Toyota")) {
            valorMatriculacion = (tipo.equalsIgnoreCase("Jeep")) ? valorVehiculo * 0.08 :
                    (tipo.equalsIgnoreCase("Camioneta")) ? valorVehiculo * 0.12 : 0;
        } else if (marca.equalsIgnoreCase("Suzuki")) {
            valorMatriculacion = (tipo.equalsIgnoreCase("Vitara")) ? valorVehiculo * 0.10 :
                    (tipo.equalsIgnoreCase("Automóvil")) ? valorVehiculo * 0.09 : 0;
        }

        return (multas.equalsIgnoreCase("si")) ? valorMatriculacion + sueldoBasico * 0.25 : valorMatriculacion;
    }

}