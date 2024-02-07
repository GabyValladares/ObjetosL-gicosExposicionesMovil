package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CalificacionServicioActivity extends AppCompatActivity {
    RatingBar rbServicio;
    TextView tvValor;
    Button btCalificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_servicio);
        rbServicio=findViewById(R.id.rtBServicio);
        btCalificar=findViewById(R.id.btnCalificar);
        tvValor=findViewById(R.id.txtCalificarTitulo);

    }

    public void contar(View view){
        String nombre=tvValor.getText().toString();
        float resultado=rbServicio.getRating();
        Toast.makeText(CalificacionServicioActivity.this,nombre+" ha sido calificado con :"+ resultado+ " estrellas",Toast.LENGTH_LONG).show();

    }
}