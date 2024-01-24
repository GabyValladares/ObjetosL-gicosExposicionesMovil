package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReatingbarActivity extends AppCompatActivity {
    TextView tvtex;
    Button btncali;
    RatingBar ratinng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reatingbar);
        tvtex = findViewById(R.id.txtCal);
        btncali = findViewById(R.id.btncal);
        ratinng = findViewById(R.id.reating1);
    }
    public void contar(View v){
        String nombre = tvtex.getText().toString();
        float resultado = ratinng.getRating();
        Toast.makeText(ReatingbarActivity.this,nombre+"Has sido califocado:" +resultado+"estrellas", Toast.LENGTH_LONG).show();
    }
}