package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class RadioGroup_activity extends AppCompatActivity {
    private RadioGroup radioGroupThemee;
    private Button button;
    private RelativeLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);


        radioGroupThemee = findViewById(R.id.RDT);
        button = findViewById(R.id.button);
        mainLayout = findViewById(R.id.mainLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupThemee.getCheckedRadioButtonId();

                if (selectedId == R.id.radioButtonOscuro) {
                    mainLayout.setBackgroundColor(Color.BLACK);
                } else if (selectedId == R.id.radioButtonClaro) {
                    mainLayout.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }


    }
