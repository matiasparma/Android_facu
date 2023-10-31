package com.example.myapplication;

import java.io.Serializable;

public class Carrito implements Serializable {
        public String nombre;
        public String color;
        public double precio;

        public double cantidad;

        public String descripcion;
        public String marca;
        public String codigo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Carrito(String codigo, String nombre, double precio, double cantidad, String desc, String marca) {
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
            this.descripcion=desc;
            this.marca=marca;
            this.codigo=codigo;
            this.total = precio * cantidad;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double total;

        public String getNombre() {
            return nombre;
        }

        public String getColor() {
            return color;
        }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
            return precio;
        }

        public double getTotal() {
            return total;
        }
    }



