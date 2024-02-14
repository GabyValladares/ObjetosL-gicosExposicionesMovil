//package com.ggvc.practicaobjetoslogicosmoviliipa2023;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.CheckBox;
//
//public class Aldas_chekcbox extends AppCompatActivity {
//
//    private  static final String PREFS_NAME = "MyPrefsFile";
//    private  static final String DARK_MODE_KEY="darkMoney";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_aldas_chekcbox);
//
//        final CheckBox showButtonCheckBox=findViewById(R.id.checkBox1);
//        final Button myButton = findViewById(R.id.aldaz);
//        final CheckBox darKModelChecBox= findViewById(R.id.checkBox2);
//        darKModelChecBox.setChecked(isDarkModeEnabed());
//        darKModelChecBox.setOnCheckedChangeListener((buttonView,isChecked));
//    }
//}