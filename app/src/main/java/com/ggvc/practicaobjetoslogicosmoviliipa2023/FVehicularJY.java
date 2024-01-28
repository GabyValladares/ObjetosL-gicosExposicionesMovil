package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FVehicularJY extends AppCompatActivity {

    EditText edNombre, edCedulas;
    Button btnProcesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvehicular_jy);

        edNombre = findViewById(R.id.txtNombre);
        edCedulas = findViewById(R.id.txtCedula);
        btnProcesar = findViewById(R.id.btnGuardar);

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar(view);
            }
        });
    }

    public void procesar(View view) {
        String nombre = edNombre.getText().toString();
        String cedula = edCedulas.getText().toString();

        if (!nombre.isEmpty() && cedula.length() == 10) {
            // El nombre no está vacío y la cédula tiene 10 dígitos
            Toast.makeText(FVehicularJY.this, "Datos válidos", Toast.LENGTH_SHORT).show();

            // Puedes continuar con la lógica de procesamiento aquí si es necesario
        } else {
            // Mostrar un mensaje de error si el nombre está vacío o la cédula no tiene 10 dígitos
            Toast.makeText(FVehicularJY.this, "Por favor, ingrese datos válidos", Toast.LENGTH_SHORT).show();
        }
    }
}