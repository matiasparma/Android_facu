package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

public class ActivityController {
    public static void abrirMain(Context context){
        Intent abreMain = new Intent(context, MainActivity.class);
        abreMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(abreMain);
    }
    public static void abrirLogin(Context context){
        Intent abreLogin = new Intent(context, LoginActivity.class);
        abreLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(abreLogin);
    }
    public static void abrirClienteOHome(String username,String nombreAcomparar, Context context){
        if (username.equals(nombreAcomparar)) abrirMain(context);
        else abrirLogin(context);
    }
    public static void abrirActivity(Context context, boolean validacionArchivo,boolean validacionTXT,String username,String administrador){
        if(!validacionArchivo || validacionTXT){
           abrirMain(context);
            // abrirClienteOHome(username,administrador,context);
        }
        else{
            abrirLogin(context);
        }
    }
}
