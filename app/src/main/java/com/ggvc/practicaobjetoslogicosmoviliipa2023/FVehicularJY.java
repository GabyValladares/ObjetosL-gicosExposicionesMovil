package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FVehicularJY extends AppCompatActivity {

    EditText edNombres, edCedulas, edPlaca, edAnio;
    Button btnProcesar, btnCalendario;

    private EditText edCedula;
    private EditText edNombre;

    private Button btnEnviarDatos;

    DatePicker datePicker;
    TextView edFecha; // Cambiado de EditText a TextView

    RadioGroup rgMarca, rgTipo;

    //private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvehicular_jy);

       // dbHelper = new DBHelper(this);

        edNombres = findViewById(R.id.txtNombre);
        edCedulas = findViewById(R.id.txtCedula);
        edPlaca = findViewById(R.id.txtPlaca);
        edAnio = findViewById(R.id.txtAnioVe);
        btnProcesar = findViewById(R.id.btnGuardar);
        btnCalendario = findViewById(R.id.btnCalendario);
        edFecha = findViewById(R.id.txtFecha); // Actualizado
      // RADIO GROUP de marca y tipo vehiculo
        rgMarca = findViewById(R.id.rgMarca);
        rgTipo = findViewById(R.id.rgTipo);


        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar(view);
            }
        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });


        generarNotificacion();

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                FVehicularJY.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        edFecha.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void procesar(View view) {
        String nombre = edNombres.getText().toString();
        String cedula = edCedulas.getText().toString();
        String fecha = edFecha.getText().toString();
        String placa = edPlaca.getText().toString();
        String anio = edAnio.getText().toString();
        boolean tieneMultas = ((Switch) findViewById(R.id.bntMultas)).isChecked();

       // dbHelper.insertarPropietario(nombre, cedula, fecha);
       // dbHelper.insertarVehiculo(placa, anio, marcaSeleccionada, tipoSeleccionado);

        RadioButton radioButtonMarca = findViewById(rgMarca.getCheckedRadioButtonId());
        String marcaSeleccionada = radioButtonMarca.getText().toString();

        // Obtener la selección de tipo
        RadioButton radioButtonTipo = findViewById(rgTipo.getCheckedRadioButtonId());
        String tipoSeleccionado = radioButtonTipo.getText().toString();

        if (!nombre.isEmpty() && isNumeric(cedula) && !fecha.isEmpty()) {
            // Campos requeridos están llenos
            // Puedes ajustar el progreso del ProgressBar aquí
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(60);

            // Puedes utilizar la variable tieneMultas en tu lógica según sea necesario

            // Crea un Intent para la nueva actividad
            Intent intent = new Intent(FVehicularJY.this, ActivityResultados.class);

            // Agrega los datos como extras al Intent
            intent.putExtra("NOMBRE", nombre);
            intent.putExtra("CEDULA", cedula);
            intent.putExtra("FECHA", fecha);
            intent.putExtra("TIENE_MULTAS", tieneMultas);
            intent.putExtra("PLACA", placa);
            intent.putExtra("ANIO", anio);
            intent.putExtra("MARCA", marcaSeleccionada);
            intent.putExtra("TIPO", tipoSeleccionado);

            startActivity(intent);

        } else {
            // Mostrar un mensaje de error si el nombre está vacío o la cédula no es numérica
            Toast.makeText(FVehicularJY.this, "Por favor, ingrese datos válidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void generarNotificacion() {
        String CHANNEL_ID = "com.ggvc.practicaobjetoslogicosmoviliipa2023.channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notificacion)
                .setContentTitle("APLICACION FICHA VEHICULAR")
                .setContentText("Aplicación PAGOS MULTAS VEHICULOS")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("HOLA ESTA ES MI NOTIFICACION UWU"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Obtén una instancia del NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Crea el canal de notificación para dispositivos con Android Oreo y superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal de Notificación";
            String description = "Canal para notificaciones de la aplicación";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Registra el canal en el NotificationManager
            notificationManager.createNotificationChannel(channel);
        }

        // Muestra la notificación
        notificationManager.notify(1, builder.build());
    }


    // Función auxiliar para verificar si una cadena es numérica
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
