package com.example.alumnos.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {

    String tbl_item = "CREATE TABLE tbl_item (iditem INTEGER PRIMARY KEY AUTOINCREMENT, nombreItem TEXT);";
    String spinner_items = "INSERT INTO tbl_item VALUES (null,'Tiendas'),(null,'Supermercado'),(null,'Vestuario'),(null,'Cuentas'),(null,'Autopistas');";
    String tbl_gasto = "CREATE TABLE tbl_gasto (idgasto INTEGER PRIMARY KEY AUTOINCREMENT, monto INTEGER, descripcion TEXT, fecha DATETIME,itemID INTEGER, FOREIGN KEY (itemID) REFERENCES tbl_item(iditem));";

    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(tbl_item);
        db.execSQL(spinner_items);
        db.execSQL(tbl_gasto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
