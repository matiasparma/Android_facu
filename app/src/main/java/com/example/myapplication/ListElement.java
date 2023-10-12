package com.example.myapplication;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String color;
    public String nombre;
    public String city;
    public String precio;

    public ListElement(String color, String nombre, String city, String precio) {
        this.color = color;
        this.nombre = nombre;
        this.city = city;
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
