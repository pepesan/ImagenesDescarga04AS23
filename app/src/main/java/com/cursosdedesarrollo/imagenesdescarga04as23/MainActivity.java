package com.cursosdedesarrollo.imagenesdescarga04as23;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.texto_cargando);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {
            Log.d("Boton Descargar", "Pulsado");
            //downloadAction();

            MiTarea tarea=new MiTarea();
            tarea.execute(new Void[]{null});
            return true;
        }
        if (id == R.id.action_reset) {
            Log.d("Boton Reset", "Pulsado");
            //resetAction();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static public class MiTarea extends AsyncTask<Void,Void,Void> {
        protected void onPreExecute(){
            tv.setText("Descargando");
            //cargando.setVisibility(View.VISIBLE);
            //descarga.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*try {
                downloadBitmap =
                        downloadBitmap(params[0]);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return downloadBitmap;
            */
            return null;
        }
        protected void onPostExecute(Void bm){
            tv.setText("Descargado");
            /*
            imageView.setImageBitmap(bm);
            //dialog.dismiss();
            cargando.setVisibility(View.GONE);
            //descarga.setEnabled(true);
            */
        }

    }
}
