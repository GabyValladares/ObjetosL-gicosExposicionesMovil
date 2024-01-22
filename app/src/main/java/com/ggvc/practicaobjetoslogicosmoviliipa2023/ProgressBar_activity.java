package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


public class ProgressBar_activity extends AppCompatActivity {


    //progeressbar
    private ProgressBar progressBar;
    private Button startButton;
    private int progressStatus = 0;
    private Handler handler = new Handler();





    public ProgressBar_activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        progressBar = findViewById(R.id.progressBar);
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