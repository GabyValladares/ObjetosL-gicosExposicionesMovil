package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioVehiculo extends AppCompatActivity {

    private EditText txtCedula, txtNombre, txtPlaca, txtFbVehiculo, txtMarca, txtColor, txtTipo, txtValor, txtMultas;
    private Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_vehiculo);

        txtCedula = findViewById(R.id.txtcedula);
        txtNombre = findViewById(R.id.txtnombre);
        txtPlaca = findViewById(R.id.txtplaca);
        txtFbVehiculo = findViewById(R.id.txtfbvehiculo);
        txtMarca = findViewById(R.id.txtmarca);
        txtColor = findViewById(R.id.txtcolor);
        txtTipo = findViewById(R.id.txttipo);
        txtValor = findViewById(R.id.txtvalor);
        txtMultas = findViewById(R.id.txtmultas);
        btnPagar = findViewById(R.id.btnconsultar);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cedula = txtCedula.getText().toString();
                String nombre = txtNombre.getText().toString();
                String placa = txtPlaca.getText().toString();
                String fbVehiculo = txtFbVehiculo.getText().toString();
                String marca = txtMarca.getText().toString();
                String color = txtColor.getText().toString();
                String tipo = txtTipo.getText().toString();
                String valor = txtValor.getText().toString();
                String multas = txtMultas.getText().toString();


               // Intent intent = new Intent(FormularioVehiculo.this, Pago.class);
                //intent.putExtra("cedula", cedula);
                //intent.putExtra("nombre", nombre);
                //intent.putExtra("placa", placa);
                //intent.putExtra("fbVehiculo", fbVehiculo);
                //intent.putExtra("marca", marca);
                //intent.putExtra("color", color);
                //intent.putExtra("tipo", tipo);
                //intent.putExtra("valor", valor);
                //intent.putExtra("multas", multas);
                //startActivity(intent);
            }
        });
    }
}
