package com.example.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.VariablesGlobales;
import com.example.myapplication.activity.ClienteActivity;
import com.example.myapplication.activity.ContenidoPedidoActivity;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.datos.DDBBCarrito;
import com.example.myapplication.fragment.CartFragment;

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
            nombreManager manager=new nombreManager(context);
            boolean tieneElementos= DDBBCarrito.tieneElementos(context);

            if(!tieneElementos){
                manager.eliminarTexto();
                ActivityController.abrirCliente(context);
            }
            else {
                abrirMain(context);
            }
            VariablesGlobales.nombre =manager.obtenerTexto();
            //Toast.makeText(context, ""+VariablesGlobales.nombre, Toast.LENGTH_SHORT).show();


        }
        else{
            abrirLogin(context);
        }
    }
    public static void abrirCliente(Context context){
        Intent abreCliente = new Intent(context, ClienteActivity.class);
        abreCliente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(abreCliente);
    }
    public static void abrirContenidoPedido(Context context){
        Intent abrePedido = new Intent(context, ContenidoPedidoActivity.class);
        abrePedido.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(abrePedido);
    }



}
