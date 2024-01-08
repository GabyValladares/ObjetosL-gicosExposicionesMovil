package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioButon extends AppCompatActivity {

    private RadioGroup radioGroup1;

    private RadioButton rdbutton1, rdbutton2, rdbutton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buton);

        radioGroup1 = findViewById(R.id.rdbg);
        rdbutton1 = findViewById(R.id.rdb1);
        rdbutton2 = findViewById(R.id.rdb2);
        rdbutton3 = findViewById(R.id.rdb3);

        Button btnMostrar = findViewById(R.id.button);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llamar a la función para mostrar la selección
                showSelection();
            }
        });
    }
    private void showSelection() {
        // Obtener el ID de la opción seleccionada
        int selectedId = radioGroup1.getCheckedRadioButtonId();

        // Verificar la opción seleccionada y mostrar un mensaje
        String message;
        if (selectedId == R.id.rdb1) {
            message = "HAZ ELEGIDO LA OPCION 1";
        } else if (selectedId == R.id.rdb2) {
            message = "HAZ ELEGIDO LA OPCION 2";
        } else if (selectedId == R.id.rdb3) {
            message = "HAZ ELEGIDO LA OPCION 3";
        } else {
            message = "NINGUNA OPCION SELECCIONADA";
        }

        // Mostrar un Toast con el mensaje
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}