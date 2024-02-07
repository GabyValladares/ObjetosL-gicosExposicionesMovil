package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalificacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);



        // Recuperar el dato de nombre de la Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("SR/SRA:");

        // Obtener referencia a la TextView en CalificacionActivity
        TextView lblNombre2 = findViewById(R.id.lblNombre2);

        // Mostrar el dato de nombre en la TextView
        lblNombre2.setText("SR/SRA:" + nombre);

        Button btnAtras = findViewById(R.id.btnAtras2);
        Button btnPuntuar = findViewById(R.id.btnPuntuar);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la actividad anterior
            }
        });

        btnPuntuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar código para enviar la puntuación
                // Por ahora, simplemente regresa a la actividad anterior
                finish();
            }
        });
    }
}
