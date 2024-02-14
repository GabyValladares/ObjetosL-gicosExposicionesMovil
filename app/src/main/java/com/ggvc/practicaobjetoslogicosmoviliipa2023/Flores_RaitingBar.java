package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Flores_RaitingBar extends AppCompatActivity {
 TextView tvName1;
 Button btnRate1;
 RatingBar rbStar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flores_raiting_bar);
        tvName1= findViewById(R.id.textV);
        btnRate1=findViewById(R.id.btnIniciar);
        rbStar1=findViewById(R.id.estrellas);

    }
    public void contar1 (View v){
        String nombre=tvName1.getText().toString();
        float resultado = rbStar1.getRating();
        Toast.makeText(Flores_RaitingBar.this,nombre +"ha sido calificado" +resultado+ "estrellas",Toast.LENGTH_SHORT).show();

    }
}