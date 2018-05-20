package com.example.alumnos.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Spinner sp1;
    EditText edt1, edt2;
    DatePicker dtp1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);
        dtp1 = (DatePicker) findViewById(R.id.datePicker);

        inflar();
    }


    public void inflar (){
        dbHelper baseHelper = new dbHelper(this,"gastobbdd",null,1);
        SQLiteDatabase db = baseHelper.getReadableDatabase();

        if(db!=null){
            Cursor c =  db.rawQuery("SELECT * FROM tbl_item",null);
            int cantidad = c.getCount();
            int i = 0;
            String[] arreglo = new String [cantidad];
            if(c.moveToFirst()){
                do {
                    String linea = c.getString(0)+" "+c.getString(1);
                    arreglo[i] = linea;
                    i++;
                }while(c.moveToNext());
            }
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);
            sp1 = (Spinner) findViewById(R.id.spinner);
            sp1.setAdapter(adapter);

        }
    }

    public void insertar (View view) {
        dbHelper baseHelper = new dbHelper(this,"gastobbdd",null,1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        try {
                String descripcion = "", fecha = "";
                int  Sdp_fechaDIA,Sdp_fechaMES,Sdp_fechaAÑO;
                int monto = 0;
                descripcion = edt2.getText().toString();
                monto = Integer.parseInt(edt1.getText().toString());
                Sdp_fechaDIA = dtp1.getDayOfMonth();
                String mestemp="";
            Sdp_fechaAÑO = dtp1.getYear();
                if(dtp1.getMonth()<10){
                    mestemp = "0"+(dtp1.getMonth()+1);
                    fecha = String.valueOf(Sdp_fechaAÑO)+"-"+mestemp+"-"+String.valueOf(Sdp_fechaDIA);
                }else{
                    Sdp_fechaMES = dtp1.getMonth()+1;
                    fecha = String.valueOf(Sdp_fechaAÑO)+"-"+String.valueOf(Sdp_fechaMES)+"-"+String.valueOf(Sdp_fechaDIA);
                }
                int position = sp1.getSelectedItemPosition()+1;
                Toast.makeText(this, " "+monto+" "+" "+descripcion+" "+" "+position+" "+" "+fecha+"", Toast.LENGTH_SHORT).show();
                if (db != null) {
                    ContentValues registro = new ContentValues();
                    registro.put("monto", monto);
                    registro.put("descripcion", descripcion);
                    registro.put("fecha", fecha);
                    registro.put("itemID", position);
                    long i = db.insert("tbl_gasto", null, registro);
                    if (i > 0) {
                        Toast.makeText(this, "Todo ok: ", Toast.LENGTH_SHORT).show();
                        querysmysql query = new querysmysql();
                        query.insertar_mysql(monto,descripcion,position,fecha);
                    } else {
                        Toast.makeText(this, "Error..al insertar", Toast.LENGTH_SHORT).show();
                    }
                }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error..al insertar", Toast.LENGTH_LONG).show();
        }
    }


    public void enviar(View v){

        Intent intent = new Intent(this,consultar.class);
        startActivity(intent);

    }
}

