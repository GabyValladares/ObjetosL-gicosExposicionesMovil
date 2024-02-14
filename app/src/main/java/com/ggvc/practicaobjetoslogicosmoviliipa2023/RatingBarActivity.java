package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingBarActivity extends AppCompatActivity {

    Button btnCalificar;
    RatingBar rbarPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        btnCalificar = findViewById(R.id.btnCalificar);
        rbarPrueba = findViewById(R.id.rbarPrueba);
    }

    public void calificar(View view) {
        float resultado = rbarPrueba.getRating();
        Toast.makeText(this, "Rating: " + resultado, Toast.LENGTH_SHORT).show();
    }
}
