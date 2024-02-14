package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Pago_Activity extends AppCompatActivity {

    String fecha = "";
    EditText txtPlaca, txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        EditText txtCedula = findViewById(R.id.edCedula);
        txtCedula.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText txtNombres = findViewById(R.id.edNombres);
        EditText txtPlaca = findViewById(R.id.edPlaca);
        TextView txtAnioFab = findViewById(R.id.tvanio);
        txtAnioFab.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText txtvalor = findViewById(R.id.edValor);
        txtvalor.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText txtmultas = findViewById(R.id.edMultas);
        txtmultas.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button btncalcular = findViewById(R.id.btCalcula);
        Button btCalendar = findViewById(R.id.btnCalendar);

        Spinner marcas = (Spinner) findViewById(R.id.cbxmarca);
        String[] opMarcas = {
                "TOYOTA",
                "MAZDA",
                "FORD"
        };
        ArrayAdapter<String> marcasVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opMarcas);
        marcas.setAdapter(marcasVehiculo);

        Spinner colores = (Spinner) findViewById(R.id.cbxcolor);
        String[] opColor = {
                "AZUL",
                "BLANCO",
                "NEGRO"
        };
        ArrayAdapter<String> coloresVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opColor);
        colores.setAdapter(coloresVehiculo);

        Spinner tipos = (Spinner) findViewById(R.id.cbxTipo);
        String[] opTipo = {
                "JEEP",
                "CAMIONETA",
                "AUTOMOVIL"
        };
        ArrayAdapter<String> tiposVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opTipo);
        tipos.setAdapter(tiposVehiculo);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pago_Activity.this, MainActivity.class);
                Bundle datos = new Bundle();
                String cedulaP = txtCedula.getText().toString();
                String nombresP = txtNombres.getText().toString();
                String placaV = txtPlaca.getText().toString();
                String anioV = txtAnioFab.getText().toString();
                String marcaV = marcas.getSelectedItem().toString();
                String colorV = colores.getSelectedItem().toString();
                String tipoV = tipos.getSelectedItem().toString();
                String valorV = txtvalor.getText().toString();
                String multasV = txtmultas.getText().toString();

                double valorVe = Double.parseDouble(valorV);

                BDHelper admin = new BDHelper(Pago_Activity.this, "fichaVehiculo.db", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String placa = placaV;
                String color = colorV;
                String marca = marcaV;
                String tipo = tipoV;
                Double valorv = valorVe;
                if (!placaV.isEmpty() && !color.isEmpty() && !marca.isEmpty() && !tipo.isEmpty()) {
                    ContentValues datosVehiculo = new ContentValues();
                    datosVehiculo.put("vhe_placa", placa);
                    datosVehiculo.put("vhe_color", color);
                    datosVehiculo.put("vhe_marca", marca);
                    datosVehiculo.put("vhe_tipo", tipo);
                    datosVehiculo.put("vhe_valor", valorv);

                    bd.insert("tblVehiculos", null, datosVehiculo);
                    Toast.makeText(Pago_Activity.this, "VEHICULO REGISTRADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Pago_Activity.this, "VEHICULO NO REGISTRADO", Toast.LENGTH_LONG).show();
                }

            }
        });

        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(Pago_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        txtAnioFab.setText(fecha);
                    }
                }, dia, mes, anio);
                dpd.show();
            }
        });
    }
}

