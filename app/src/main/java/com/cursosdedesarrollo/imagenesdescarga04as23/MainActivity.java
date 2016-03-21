package com.cursosdedesarrollo.imagenesdescarga04as23;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //public static TextView tv;
    public static ImageView iv;
    public static ProgressBar pb;
    private static String url="http://www.telecinco.es/todoelmundoesbueno/pilar-rubio-estrena-todo-el-mundo-es-bueno_MDSVID20120625_0117_4.jpg";
    public static Bitmap bm=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv=(TextView)findViewById(R.id.texto_cargando);
        iv=(ImageView)findViewById(R.id.imageView);
        pb=(ProgressBar)findViewById(R.id.cargando);
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
            if(bm==null) {
                Log.d("Boton Descargar", "No la tengo, a descargo");
                MiTarea tarea = new MiTarea();
                tarea.execute(new String[]{url});
            }else{
                Log.d("Boton Descargar", "Ya la tengo, no la descargo");
                iv.setImageBitmap(bm);
                Toast.makeText(
                        getApplicationContext(),
                        "Ya está descargada! Te das CUEN! JARL!",
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (id == R.id.action_reset) {
            Log.d("Boton Reset", "Pulsado");
            //resetAction();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MiTarea extends AsyncTask<String,Integer,Bitmap> {
        protected void onPreExecute(){
            //tv.setText("Descargando");
            pb.setVisibility(View.VISIBLE);
            iv.setVisibility(View.INVISIBLE);
            //cargando.setVisibility(View.VISIBLE);
            //descarga.setEnabled(false);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("Values",values[0].toString());
            Toast.makeText(
                    MainActivity.this,
                    "Datos desde la AsyncTask: "+values[0].toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            /*
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
            publishProgress(new Integer[]{new Integer(0)});
            bm=getBitmapFromURL(params[0]);
            /*try {
                downloadBitmap =
                        downloadBitmap(params[0]);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return downloadBitmap;
            */
            publishProgress(new Integer[]{new Integer(100)});
            return bm;
        }
        protected void onPostExecute(Bitmap bm){
            //tv.setText("Descargado");
            iv.setImageBitmap(bm);
            pb.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
            /*
            imageView.setImageBitmap(bm);
            //dialog.dismiss();
            cargando.setVisibility(View.GONE);
            //descarga.setEnabled(true);
            */
        }

    }
    static public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
