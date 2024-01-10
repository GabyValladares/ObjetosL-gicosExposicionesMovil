package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText txtCedula, txtNombre, txtPlaca, txtFbVehiculo, txtMarca, txtColor, txtTipo, txtValor, txtMultas;

    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtFbVehiculo = findViewById(R.id.txtAnio);
        txtMarca = findViewById(R.id.txtMarca);
        txtColor = findViewById(R.id.txtColor);
        txtTipo = findViewById(R.id.txtTipoV);
        txtValor = findViewById(R.id.TXTvALOR);
        txtMultas = findViewById(R.id.txtMultas);
        btnEnviar = findViewById(R.id.btnCalcular);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = txtCedula.getText().toString();
                String nombre = txtNombre.getText().toString();
                String placa = txtPlaca.getText().toString();
                String fbVehiculo = txtFbVehiculo.getText().toString();
                String marca = txtMarca.getText().toString();
                String color = txtColor.getText().toString();
                String tipo = txtTipo.getText().toString();
                String valor = txtValor.getText().toString();
                String multas = txtMultas.getText().toString();

                Intent intent = new Intent(MainActivity.this, Pago_Activity.class);
                intent.putExtra("cedula", cedula);
                intent.putExtra("nombre", nombre);
                intent.putExtra("placa", placa);
                intent.putExtra("fbVehiculo", fbVehiculo);
                intent.putExtra("marca", marca);
                intent.putExtra("color", color);
                intent.putExtra("tipo", tipo);
                intent.putExtra("valor", valor);
                intent.putExtra("multas", multas);
                startActivity(intent);
            }
        });

    }
    }