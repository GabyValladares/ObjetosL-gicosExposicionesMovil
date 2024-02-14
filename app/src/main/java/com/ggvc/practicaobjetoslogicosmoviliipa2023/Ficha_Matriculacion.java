package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class Ficha_Matriculacion extends AppCompatActivity {
    EditText edNombre, edCedula, edApellido, edColor, edCosto, edPlaca;
    Button btnProcesar, btnCalificar;
    ProgressBar progressBar;
    TextView txtProgreso;
    TextView tv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_matriculacion);
        tv = findViewById(R.id.txtCostof4);

        edNombre = findViewById(R.id.txtNombref2);
        edCedula = findViewById(R.id.txtCedulaf);
        edApellido = findViewById(R.id.txtApellidof);
        edColor = findViewById(R.id.txtColorf2);
        edCosto = findViewById(R.id.txtCostof4);
        edPlaca = findViewById(R.id.editTextText);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnCalificar = findViewById(R.id.btnCalificar);
        progressBar = findViewById(R.id.progreso);
        txtProgreso = findViewById(R.id.txtProgreso);

        edNombre.addTextChangedListener(watcher);
        edCedula.addTextChangedListener(watcher);
        edApellido.addTextChangedListener(watcher);
        edColor.addTextChangedListener(watcher);
        edCosto.addTextChangedListener(watcher);
        edPlaca.addTextChangedListener(watcher);

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar(view);
            }
        });

        btnCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ficha_Matriculacion.this, Flores_RaitingBar.class);
                startActivity(intent);
            }
        });
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            calcularYActualizarProgreso();
        }
    };

    public void procesar(View view) {
        calcularYActualizarProgreso();
    }

    public void ABRECALENDARIO(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(Ficha_Matriculacion.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                tv.setText(fecha);
            }
        }, dia, mes, anio);
        dpd.show();
    }

    private void calcularYActualizarProgreso() {
        String nombre = edNombre.getText().toString();
        String cedula = edCedula.getText().toString();
        String apellido = edApellido.getText().toString();
        String color = edColor.getText().toString();
        String costo = edCosto.getText().toString();
        String placa = edPlaca.getText().toString();

        int progreso = calcularProgreso(nombre, cedula, apellido, color, costo, placa);

        progressBar.setProgress(progreso);
        txtProgreso.setText("Progreso: " + progreso + "%");
    }

    private int calcularProgreso(String nombre, String cedula, String apellido, String color, String costo, String placa) {
        int camposLlenos = 0;

        if (!TextUtils.isEmpty(nombre)) camposLlenos++;
        if (!TextUtils.isEmpty(cedula)) camposLlenos++;
        if (!TextUtils.isEmpty(apellido)) camposLlenos++;
        if (!TextUtils.isEmpty(color)) camposLlenos++;
        if (!TextUtils.isEmpty(costo)) camposLlenos++;
        if (!TextUtils.isEmpty(placa)) camposLlenos++;

        return (int) ((camposLlenos / 6.0) * 100);
    }
}
