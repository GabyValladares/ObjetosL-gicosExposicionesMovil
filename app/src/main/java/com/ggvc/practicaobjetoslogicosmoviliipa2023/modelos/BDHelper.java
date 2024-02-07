package com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREACIÓN DE LAS TABLAS
            db.execSQL("CREATE TABLE tblVehiculos"+"(" +
                    "veh_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "veh_placa text NOT NULL,"+
                    "veh_color text NOT NULL," +
                    "veh_marca text NOT NULL,"+
                    "veh_tipo text NOT NULL,"+
                    "veh_valor DOUBLE NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE tblVehiculos");
        onCreate(db);
    }
}
