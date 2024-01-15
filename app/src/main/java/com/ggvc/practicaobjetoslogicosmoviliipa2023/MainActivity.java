package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar1;

    //progeressbar
    private ProgressBar progressBar;
    private Button startButton;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    private View datosPersonalesLayout;
    private View fichaVehicularLayout;


    private EditText edNombre, edCedula, edAnioFabricacion, edValor, edPlaca;
    private RadioGroup radioGroupMultas, radioGroupMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar1=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        progressBar = findViewById(R.id.progressBar);
        startButton = findViewById(R.id.startButton);
        datosPersonalesLayout = findViewById(R.id.datosPersonalesLayout);
        fichaVehicularLayout = findViewById(R.id.fichaVehicularLayout);


        edNombre = findViewById(R.id.edNombre);
        edCedula = findViewById(R.id.edCedula);
        edAnioFabricacion = findViewById(R.id.edAnioFabricacion);
        edValor = findViewById(R.id.edValor);
        edPlaca = findViewById(R.id.edPlaca);
        radioGroupMultas = findViewById(R.id.radioGroupMultas);
        radioGroupMarca = findViewById(R.id.radioGroupMarca);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia un hilo para simular un proceso en segundo plano
                new Thread(new Runnable() {
                    public void run() {

                        // Lógica para simular el progreso...
                        while (progressStatus < 100) {
                            progressStatus += 1;

                            // Actualiza la barra de progreso en el hilo principal
                            handler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                }
                            });

                            try {
                                // Agrega una pequeña pausa para simular el progreso
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_datos_personales) {
            // Mostrar Datos Personales y ocultar Ficha Vehicular
            datosPersonalesLayout.setVisibility(View.VISIBLE);
            fichaVehicularLayout.setVisibility(View.GONE);
            return true;
        } else if (itemId == R.id.action_ficha_vehicular) {
            // Mostrar Ficha Vehicular y ocultar Datos Personales
            fichaVehicularLayout.setVisibility(View.VISIBLE);
            datosPersonalesLayout.setVisibility(View.GONE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}