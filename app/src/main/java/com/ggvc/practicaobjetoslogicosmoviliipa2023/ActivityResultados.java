package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import java.util.Calendar;

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
        String marca = intent.getStringExtra("MARCA");
        String tipo = intent.getStringExtra("TIPO");


// Obtener referencias a las TextViews
        TextView lblNombre = findViewById(R.id.lblNombre);
        TextView lblCedula = findViewById(R.id.lblCedula);
        TextView lblPlaca = findViewById(R.id.lblPlaca);
        TextView lblAnio = findViewById(R.id.lblContaminacion); // Referencia a la TextView del año
        TextView lblMultas = findViewById(R.id.lblMxM);
        TextView lblMarcaTipo = findViewById(R.id.lblMarcaTipo);


// Mostrar los datos en las TextViews
        lblNombre.setText("NOMBRE: " + nombre);
        lblCedula.setText("CEDULA: " + cedula);
        lblPlaca.setText("PLACA: " + placa);
        lblAnio.setText("AÑO: " + anio);
        if (tieneMultas) {
            lblMultas.setText("SI");
        } else {
            lblMultas.setText("NO");
        }
        lblMarcaTipo.setText("Marca y Tipo: " + marca + " - " + tipo);


        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnPuntuacion = findViewById(R.id.btnPuntuacion);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la actividad anterior
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el número de cédula, la placa y el año de fabricación
                String cedula = getIntent().getStringExtra("CEDULA");
                String placa = getIntent().getStringExtra("PLACA");
                String anio = getIntent().getStringExtra("ANIO");

                // Convertir el año de fabricación a entero
                int anioFabricacion = Integer.parseInt(anio);

                // Obtener el año actual
                Calendar calendar = Calendar.getInstance();
                int anioActual = calendar.get(Calendar.YEAR);

                // Calcular la multa por contaminación
                double multaContaminacion = 0;

                // Verificar si el año de fabricación es menor a 2010
                if (anioFabricacion < 2010) {
                    // Calcular la cantidad de años de contaminación
                    int aniosContaminacion = anioActual - anioFabricacion;

                    // Calcular la multa por contaminación (2% por cada año de contaminación)
                    multaContaminacion = 0.02 * aniosContaminacion; // 2% por cada año
                }

                // Mostrar el resultado de la multa por contaminación en la TextView correspondiente
                TextView lblMContam = findViewById(R.id.lblMContam);
                lblMContam.setText(String.format("%.2f", multaContaminacion)); // Mostrar el resultado con dos decimales

                // Verificar la condición y calcular el resultado
                double resultado = 0;
                if (cedula.startsWith("1") && placa.contains("I")) {
                    // Si la cédula comienza con 1 y la placa contiene la letra I
                    resultado = 0.05 * 435; // 5% del sueldo básico ($435)
                }

                // Mostrar el resultado en la TextView correspondiente
                TextView lblMRenovPlaca = findViewById(R.id.lblMRenovPlaca);
                lblMRenovPlaca.setText(String.format("%.2f", resultado)); // Mostrar el resultado con dos decimales
            }
        });



// EL BOTON PARA LA SIGUIENTE ACTIVIDAD UWU
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
