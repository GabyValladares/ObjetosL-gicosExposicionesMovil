package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Checkbox extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String DARK_MODE_KEY = "darkModeKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme(); // Aplica el tema al iniciar la actividad
        setContentView(R.layout.activity_checkbox);

        final CheckBox showButtonCheckBox = findViewById(R.id.showButtonCheckBox);
        final Button myButton = findViewById(R.id.myButton);
        final CheckBox darkModeCheckBox = findViewById(R.id.darkModeCheckBox);
        darkModeCheckBox.setChecked(isDarkModeEnabled()); // Restaura el estado del CheckBox

        darkModeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveDarkModeState(isChecked); // Guarda el estado del CheckBox en SharedPreferences
            applyTheme(); // Aplica el tema inmediatamente al cambiar el CheckBox
        });

        showButtonCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Mostrar el botón
                myButton.setVisibility(View.VISIBLE);
            } else {
                // Ocultar el botón
                myButton.setVisibility(View.INVISIBLE);
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