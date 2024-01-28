package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class floRating extends AppCompatActivity {

    Button btnRatea1;
    RatingBar rbStar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flo_rating);

        btnRatea1 = findViewById(R.id.boton);
        rbStar1 = findViewById(R.id.estrella);



    }
    public void contar1(View v){

        float resultado = rbStar1.getRating();
        Toast.makeText(floRating.this, "Said ha sido calificado con " +resultado+ "estrellas:",Toast.LENGTH_SHORT).show();



    }
}