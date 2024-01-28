package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import static androidx.core.graphics.drawable.DrawableCompat.applyTheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class alchebox extends AppCompatActivity {


    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String DARK_MODE_KEY = "darkModeKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_alchebox);

        final CheckBox showButtonCheckBox = findViewById(R.id.checkBox1);
        final Button muyButton = findViewById(R.id.muyButton);
        final CheckBox darkModeCheckBox = findViewById(R.id.checkBox2);
        darkModeCheckBox.setChecked(isDarkModeEnabled());



        darkModeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) ->{
            saveDarkModeState(isChecked);
            applyTheme();

        });

        showButtonCheckBox.setOnCheckedChangeListener((buttonView,isChecked) ->{

            if(isChecked){

                muyButton.setVisibility(View.VISIBLE);

            }else{
                muyButton.setVisibility(View.INVISIBLE);
            }



        });
    }

    private void applyTheme() {
        if (isDarkModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private boolean isDarkModeEnabled() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(DARK_MODE_KEY, false);
    }

    private void saveDarkModeState(boolean isChecked) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putBoolean(DARK_MODE_KEY, isChecked);
        editor.apply();
}
}