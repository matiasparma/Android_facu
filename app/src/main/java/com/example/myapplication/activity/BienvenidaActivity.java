package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.myapplication.controller.ActivityController;
import com.example.myapplication.controller.ArchivoTXTController;
import com.example.myapplication.R;
import com.example.myapplication.controller.TokenManager;
import com.example.myapplication.VariablesGlobales;

public class BienvenidaActivity extends AppCompatActivity {
    boolean valorArchivo;
    boolean valorTXT;
    private static final int TIEMPO_ESPERA=200;
    String archivos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        archivos = fileList();

    }
    public void abrir(View v){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                valorArchivo=ArchivoTXTController.archivoExistente(archivos,VariablesGlobales.PREF_NAME);
               // valorTXT=ArchivoTXTController.verificarTXT(BienvenidaActivity.this," ");
                TokenManager tokenManager=TokenManager.getInstance(BienvenidaActivity.this);
                String token=tokenManager.getToken();
                ActivityController.abrirActivity(BienvenidaActivity.this,valorArchivo,valorTXT,token);
                //Cierra MainActivity para que no pueda volver atrás con el botón "Atrás"
                finish();
            }
        }, TIEMPO_ESPERA);
    }
}
