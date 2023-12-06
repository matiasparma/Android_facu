package com.example.myapplication.Adapter.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.VariablesGlobales;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LoginController {
    public static boolean verificarCampo(TextView t, Context context){
        boolean validacion=true;
        if (t.getText().length()==0)validacion=false;
        return validacion;
    }
    public static void guardarDatosTxt(TextView username,Context context){
        boolean valor=verificarCampo(username,context);
        String nombre=username.getText().toString();
        if (valor){
            try {
                OutputStreamWriter datoUsuario=new OutputStreamWriter(context.openFileOutput(VariablesGlobales.PREF_NAME, Activity.MODE_PRIVATE));
                datoUsuario.write(username.getText().toString());
                datoUsuario.flush();
                datoUsuario.close();
                abrirClienteOHome(nombre,context);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else Toast.makeText(context,"campo vacio",Toast.LENGTH_SHORT).show();

    }
    public static void abrirClienteOHome(String username, Context context){
        ActivityController.abrirClienteOHome(username,"matias",context);
    }

}
