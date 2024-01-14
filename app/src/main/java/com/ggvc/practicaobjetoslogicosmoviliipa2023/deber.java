package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

public class deber extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    EditText txt_numeroCedula, txt_nombrepropietario, txt_numeroPlaca, txt_anioFabricacion, txt_color,  txt_valorVehiculo, txt_multas;
    RadioGroup radioGroupMarca;

    RadioGroup radioGroupTipoVehiculo;

    TextView tipoVehiculo, valorVehiculo, multas, cedula, nombre, placa, aniodefabr, marca, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deber);
        progressBar = findViewById(R.id.progressBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.button);
        txt_numeroCedula = findViewById(R.id.txt_numeroCedula);
        txt_nombrepropietario = findViewById(R.id.txt_nombrepropietario);
        txt_numeroPlaca = findViewById(R.id.txt_numeroPlaca);
        txt_anioFabricacion = findViewById(R.id.txt_anioFrabri);
        txt_color = findViewById(R.id.txt_color);
        radioGroupTipoVehiculo = findViewById(R.id.radioGroupTipoVehiculo);
        txt_valorVehiculo = findViewById(R.id.txt_valorvehiculo);
        txt_multas = findViewById(R.id.txt_multas);
        radioGroupMarca = findViewById(R.id.radioGroupMarca);

        // Referencias de TextView
        tipoVehiculo = findViewById(R.id.tipo_vehiculo);
        valorVehiculo = findViewById(R.id.valor_vehiculo);
        multas = findViewById(R.id.multas);
        cedula = findViewById(R.id.cedula);
        nombre = findViewById(R.id.nombre);
        placa = findViewById(R.id.placa);
        aniodefabr = findViewById(R.id.aniodefabr);
        marca = findViewById(R.id.marca);
        color = findViewById(R.id.color);

        ocultarCampos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroCedula = txt_numeroCedula.getText().toString();
                String nombreText = txt_nombrepropietario.getText().toString();
                String numeroPlaca = txt_numeroPlaca.getText().toString();
                String anioFabricacion = txt_anioFabricacion.getText().toString();
                String colorText = txt_color.getText().toString();
                String valorVehiculoText = txt_valorVehiculo.getText().toString();
                String multasText = txt_multas.getText().toString();


                int selectedTipoVehiculoId = radioGroupTipoVehiculo.getCheckedRadioButtonId();
                RadioButton radioButtonTipoVehiculo = findViewById(selectedTipoVehiculoId);
                String tipoVehiculoText = radioButtonTipoVehiculo.getText().toString();


                int selectedId = radioGroupMarca.getCheckedRadioButtonId();
                RadioButton radioButtonMarca = findViewById(selectedId);
                String marcaText = radioButtonMarca.getText().toString();
                updateProgressBar();


                if (!numeroCedula.isEmpty() && !numeroPlaca.isEmpty() && !valorVehiculoText.isEmpty()) {
                    Intent intent = new Intent(deber.this, validacion.class);
                    intent.putExtra("numeroCedula", numeroCedula);
                    intent.putExtra("nombre", nombreText);
                    intent.putExtra("numeroPlaca", numeroPlaca);
                    intent.putExtra("anioFabricacion", anioFabricacion);
                    intent.putExtra("color", colorText);
                    intent.putExtra("tipoVehiculo", tipoVehiculoText);
                    intent.putExtra("valorVehiculo", valorVehiculoText);
                    intent.putExtra("marca", marcaText);
                    intent.putExtra("multas", multasText);
                    startActivity(intent);
                } else {
                }
            }
        });

        txt_numeroCedula.addTextChangedListener(new CustomTextWatcher());
        txt_nombrepropietario.addTextChangedListener(new CustomTextWatcher());
        txt_numeroPlaca.addTextChangedListener(new CustomTextWatcher());
        txt_anioFabricacion.addTextChangedListener(new CustomTextWatcher());
        txt_color.addTextChangedListener(new CustomTextWatcher());
        txt_valorVehiculo.addTextChangedListener(new CustomTextWatcher());
        txt_multas.addTextChangedListener(new CustomTextWatcher());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_ficha_personal) {
            // Lógica para la opción "Ficha Personal"
            mostrarCamposFichaPersonal();
            return true;
        } else if (itemId == R.id.action_ficha_vehicular) {
            mostrarCamposFichaVehicular();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void ocultarCampos() {
        txt_numeroCedula.setVisibility(View.GONE);
        txt_nombrepropietario.setVisibility(View.GONE);
        txt_numeroPlaca.setVisibility(View.GONE);
        txt_anioFabricacion.setVisibility(View.GONE);
        txt_color.setVisibility(View.GONE);
        radioGroupTipoVehiculo.setVisibility(View.GONE);
        txt_valorVehiculo.setVisibility(View.GONE);
        txt_multas.setVisibility(View.GONE);
        radioGroupMarca.setVisibility(View.GONE);

        tipoVehiculo.setVisibility(View.GONE);
        valorVehiculo.setVisibility(View.GONE);
        multas.setVisibility(View.GONE);
        cedula.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
        placa.setVisibility(View.GONE);
        aniodefabr.setVisibility(View.GONE);
        marca.setVisibility(View.GONE);
        color.setVisibility(View.GONE);
    }

    private void mostrarCamposFichaPersonal() {
        // Muestra los EditText
        txt_numeroCedula.setVisibility(View.VISIBLE);
        txt_nombrepropietario.setVisibility(View.VISIBLE);

        // Oculta los demás EditText
        txt_numeroPlaca.setVisibility(View.GONE);
        txt_anioFabricacion.setVisibility(View.GONE);
        txt_color.setVisibility(View.GONE);
        radioGroupTipoVehiculo.setVisibility(View.GONE);
        txt_valorVehiculo.setVisibility(View.GONE);
        txt_multas.setVisibility(View.GONE);
        radioGroupMarca.setVisibility(View.GONE);

        // Muestra los TextView
        cedula.setVisibility(View.VISIBLE);
        nombre.setVisibility(View.VISIBLE);

        // Oculta los demás TextView
        placa.setVisibility(View.GONE);
        aniodefabr.setVisibility(View.GONE);
        color.setVisibility(View.GONE);
        tipoVehiculo.setVisibility(View.GONE);
        valorVehiculo.setVisibility(View.GONE);
        multas.setVisibility(View.GONE);
        marca.setVisibility(View.GONE);
    }

    private void mostrarCamposFichaVehicular() {
        // Muestra los EditText
        txt_numeroPlaca.setVisibility(View.VISIBLE);
        txt_anioFabricacion.setVisibility(View.VISIBLE);
        txt_color.setVisibility(View.VISIBLE);
        radioGroupTipoVehiculo.setVisibility(View.VISIBLE);
        txt_valorVehiculo.setVisibility(View.VISIBLE);
        txt_multas.setVisibility(View.VISIBLE);
        radioGroupMarca.setVisibility(View.VISIBLE);

        // Oculta los demás EditText
        txt_numeroCedula.setVisibility(View.GONE);
        txt_nombrepropietario.setVisibility(View.GONE);

        // Muestra los TextView
        placa.setVisibility(View.VISIBLE);
        aniodefabr.setVisibility(View.VISIBLE);
        color.setVisibility(View.VISIBLE);
        tipoVehiculo.setVisibility(View.VISIBLE);
        valorVehiculo.setVisibility(View.VISIBLE);
        multas.setVisibility(View.VISIBLE);
        marca.setVisibility(View.VISIBLE);

        // Oculta los demás TextView
        cedula.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
    }

    private void updateProgressBar() {
        // Calcular el progreso actual
        int progress = calculateProgress();

        // Actualizar el progreso del ProgressBar
        progressBar.setProgress(progress);
    }

    private int calculateProgress() {
        int filledEditTextCount = 0;

        int totalEditTextCount = 7;

        if (!txt_numeroCedula.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_nombrepropietario.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_numeroPlaca.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_anioFabricacion.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_color.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_valorVehiculo.getText().toString().isEmpty()) filledEditTextCount++;
        if (!txt_multas.getText().toString().isEmpty()) filledEditTextCount++;

        // Calcular el progreso
        return (filledEditTextCount * 100) / totalEditTextCount;
    }

    private class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No necesitamos hacer nada antes de cambiar el texto
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No necesitamos hacer nada cuando el texto está cambiando
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Llamamos a la función para actualizar la barra de progreso
            updateProgressBar();
        }
    }
}