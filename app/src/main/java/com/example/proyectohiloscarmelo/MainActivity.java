package com.example.proyectohiloscarmelo;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Instancias
    int contThread =0, contAsync = 0;
    TextView tvThread, tvAsyn;
    Button btThread, btAsync, btDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        tvAsyn = findViewById(R.id.tvAsyn);
        tvThread = findViewById(R.id.tvThread);
        btThread = findViewById(R.id.btThread);
        btThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contThread == 0){
                    Hilo hilo = new Hilo();
                    hilo.start();
                }
            }
        });

        btAsync = findViewById(R.id.btAsyn);
        btAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contAsync == 0){
                    new MiAsyncTask().execute();
                    tvAsyn.setText(""+contAsync);
                }
            }
        });

        btDos = findViewById(R.id.btDos);
        btDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contThread == 0){
                    Hilo hilo = new Hilo();
                    hilo.start();
                }
                if (contAsync == 0){
                    new MiAsyncTask().execute();
                    tvAsyn.setText(""+contAsync);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void espera(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Hilo extends Thread{
        @Override
        public void run() {
            for (int i=0; i<=10; i++){
                espera(1000);
                tvThread.post(new Runnable() {
                    @Override
                    public void run() {
                        contThread++;
                        tvThread.setText(""+ contThread);
                    }
                });
            }
            contThread = -1;
        }
    }

    private class MiAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i=0; i<=10; i++){
                espera(1000);
                tvAsyn.post(new Runnable() {
                    @Override
                    public void run() {
                        contAsync++;
                        tvAsyn.setText(""+ contAsync);
                    }
                });
            }
            contAsync = -1;
            return null;
        }
    }
}
