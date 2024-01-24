package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SEARCHVIEW extends AppCompatActivity {
    private String[] nombre;
    private ArrayAdapter<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        nombre = new String[]{"jose", "luis", "pedro", "lucas", "martin", "flores"};
//asigna variable
        ListView listView = findViewById(R.id.lisa);
        lista = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombre);
        listView.setAdapter(lista);

        SearchView searchView = findViewById(R.id.idsearch);
//es un objeto para manejar los eventos
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;//envia la consulta de busqueda
            }
            //implementa el metodocuando realiza la busqueda
            @Override
            public boolean onQueryTextChange(String newText) {
                lista.getFilter().filter(newText);
                return true;
            }
        });


    }
}