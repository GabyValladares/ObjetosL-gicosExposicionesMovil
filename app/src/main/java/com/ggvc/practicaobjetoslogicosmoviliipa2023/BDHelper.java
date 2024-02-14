package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        db.execSQL("CREATE TABLE tblVehiculo"+"(" +
                "cedula INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre text NOT NULL,"+
                "apellido text NOT NULL," +
                "precio integer NOT NULL,"+
                "marca text NOT NULL,"+
                "color text NOT NULL,"+
                "multas text NOT NULL,"+
                "año text NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE tblUsuarios");
        onCreate(db);
    }
}