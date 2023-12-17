package com.example.myapplication.modelo;

public class Pedido {
    private int numeroComprobante;
    private int codigoUsuario;
    private String codigoArticulo;
    private double precio;
    private int cantidad;
    private String estado;

    public Pedido(int numeroComprobante, int codigoUsuario, String codigoArticulo, double precio, int cantidad, String estado) {
        this.numeroComprobante = numeroComprobante;
        this.codigoUsuario = codigoUsuario;
        this.codigoArticulo = codigoArticulo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }
}
