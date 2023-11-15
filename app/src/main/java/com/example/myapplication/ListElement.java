package com.example.myapplication;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String nombre;
    public String city;
    public String precio;

    public String descripcion;

    public String marca;

    public String getDescripcion() {
        return descripcion;
    }
    public String codigo;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getCodigo() {
        return codigo;
    }
    public ListElement(){}

    public ListElement(String codigo, String nombre, String city, String precio, String desc, String marca) {

        this.nombre = nombre;
        this.city = city;
        this.precio = precio;
        this.descripcion=desc;
        this.marca=marca;
        this.codigo=codigo;
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String status) {
        this.precio = status;
    }
}
