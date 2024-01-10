package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class ProgressBar extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnEjecutar;
    int progressStatus=0;
    Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        progressBar.findViewById(R.id.progressBar2);
        btnEjecutar.findViewById(R.id.btnEjecutar);

        btnEjecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            }}
                    }

            }).start();

            }
   });
}
}