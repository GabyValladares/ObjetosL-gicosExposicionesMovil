package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos.Persona;
import com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos.Vehiculo;

import java.util.Calendar;

public class FichaMatriculacion extends AppCompatActivity {

    EditText edNombre, edCedula,edPlaca,edValor,edAnioFab;
    Button btMarca,btnOculto;
    TextView tvFecha,tvMarca;
    CheckBox ckbColor;
    private android.widget.ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    String fecha;

    private RelativeLayout mainLayout;
    //
    private static final String PREFS_NAME="MyPrefsFile";
    private static final String DARK_MODE_KEY="darkModeKey";

    private Persona persona=new Persona();
    private Vehiculo vehiculo=new Vehiculo();

    String[] listaColores = {"Seleccione","Negro","Rojo","Beige","Blanco","Plomo","Café","Naranja","Verde"};
    String[] listaTipo={"Seleccione","Jeep","Automóvil","Camioneta","Vitara"};
    Spinner spnColores,spnTipo;

    Switch swtMultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_matriculacion);
        edNombre=findViewById(R.id.txtNombres);
        edCedula=findViewById(R.id.txtCedula);
        edPlaca=findViewById(R.id.txtPlaca);
        edValor=findViewById(R.id.txtValor);
        edAnioFab=findViewById(R.id.txtAnioFab);
        tvFecha=findViewById(R.id.txtFecha);
        progressBar = findViewById(R.id.pgBar);
        ckbColor=findViewById(R.id.ckbColor);
       // radioGroupTheme = findViewById(R.id.radioGroupTheme);
        btMarca = findViewById(R.id.btnMarca);
        mainLayout = findViewById(R.id.mainLayout);
        tvMarca=findViewById(R.id.txtMarca);
        spnColores=findViewById(R.id.spnColor);
        spnTipo=findViewById(R.id.spnTipo);
        swtMultas=findViewById(R.id.swtMultas);

        spnColores.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaColores));
        spnTipo.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaTipo));
        Toast.makeText(this,spnColores.getSelectedItem()+"",Toast.LENGTH_LONG);

        ckbColor.setChecked(isDarkModeEnabled());
        ckbColor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveDarkModeState(ckbColor.isChecked());
            applyTheme();
        });

        btMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();

            }
        });

    }

    //PROGRESSBAR
    public void procesar(View view){
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


        String nombre=edNombre.getText().toString();
        String cedula=edCedula.getText().toString();
        String placa=edPlaca.getText().toString();
        Double valor=Double.parseDouble(edValor.getText().toString());
        String color= (String) spnColores.getSelectedItem();
        String tipo= (String) spnTipo.getSelectedItem();
        String marca=tvMarca.getText().toString();
        int anioFab=Integer.parseInt(edAnioFab.getText().toString());
        boolean multas=swtMultas.isChecked();
        persona.setNombres(nombre);
        persona.setCedula(cedula);
        if(this.comprobarCedula(cedula)==true && this.comprobarNombre(nombre)==true){
            Bundle datos=new Bundle();
            datos.putString("nombre",nombre);
            datos.putString("cedula",cedula);
            //datos.putString("persona", String.valueOf(persona));
            datos.putString("fecha",fecha);
            datos.putString("placa",placa);
            datos.putDouble("valor",valor);
            datos.putString("color",color);
            datos.putInt("anio",anioFab);
            datos.putString("tipo",tipo);
            datos.putString("marca",marca);
            datos.putBoolean("multas",multas);
            Intent intent= new Intent(this,ResultadoFVActivity.class);
            intent.putExtras(datos);
            startActivity(intent);

        }

    }

    public boolean comprobarCedula(String cedula){
        if(cedula.length()==10){
            return true;
        }else{
            return false;
        }
    }

    public boolean comprobarNombre(String nombre){

        if(!nombre.isEmpty()){
            return true;
        }else{
            return false;}
    }
    //FECHA
    public void AbrirCalendario(View view){
        Calendar cal= Calendar.getInstance();
        int anio= cal.get(Calendar.YEAR);
        int mes= cal.get(Calendar.MONTH);
        int dia= cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(FichaMatriculacion.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha= dayOfMonth+"/"+(month+1)+"/"+year;
                tvFecha.setText(fecha);
            }
        },dia,mes,anio);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();
    }
    //FONDO DE APP
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

    //MARCA
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflar el diseño personalizado del cuadro de diálogo

        final View dialogView = getLayoutInflater().inflate(R.layout.opcionesmarca, null);
        builder.setView(dialogView);

        final View group = dialogView.findViewById(R.id.rdgMarcas);

        RadioButton rbToyota = group.findViewById(R.id.rdbToyota);
        final RadioButton rbSuzuki = group.findViewById(R.id.rdbSuzuki);
        final RadioButton rbSkoda= group.findViewById(R.id.rdbSkoda);




        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    if(rbToyota.isChecked()){
                        tvMarca.setText("Toyota");
                    }else if(rbSkoda.isChecked()){
                        tvMarca.setText("Skoda");
                    }else if(rbSuzuki.isChecked()){
                        tvMarca.setText("Suzuki");
                    }

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Acciones al hacer clic en "Cancelar"
            }
        });

        builder.show();
    }




}