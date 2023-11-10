package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTXTController {
    public static boolean archivoExistente(String archivo[], String nombreArchivo) {
        boolean validacion=false;
        for (int i = 0; i < archivo.length; i++) {
            if (archivo[i].equals(nombreArchivo)) {
                validacion=true;
            }
        }
        return validacion;
    }

    public static boolean verificarTXT(Context context, String valorStringTXT){
        boolean valorTxt=true;
        try {
            InputStreamReader archivo=new InputStreamReader(context.openFileInput(VariablesGlobales.PREF_NAME));
            BufferedReader br=new BufferedReader(archivo);
            String valorLinea=br.readLine().toString();
            if(valorLinea.equals(valorStringTXT)) valorTxt=false;
            archivo.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valorTxt;
    }

    public static void resetearTXT(Context context){
        try {
            OutputStreamWriter datoUsuario=new OutputStreamWriter(context.openFileOutput(VariablesGlobales.PREF_NAME, Activity.MODE_PRIVATE));
            Toast.makeText(context, ""+VariablesGlobales.PREF_NAME, Toast.LENGTH_SHORT).show();
            datoUsuario.write(" ");
            datoUsuario.flush();
            datoUsuario.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

