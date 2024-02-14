package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class ResultadoFV extends AppCompatActivity {

    TextView tvNombre, tvCedula, tvPlaca, tvFabricacion, tvTipo, tvMarca, tvCosto, tvMulta, tvColor;
    String nombre,cedula,placa,anio,tipo,marca,costo,multa;
    Button btnNotificacion;
    private static final String CHANNEL_ID = "canal";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_fv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            generarNoticacionCanal();
        } else {
            generarNoticacionSinCanal();
        }

        /*tvNombre = findViewById(R.id.lblNombre2);
        tvCedula = findViewById(R.id.lblCedula2);
        tvPlaca = findViewById(R.id.lblPlaca2);
        tvFabricacion = findViewById(R.id.lblFabricacion);
        tvTipo = findViewById(R.id.lblTipo);
        tvMarca = findViewById(R.id.lblMarca);
        tvCosto = findViewById(R.id.lblCosto);
        tvMulta = findViewById(R.id.lblMultas2);
        tvColor = findViewById(R.id.lblColor2);
        btnNotificacion = findViewById(R.id.btnNotificacion);

        Bundle datos = getIntent().getExtras();

        nombre = datos.getString("nombre");
        cedula = datos.getString("cedula");
        placa = datos.getString("placa");
        anio = datos.getString("anio");
        tipo = datos.getString("tipo");
        marca = datos.getString("marca");
        costo = datos.getString("costo");
        multa = datos.getString("multa");
*/
    }

    /*public void CrearTablaVehiculo (View view){
        BDHelper admin=new BDHelper(this,"FichaVehicular.db",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        if(!placa.isEmpty()&&!tipo.isEmpty()&&!marca.isEmpty()&&!anio.isEmpty()){
            ContentValues datosRegistrar = new ContentValues();
            datosRegistrar.put("veh_placa", placa);
            datosRegistrar.put("veh_tipo", tipo);
            datosRegistrar.put("veh_marca", marca);
            datosRegistrar.put("veh_anio", anio);

            bd.insert("tblVehiculo", null,datosRegistrar);

            Toast.makeText(this, "registro existosamente", Toast.LENGTH_LONG);
        }
    }*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generarNoticacionCanal(){
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        generarNoticacionSinCanal();
}

    public void generarNoticacionSinCanal(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.icono_foreground)
                .setContentTitle("Notificacion Prueba")
                .setContentText("esto es una prueba de notificación")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Cálculos Matemáticos"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
}
}