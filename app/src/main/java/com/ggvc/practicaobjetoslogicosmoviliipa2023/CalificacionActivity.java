package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalificacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

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
