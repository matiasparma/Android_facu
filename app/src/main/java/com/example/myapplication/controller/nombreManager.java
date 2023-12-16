package com.example.myapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class nombreManager {
    private static final String SHARED_PREFERENCES_NAME = "MiArchivoCompartido";
    private static final String KEY_TEXT = "clave_texto";

    private final SharedPreferences sharedPreferences;

    public nombreManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void guardarTexto(String texto) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TEXT, texto);
        editor.apply();
    }

    public String obtenerTexto() {
        return sharedPreferences.getString(KEY_TEXT, "");
    }

    public void eliminarTexto() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TEXT);
        editor.apply();
    }

    public void eliminarTodosLosDatos() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
