package com.example.myapplication.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.modelo.Carrito;

import java.util.ArrayList;
import java.util.List;

public class DDBBCarrito {

    public static void registrar(Context context,int codigo, String nombre, double precio, int stock, String descripcion, String marca) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Articulos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        ContentValues articulo = new ContentValues();
        articulo.put("codigo",codigo);
        articulo.put("nombre", nombre);
        articulo.put("precio", precio);
        articulo.put("descripcion", descripcion);
        articulo.put("marca", marca);
        articulo.put("stock", stock);
        BaseDeDatos.insert("articulos", null, articulo);
        BaseDeDatos.close();
    }
    public static List<Carrito> obtenerArticulos(Context context) {
        List<Carrito> articulos = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Articulos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        Cursor cursor = BaseDeDatos.query("articulos", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int codigo = cursor.getInt(cursor.getColumnIndexOrThrow("codigo"));
                    String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                    double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));
                    String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                    String marca = cursor.getString(cursor.getColumnIndexOrThrow("marca"));
                    int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));

                    String codString=String.valueOf(codigo);
                    Carrito articulo = new Carrito(codString,nombre, precio, stock, descripcion,marca);
                    articulos.add(articulo);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        BaseDeDatos.close();

        return articulos;
    }

    public static void eliminarBD(Context context){
        context.deleteDatabase("Articulos");

    }
    public static void eliminarArti(Context context, String codigo){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Articulos", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        int cantidad=baseDeDatos.delete("Articulos","codigo="+codigo,null);
        baseDeDatos.close();
    }

    public static double obtenertotal(Context context) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Articulos", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        Cursor cursor = BaseDeDatos.query("articulos", null, null, null, null, null, null);
        double total=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));
                    int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                    total=total+(precio*stock);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        BaseDeDatos.close();

        return total;
    }
}


