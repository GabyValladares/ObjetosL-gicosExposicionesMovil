package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PogressBar extends AppCompatActivity {

    private PogressBar progressBar;
    private Button startButton;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogress_bar);


        startButton = findViewById(R.id.startButton);

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
}