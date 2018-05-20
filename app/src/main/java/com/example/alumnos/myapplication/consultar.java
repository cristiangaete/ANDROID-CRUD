package com.example.alumnos.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cz.msebera.android.httpclient.Header;

public class consultar extends AppCompatActivity {


    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        obDatos();

        listview = (ListView) findViewById(R.id.listview);

    }
    public void obDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="https://ciisaggwp.000webhostapp.com/mostrar.php";

        RequestParams parametros = new RequestParams();
        parametros.put("id_detalle",1);
        parametros.put("monto",2);
        parametros.put("id_item",3);
        parametros.put("descrip",4);
        parametros.put("fecha",5);

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200) {
                    CargarLista(obtDatosJason(new String(responseBody)));



                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }
    public void CargarLista(ArrayList<String>datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,datos);
        listview.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJason(String response){
        ArrayList<String> list = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i=0;i<jsonArray.length();i++){
                texto= jsonArray.getJSONObject(i).getString("monto") +" "+
                        jsonArray.getJSONObject(i).getString("id_item")+" "+
                        jsonArray.getJSONObject(i).getString("descrip")+" "+
                        jsonArray.getJSONObject(i).getString("fecha")+ " ";
                list.add(texto);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
