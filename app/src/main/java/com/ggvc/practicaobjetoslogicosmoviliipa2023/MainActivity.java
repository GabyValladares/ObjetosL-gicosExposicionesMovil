package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TextView tv;

    Button btnRate1;

    RatingBar rbStar1;

    private static String CHANNEL_ID= "canal";

    Button bttNotificar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.t2);
        btnRate1 = findViewById(R.id.bttCalificar);
        rbStar1 = findViewById(R.id.ratingBar1);
        bttNotificar=findViewById(R.id.bttNotificar);
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);


        bttNotificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                generarNoticacionSinCanal();
                }else{
                generarNoticacionSinCanal();
                }
            }
        });
        DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year ;
                tv.setText(fecha);

            }
        }, dia, mes, anio);
        dpd.show();



    }

    public void contar2(View v){
        float resultado = rbStar1.getRating();
        Toast.makeText(MainActivity.this, "DAVID ha sido calificado con " +resultado+ "estrellas:",Toast.LENGTH_SHORT).show();

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
                .setContentTitle("FICHA VEHICULAR")
                .setContentText("NOTIFICACION BASICA FICHA VEHICULAR")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("FICHA VEHICULAR"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);


    }
}