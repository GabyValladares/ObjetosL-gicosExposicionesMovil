package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class calendar extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        tv = findViewById(R.id.tv);
    }

    public void AbrirCalendario(View view) {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                // Assuming 'tv' is a TextView reference
                tv.setText(fecha);
            }
        }, anio, mes, dia);
        dpd.show();
    }

}