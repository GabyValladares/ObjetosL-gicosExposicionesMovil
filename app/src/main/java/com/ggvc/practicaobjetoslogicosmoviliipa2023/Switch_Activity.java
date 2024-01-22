package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Switch_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        TextView texto = findViewById(R.id.tvtexto);
        Switch swtCambia = findViewById(R.id.swtCambio);
        ConstraintLayout fondo = findViewById(R.id.clFondo);
        swtCambia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int colorPlomo = Color.rgb(169, 169, 169);
                if (view.getId()==R.id.swtCambio){
                    if (swtCambia.isChecked()){
                        texto.setText("ACTIVADO");
                        fondo.setBackgroundColor(colorPlomo);
                    }else{
                        texto.setText("DESACTIVADO");
                        fondo.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });
    }
}