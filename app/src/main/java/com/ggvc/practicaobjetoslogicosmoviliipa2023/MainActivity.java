package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edNombre;
    Button btnProcesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNombre = findViewById(R.id.txtNombre);
        btnProcesar = findViewById(R.id.btnProcesar);

    }

    public void Procesar() {
        Bundle datos = new Bundle();

        datos.putString("nombre", edNombre.getText().toString());

        Intent i = new Intent(this, ResultadoFV.class);
    }
}

