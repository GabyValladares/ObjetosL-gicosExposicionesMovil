package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    String fecha1;
    boolean Multa;
    EditText edNombre, edCedula, edPlaca, edCosto;
    Button btnProcesar, btnColor;
    Spinner sprTipo, sprMarca;
    ToggleButton tbtnMultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNombre = findViewById(R.id.txtNombre);
        edCedula = findViewById(R.id.txtCedula);
        edPlaca = findViewById(R.id.txtPlaca);
        edCosto = findViewById(R.id.txtCosto);
        btnColor = findViewById(R.id.btnColor);
        btnProcesar = findViewById(R.id.btnProcesar);
        tbtnMultas = findViewById(R.id.tbtnMultas);
        sprTipo = findViewById(R.id.sprTipo);
        sprMarca = findViewById(R.id.sprMarca);

        String[] marcas = getResources().getStringArray(R.array.Marcas);
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, marcas);
        adapterMarcas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String[] Tipos = getResources().getStringArray(R.array.Tipo);
        ArrayAdapter<String> adapterTipos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Tipos);
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sprMarca.setAdapter(adapterMarcas);
        sprTipo.setAdapter(adapterTipos);

        tbtnMultas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Multa= isChecked;
            }
        });
    }

    public void Procesar() {
        Bundle datos = new Bundle();

        datos.putString("nombre", edNombre.getText().toString());
        datos.putString("cedula", edCedula.getText().toString());
        datos.putString("placa", edPlaca.getText().toString());
        datos.putString("costo", edCosto.getText().toString());
        datos.putString("anio", fecha1);
        datos.putBoolean("multa", Multa);
        datos.putString("tipo",sprTipo.getSelectedItem().toString());
        datos.putString("marca", sprMarca.getSelectedItem().toString());


        Intent i = new Intent(this, ResultadoFV.class);
        i.putExtras(datos);
        startActivity(i);
    }

    public void Calendario(View view) {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                fecha1 = fecha;
            }
        }, anio, mes, dia);
        dpd.show();
    }
}

