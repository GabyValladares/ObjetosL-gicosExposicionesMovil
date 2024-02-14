package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos.BDHelper;
import com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos.Persona;
import com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos.Vehiculo;

public class ResultadoFVActivity extends AppCompatActivity {
    TextView tvCedula,tvNombre,tvFecha,tvPlaca,tvColor,tvMarca,tvTipo,tvValor,tvRPlaca,tvAnio,tvMultaCont,tvMultas,tvTotal;
    Persona p;
    Vehiculo v;
    Boolean multas;
    double renovacionPlaca=0;
    String placa;

    private static final String CHANNEL_ID="canal";

    Button btNotificar,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_fvactivity);
        Bundle datos = this.getIntent().getExtras();
        String cedula = datos.getString("cedula");
        String nombre = datos.getString("nombre");
        p=new Persona(nombre,cedula);
        String fechaAtencion=datos.getString("fecha");
        placa=datos.getString("placa");
        String color=datos.getString("color");
        String marca=datos.getString("marca");
        String tipo=datos.getString("tipo");
        Double valor=datos.getDouble("valor");
        int anio=datos.getInt("anio");
        multas=datos.getBoolean("multas");
        v=new Vehiculo(placa,marca,color,valor,tipo,anio);

        tvCedula = findViewById(R.id.txtNombreB);
        tvNombre=findViewById(R.id.txtCedulaB);
        tvFecha=findViewById(R.id.txtFechaB);
        tvPlaca=findViewById(R.id.lblPlacaB);
        tvColor=findViewById(R.id.lblColorB);
        tvValor=findViewById(R.id.lblValorB);
        tvRPlaca=findViewById(R.id.lblRenovacionPlaca);
        tvAnio=findViewById(R.id.lblAnioB);
        tvMultaCont=findViewById(R.id.lblMultaContaminacion);
        tvTipo=findViewById(R.id.lblTipoB);
        tvMultas=findViewById(R.id.lblCalculoMultas);
        tvTotal=findViewById(R.id.txtTotal);
        tvMarca=findViewById(R.id.lblMarcaB);
        btNotificar=findViewById(R.id.btnNotificar);
        regresar=findViewById(R.id.btnRegresar);

        tvCedula.setText("Cédula Propietario: "+cedula);
        tvNombre.setText("Nombre Propietario: "+nombre);
        tvFecha.setText("Fecha de Atención:"+fechaAtencion);
        tvPlaca.setText("Placa:"+placa);
        tvColor.setText("Color:"+color);
        tvValor.setText("Valor:"+valor);
        tvMarca.setText("Marca:"+marca);
        tvTipo.setText("Tipo:"+tipo);
        tvRPlaca.setText("Renovación Placa:"+renovacionPlaca());
        tvMultaCont.setText("Multa Contaminación:"+multaContaminacion());
        tvMultas.setText("Multas Circulación"+multas());
        tvTotal.setText("$"+totalPagar());
        tvAnio.setText("Año Fabricación:"+anio);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            generarNoticacionCanal();
        }else{
            generarNoticacionSinCanal();

        }

        btNotificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    generarNoticacionCanal();
                }else{
                    generarNoticacionSinCanal();

                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultadoFVActivity.this,FichaMatriculacion.class);
                //finish();
                startActivity(intent);
                // System.exit(0);
            }
        });

    }

    public void irCalificar(View view){
        Intent i=new Intent(this,CalificacionServicioActivity.class);
        startActivity(i);
    }
/*    Si el número de cédula del usuario empieza en 1 y el número de placa con la letra I se debe cobrar un importe del 5% del sueldo básico ($435)para renovación de placas
    Si el año de fabricación del vehículo es menor a 2010 se debe cobrar una multa por contaminación equivalente al 2% por cada año de contaminación
    Validar el valor de matriculación dependiendo de la marca, tipo de vehículo , para todos los vehículos de: Marca Toyota de tipo Jeep se establecerá  el  8% del valor del vehículo , si el vehículo es de la marca Toyota tipo camioneta del 12% , Marca Suzuki tipo: vitara del 10% ; marca Suzuki tipo: automóvil  el 9%
    si posee multas calcular el 25% del sueldo básico
    Finalmente indicar cuánto deberá pagar por matriculación el vehículo*/
    public double renovacionPlaca(){
        char cedula=p.getCedula().charAt(0);
        char placa=v.getPlaca().charAt(0);
        if(cedula=='1' && placa=='I'){
            renovacionPlaca=435*0.05;
        }
        return renovacionPlaca;

    }
    public double multaContaminacion(){
        double multa=0;
        if(v.getAnio()<2010){
            multa=(2010-v.getAnio())*(435*0.02);
        }
        return multa;
    }
    public double multas(){
        if(multas==true){
            return 435*0.25;
        }

        return 0;
    }

    public double totalPagar(){

       return this.multas()+this.multaContaminacion()+this.renovacionPlaca();
    }

    public void crearTableVehiculo(View view){
        BDHelper admin=new BDHelper(this,"fichaVehicular.db",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String placaT= placa;
        String color=v.getColor();
        String marca=v.getMarca();
        String tipo=v.getTipo();
        Double valor=v.getValor();

        if(!placaT.isEmpty()&&!color.isEmpty()&&!marca.isEmpty()&&!tipo.isEmpty()&&valor>0){
            ContentValues datosRegistar=new ContentValues();
            datosRegistar.put("veh_placa",placaT);
            datosRegistar.put("veh_color",color);
            datosRegistar.put("veh_marca",marca);
            datosRegistar.put("veh_tipo",tipo);
            datosRegistar.put("veh_valor",valor);

            bd.insert("tblVehiculos",null,datosRegistar);
            Toast.makeText(this,"EL VEHICULO FUE REGISTRADO CORRECTAMENTE",Toast.LENGTH_LONG).show();
            bd.close();
        }else{

            Toast.makeText(this,"INGRESE LA FORMACIÓN DE MANERA CORRECTA",Toast.LENGTH_LONG).show();

        }




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
                .setContentTitle("FICHA VEHICULAR GGVC")
                .setContentText("Notificación Básica Ficha Vehicular")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Ficha Vehicular"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);





    }

    }
