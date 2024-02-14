package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RadioGroup extends AppCompatActivity {

    private android.widget.RadioGroup radioGroupOperation;
    private Button button;
    private RelativeLayout mainLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);

        radioGroupOperation = findViewById(R.id.radioGroupOperation);
        button = findViewById(R.id.button);
        mainLayout = findViewById(R.id.mainLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupOperation.getCheckedRadioButtonId();

                if (selectedId == R.id.radioButtonSuma) {
                    showInputDialog("Suma");
                } else if (selectedId == R.id.radioButtonResta) {
                    showInputDialog("Resta");
                }
            }
        });
    }

    private void showInputDialog(final String operation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(operation);

        // Inflar el diseño personalizado del cuadro de diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogo_input, null);
        builder.setView(dialogView);

        final EditText editTextNumber1 = dialogView.findViewById(R.id.editTextNumber1);
        final EditText editTextNumber2 = dialogView.findViewById(R.id.editTextNumber2);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Obtener los valores ingresados
                String strNumber1 = editTextNumber1.getText().toString();
                String strNumber2 = editTextNumber2.getText().toString();

                // Convertir a números
                int number1 = Integer.parseInt(strNumber1);
                int number2 = Integer.parseInt(strNumber2);

                // Realizar la operación correspondiente
                if (operation.equals("Suma")) {
                    int result = number1 + number2;
                    showToast("Resultado de la suma: " + result);
                } else if (operation.equals("Resta")) {
                    int result = number1 - number2;
                    showToast("Resultado de la resta: " + result);
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Acciones al hacer clic en "Cancelar"
            }
        });

        builder.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}