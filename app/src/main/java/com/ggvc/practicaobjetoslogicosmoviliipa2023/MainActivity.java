package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText txtCedula, txtNombre, txtPlaca, txtFbVehiculo, txtMarca, txtColor, txtTipo, txtValor, txtMultas;
    private Button btnEnviar;
    private Toolbar toolbar1;
    private RadioButton rbtoyota, rbmazda;
    private ProgressBar progressBar;
    private TextView txtProgresoNumerico;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtFbVehiculo = findViewById(R.id.txtAnio);
        rbtoyota = findViewById(R.id.marToyota);
        rbmazda = findViewById(R.id.marMazda);
        txtMarca = findViewById(R.id.txtMarca);
        txtColor = findViewById(R.id.txtColor);
        txtTipo = findViewById(R.id.txtTipoV);
        txtValor = findViewById(R.id.TXTvALOR);
        txtMultas = findViewById(R.id.txtMultas);
        btnEnviar = findViewById(R.id.btnCalcular);
        progressBar = findViewById(R.id.barra_datos);
        txtProgresoNumerico = findViewById(R.id.txtProgresoNumerico);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        addTextWatcher(txtCedula);
        addTextWatcher(txtNombre);
        addTextWatcher(txtPlaca);
        addTextWatcher(txtFbVehiculo);
        addTextWatcher(txtMarca);
        addTextWatcher(txtColor);
        addTextWatcher(txtTipo);
        addTextWatcher(txtValor);
        addTextWatcher(txtMultas);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(100);
                txtProgresoNumerico.setText("100%");

                Intent intent = new Intent(MainActivity.this, Pago_Activity.class);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("NonConstantResourceId")



    private void addTextWatcher(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int progress = calculateProgress();
                progressBar.setProgress(progress);
                txtProgresoNumerico.setText(progress + "%");
            }
        });
    }

    private int calculateProgress() {
        int totalFields = 10;
        int filledFields = 0;

        if (!txtCedula.getText().toString().isEmpty()) filledFields++;
        if (!txtNombre.getText().toString().isEmpty()) filledFields++;
        if (!txtPlaca.getText().toString().isEmpty()) filledFields++;
        if (!txtFbVehiculo.getText().toString().isEmpty()) filledFields++;

        if ((rbtoyota != null && rbtoyota.isChecked()) || (rbmazda != null && rbmazda.isChecked())) {
            filledFields++;
        }

        if (((rbtoyota != null && rbtoyota.isChecked()) || (rbmazda != null && rbmazda.isChecked())) && txtMarca.getText().toString().isEmpty()) {
            filledFields++;
        }

        if (!txtColor.getText().toString().isEmpty()) filledFields++;
        if (!txtTipo.getText().toString().isEmpty()) filledFields++;
        if (!txtValor.getText().toString().isEmpty()) filledFields++;
        if (!txtMultas.getText().toString().isEmpty()) filledFields++;

        return (filledFields * 100) / totalFields;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
