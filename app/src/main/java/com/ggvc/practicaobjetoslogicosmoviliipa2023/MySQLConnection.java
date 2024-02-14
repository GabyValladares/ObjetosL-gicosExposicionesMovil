package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnection extends SQLiteOpenHelper {
    public MySQLConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tblvehiculo"+"(" +
                "vehiculo_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre text NOT NULL,"+
                "cedula text NOT NULL," +

                "pagarMatricula text NOT NULL,"+
                "PagoMultas text NOT NULL,"+
                "matriculacion text NOT NULL,"+
                "pagoCont text NOT NULL,"+
                "total text NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE tblvehiculo");
        onCreate(db);
    }
}
