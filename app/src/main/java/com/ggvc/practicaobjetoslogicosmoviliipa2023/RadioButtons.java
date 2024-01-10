package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

public class RadioButtons extends AppCompatActivity {
    RadioGroup rdGroup;
    RadioButtons rdOp1, rdOp2,rdOp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttons);
        rdGroup.findViewById(R.id.rdGroup);
        rdOp1.findViewById(R.id.rbOp1);
        rdOp2.findViewById(R.id.rbOp2);
        rdOp3.findViewById(R.id.rbOp3);



    }
}