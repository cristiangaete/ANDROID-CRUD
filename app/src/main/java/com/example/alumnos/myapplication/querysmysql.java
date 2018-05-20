package com.example.alumnos.myapplication;

/**
 * Created by Alumnos on 30-11-2017.
 */

import android.app.DownloadManager;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class querysmysql {

    public void insertar_mysql(int monto, String descripcion, int posicion, String fecha){
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "https://ciisaggwp.000webhostapp.com/insertarDatos.php";//conexion para archivo php
        String parametros = "?monto=" + monto + "&id_item=" + posicion + "&descrip=" + descripcion +  "&fecha=" + fecha;
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String resultado = new String(responseBody);
                    //Toast.makeText(MainActivity.this,"enviado con exito "+resultado,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String resultado = new String(responseBody);
                //Toast.makeText(this,"fallo "+resultado,Toast.LENGTH_SHORT).show();
            }
        });
    }




}
