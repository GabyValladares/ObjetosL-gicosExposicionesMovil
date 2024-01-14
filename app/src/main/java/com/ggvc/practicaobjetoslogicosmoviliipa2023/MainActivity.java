package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Agrega un Toolbar a la actividad
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // Infla el menú en la barra de herramientas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Maneja los clics en los elementos del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Maneja los clics en los elementos del menú aquí
        int itemId = item.getItemId();

        if (itemId == R.id.opc1) {
            // Lógica para la opción 1
            return true;
        } else if (itemId == R.id.opc2) {
            // Lógica para la opción 2
            return true;
        } else if (itemId == R.id.opc3) {
            // Lógica para la opción 3
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
