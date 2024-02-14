package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    TextView txvCedula, txvNombres, txvValorPlaca, txvValorAnio, txvValorMarca, txvValorMultas, txvValorTotal, txvTipo;
    private static final String CHANNEL_ID= "canal";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvCedula = findViewById(R.id.tvCedula);
        txvNombres = findViewById(R.id.tvNombres);
        txvTipo = findViewById(R.id.tvTipo);
        txvValorPlaca = findViewById(R.id.tvValorPlaca);
        txvValorAnio = findViewById(R.id.tvValorAnio);
        txvValorMarca = findViewById(R.id.tvValorMarca);
        txvValorMultas = findViewById(R.id.tvValorMultas);
        txvValorTotal = findViewById(R.id.tvTotal);
        Button btnregresar = findViewById(R.id.btRegresa);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            generarNoticacionCanal();
        }else{
            generarNoticacionSinCanal();
        }

        Bundle datosV = getIntent().getExtras();
        String cedulaP = datosV.getString("cedulaP");
        String nombresP = datosV.getString("nombresP");
        String marcaV = datosV.getString("marcaV");
        String tipoV = datosV.getString("tipoV");
        String colorV = datosV.getString("colorV");
        String placaV = datosV.getString("placaV");
        Integer anioV = datosV.getInt("anioV");
        Integer valorV = datosV.getInt("valorV");
        Integer multasV = datosV.getInt("multasV");

        double valorPlaca = this.calcularValorRenovacionPlacas(cedulaP,placaV);
        double valorAnio = this.calcularMultaContaminacion(anioV);
        double valorMarca = this.calcularValorMatriculacion(marcaV,tipoV,valorV);
        double valorMultas = this.calcularMultaPorMultas(multasV);
        double valorTotal = valorPlaca + valorAnio + valorMarca + valorMultas;

        txvCedula.setText(cedulaP);
        txvNombres.setText(nombresP);
        txvTipo.setText(tipoV+" "+marcaV+" "+colorV);
        txvValorPlaca.setText("$"+valorPlaca);
        txvValorAnio.setText("$"+valorAnio);
        txvValorMarca.setText("$"+valorMarca);
        txvValorMultas.setText("$"+valorMultas);
        txvValorTotal.setText("$"+valorTotal);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pago_Activity.class);
                startActivity(intent);
            }
        });
    }
    public double calcularValorRenovacionPlacas(String cedula, String placa) {
        int sueldoBasico = 435;
        if (cedula.startsWith("1") && placa.contains("I")) {

            double porcentajeRenovacion = 0.05;
            double valorPlaca = sueldoBasico * porcentajeRenovacion;
            return valorPlaca;
        }
        return 0;
    }
    public double calcularMultaContaminacion(int anioFabricacion) {

        if (anioFabricacion < 2010) {

            double porcentajeMulta = 0.02;
            int aniosDeContaminacion = 2010 - anioFabricacion;
            double valorAnio = aniosDeContaminacion * porcentajeMulta;
            return valorAnio;
        }
        return 0;
    }
    public double calcularValorMatriculacion(String marca, String tipo, int valorVehiculo) {
        // Verifica la marca y el tipo del vehículo y calcula el valor de matriculación según las condiciones
        if (marca.equals("TOYOTA") && tipo.equals("JEEP")) {
            // 8% del valor del vehículo
            double porcentajeMatriculacion = 0.08;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("TOYOTA") && tipo.equals("CAMIONETA")) {

            double porcentajeMatriculacion = 0.12;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("MAZDA") && tipo.equals("CAMIONETA")) {

            double porcentajeMatriculacion = 0.10;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("FORD") && tipo.equals("AUTOMOVIL")) {

            double porcentajeMatriculacion = 0.09;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        }
        return 0;
    }
    public double calcularMultaPorMultas(int cantidadMultas) {
        int sueldoBasico = 435;
        if (cantidadMultas > 0) {
            double porcentajeMulta = 0.25;
            double valorMulta = sueldoBasico * porcentajeMulta;
            return valorMulta;
        }
        return 0;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generarNoticacionCanal(){
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        generarNoticacionSinCanal();
    }
    public void generarNoticacionSinCanal(){

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.icono)
                .setContentTitle("Ficha vehicular mb")
                .setContentText("Aplicación que permite ingresar usuario")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Ficha Vehicular"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);





    }
}