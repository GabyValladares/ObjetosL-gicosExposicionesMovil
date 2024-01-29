package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FVehicularJY extends AppCompatActivity {

    EditText edNombre, edCedulas;
    Button btnProcesar, btnCalendario;
    DatePicker datePicker;
    TextView edFecha; // Cambiado de EditText a TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvehicular_jy);

        edNombre = findViewById(R.id.txtNombre);
        edCedulas = findViewById(R.id.txtCedula);
        btnProcesar = findViewById(R.id.btnGuardar);
        btnCalendario = findViewById(R.id.btnCalendario);
       // datePicker = findViewById(R.id.datePicker);
        edFecha = findViewById(R.id.txtFecha); // Actualizado

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar(view);
            }
        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                FVehicularJY.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        edFecha.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void procesar(View view) {
        String nombre = edNombre.getText().toString();
        String cedula = edCedulas.getText().toString();
        String fecha = edFecha.getText().toString();
        boolean tieneMultas = ((Switch) findViewById(R.id.bntMultas)).isChecked();

        if (!nombre.isEmpty() && isNumeric(cedula) && !fecha.isEmpty()) {
            // Campos requeridos están llenos
            // Puedes ajustar el progreso del ProgressBar aquí
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(60);

            // Puedes utilizar la variable tieneMultas en tu lógica según sea necesario

            Intent intent = new Intent(FVehicularJY.this, ActivityResultados.class);
            startActivity(intent);
        } else {
            // Mostrar un mensaje de error si el nombre está vacío o la cédula no es numérica
            Toast.makeText(FVehicularJY.this, "Por favor, ingrese datos válidos", Toast.LENGTH_SHORT).show();
        }
    }

    // Función auxiliar para verificar si una cadena es numérica
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
