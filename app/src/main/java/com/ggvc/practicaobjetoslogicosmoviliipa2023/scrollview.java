package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class scrollview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        Button btn0=findViewById(R.id.btn0);
        Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
        Button btn3=findViewById(R.id.btn3);
        Button btn4=findViewById(R.id.btn4);
        Button btn5=findViewById(R.id.btn5);
        Button btn6=findViewById(R.id.btn6);
        Button btn7=findViewById(R.id.btn7);
        Button btn8=findViewById(R.id.btn8);
        Button btn9=findViewById(R.id.btn9);
        Button btn10=findViewById(R.id.btn10);
        
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mostrarMensaje("Diste el boton 0");
            }

            private void mostrarMensaje(String s) {
            }
        });

    }
}