package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        // Recuperar los datos de la Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String cedula = intent.getStringExtra("CEDULA");
        String placa = intent.getStringExtra("PLACA");
        String anio = intent.getStringExtra("ANIO"); // Obtener el año
        boolean tieneMultas = intent.getBooleanExtra("TIENE_MULTAS", false);


// Obtener referencias a las TextViews
        TextView lblNombre = findViewById(R.id.lblNombre);
        TextView lblCedula = findViewById(R.id.lblCedula);
        TextView lblPlaca = findViewById(R.id.lblPlaca);
        TextView lblAnio = findViewById(R.id.lblContaminacion); // Referencia a la TextView del año
        TextView lblMultas = findViewById(R.id.lblMxM);


// Mostrar los datos en las TextViews
        lblNombre.setText("NOMBRE: " + nombre);
        lblCedula.setText("CEDULA: " + cedula);
        lblPlaca.setText("PLACA: " + placa);
        lblAnio.setText("AÑO: " + anio); // Mostrar el año en la TextView correspondiente

        if (tieneMultas) {
            lblMultas.setText("SI");
        } else {
            lblMultas.setText("NO");
        }




        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnPuntuacion = findViewById(R.id.btnPuntuacion);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la actividad anterior
            }
        });

        btnPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear una nueva Intent
                Intent intent = new Intent(ActivityResultados.this, CalificacionActivity.class);

                // Pasar el dato de nombre a la siguiente actividad
                intent.putExtra("SR/SRA:", nombre);

                // Iniciar la actividad de calificación
                startActivity(intent);
            }
        });

    }
}
