package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RadiosActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radios);

        // Obtener referencias de los elementos en el layout
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        // Configurar el evento clic del botón
        Button btnMostrar = findViewById(R.id.btnMostrar);
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
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // Verificar la opción seleccionada y mostrar un mensaje
        String message;
        if (selectedId == radioButton1.getId()) {
            message = "HAZ ELEGIDO LA OPCION 1";
        } else if (selectedId == radioButton2.getId()) {
            message = "HAZ ELEGIDO LA OPCION 2";
        } else if (selectedId == radioButton3.getId()) {
            message = "HAZ ELEGIDO LA OPCION 3";
        } else {
            message = "NINGUNA OPCION SELECCIONADA";
        }


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
