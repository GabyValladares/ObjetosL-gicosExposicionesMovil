package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingBar1 extends AppCompatActivity {

    Button btnRate1;
    RatingBar rbStar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        btnRate1 = findViewById(R.id.btn1);
        rbStar1 = findViewById(R.id.ratingBar1);
    }
    public void contar1(View v){

        float resultado = rbStar1.getRating();
        Toast.makeText(RatingBar1.this," has calificado con "+resultado+" estrellas", Toast.LENGTH_SHORT).show();
    }

}