package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
        double valorVehiculo = intent.getDoubleExtra("VALOR_VEHICULO", 0);

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
        lblMultas.setText(tieneMultas ? "SI" : "NO");
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
                // Obtener el año de fabricación
                int anioFabricacion = Integer.parseInt(anio);

                // Obtener el año actual
                Calendar calendar = Calendar.getInstance();
                int anioActual = calendar.get(Calendar.YEAR);

                // Calcular la multa por contaminación
                double multaContaminacion = 0;
                if (anioFabricacion < 2010) {
                    int aniosContaminacion = anioActual - anioFabricacion;
                    multaContaminacion = 0.02 * aniosContaminacion; // 2% por cada año
                }

                // Mostrar el resultado de la multa por contaminación
                TextView lblMContam = findViewById(R.id.lblMContam);
                lblMContam.setText(String.format("%.2f", multaContaminacion));

                // Calcular el valor de matriculación
                double valorMatriculacion = calcularValorMatriculacion(marca, tipo, valorVehiculo);

                // Mostrar el resultado de la matriculación
                TextView lblValorMatric = findViewById(R.id.lblValorMatric);
                lblValorMatric.setText(String.format("%.2f", valorMatriculacion));

                // Calcular la multa por multas pendientes
                double multaPorMultas = tieneMultas ? 0.25 * 435 : 0;

                // Mostrar el resultado de la multa por multas pendientes
                TextView lblMxMultas = findViewById(R.id.lblMxMultas);
                lblMxMultas.setText(String.format("%.2f", multaPorMultas));

                // Calcular el total a pagar
                double totalPagar = valorMatriculacion + multaContaminacion + multaPorMultas;

                // Mostrar el resultado total a pagar
                TextView lblValorMatric2 = findViewById(R.id.lblValorMatric2);
                lblValorMatric2.setText(String.format("%.2f", totalPagar));
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

    private double calcularValorMatriculacion(String marca, String tipo, double valorVehiculo) {
        double valorMatriculacion = 0;
        if (marca.equals("Toyota")) {
            if (tipo.equals("Jeep")) {
                valorMatriculacion = 0.08;
            } else if (tipo.equals("Camioneta")) {
                valorMatriculacion = 0.12;
            }
        } else if (marca.equals("Suzuki")) {
            if (tipo.equals("Vitara")) {
                valorMatriculacion = 0.10;
            } else if (tipo.equals("Automóvil")) {
                valorMatriculacion = 0.09;
            }
        }
        valorMatriculacion *= valorVehiculo;
        return valorMatriculacion;
    }
}
