package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
                valorArchivo= ArchivoTXTController.archivoExistente(archivos, VariablesGlobales.rutaUsuario);
                valorArchivo=ArchivoTXTController.archivoExistente(archivos,VariablesGlobales.rutaUsuario);
                Toast.makeText(BienvenidaActivity.this," "+valorArchivo,Toast.LENGTH_SHORT).show();
                valorTXT=ArchivoTXTController.verificarTXT(BienvenidaActivity.this," ");
                ActivityController.abrirActivity(BienvenidaActivity.this,valorArchivo,valorTXT,"matias",VariablesGlobales.administrador);
                //Cierra MainActivity para que no pueda volver atrás con el botón "Atrás"
                finish();
            }
        }, TIEMPO_ESPERA);
    }
}
