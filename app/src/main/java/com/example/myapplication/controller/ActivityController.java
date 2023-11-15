package com.example.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.MainActivity;

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
    public static void abrirActivity(Context context, boolean validacionArchivo,boolean validacionTXT,String token){
        if(!validacionArchivo && token!=null){
           abrirMain(context);
        }
        else{
            abrirLogin(context);
        }
    }
}
