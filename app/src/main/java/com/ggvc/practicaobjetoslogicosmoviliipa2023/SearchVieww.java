package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

public class SearchVieww extends AppCompatActivity {

    private String[] nombres;
    private ArrayAdapter<String> adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        nombres = new String[]{"David", "Pablo", "Ipiales", "Said"};

        ListView listView = findViewById(R.id.listView);
        adaptadorLista = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        listView.setAdapter(adaptadorLista);

        SearchView searchView = findViewById(R.id.idsearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes manejar la lógica cuando se envía la búsqueda
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aquí puedes manejar la lógica cuando cambia el texto de búsqueda
                adaptadorLista.getFilter().filter(newText);
                return false;
            }
        });
    }
}
