package com.example.alumnos.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class modificar extends AppCompatActivity {
    EditText monto,descripcion; TextView fechactual;
    DatePicker fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        monto = (EditText) findViewById(R.id.txtmonto);
        descripcion = (EditText) findViewById(R.id.txtdescripcion);
        fechactual = (TextView) findViewById(R.id.textView2);
        fecha = (DatePicker) findViewById(R.id.dtpfecha);
        monto.setText(getIntent().getStringExtra("monto"));
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        fechactual.setText("fecha actual: "+getIntent().getStringExtra("fechactual"));
    }

    public void Actualizar (View view){
        int nuevomonto ;
        String nuevadescripcion,fechanueva;
        //---------------------------------------------------------
        try{
            nuevomonto =  Integer.parseInt(monto.getText().toString());
            nuevadescripcion =   descripcion.getText().toString();
            //---------------------------------------------------------
            int  Sdp_fechaDIA,Sdp_fechaMES,Sdp_fechaAÑO;
            Sdp_fechaDIA = fecha.getDayOfMonth();
            String mestemp="";
            Sdp_fechaAÑO = fecha.getYear();
            //---------------------------------------------------------
            int idgasto = Integer.parseInt(getIntent().getStringExtra("valor"));
            //---------------------------------------------------------
            if(fecha.getMonth()<10){
                mestemp = "0"+(fecha.getMonth()+1);
                fechanueva = String.valueOf(Sdp_fechaAÑO)+"-"+mestemp+"-"+String.valueOf(Sdp_fechaDIA);
            }else{
                Sdp_fechaMES = fecha.getMonth()+1;
                fechanueva = String.valueOf(Sdp_fechaAÑO)+"-"+String.valueOf(Sdp_fechaMES)+"-"+String.valueOf(Sdp_fechaDIA);
            }
            //---------------------------------------------------------

            //---------------------------------------------------------
            dbHelper baseHelper = new dbHelper(this,"gastobbdd",null,1);
            SQLiteDatabase db = baseHelper.getWritableDatabase();

            if (db != null) {

                ContentValues nuevos_datos = new ContentValues();
                nuevos_datos.put("monto",nuevomonto);
                nuevos_datos.put("descripcion",nuevadescripcion);
                nuevos_datos.put("fecha",fechanueva);


                long i = db.update("tbl_gasto",nuevos_datos,"idgasto ="+idgasto,null);
                if (i > 0) {
                    Toast.makeText(this, "GASTO ID: N°"+idgasto+" MODIFICADO CON EXITO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Error  con el ID: N°"+idgasto+"", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "El campo monto es incorrecto", Toast.LENGTH_SHORT).show();
        }
        //---------------------------------------------------------
    }
}
