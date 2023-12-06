package com.example.myapplication.modelo;

public class ArticuloPedido {
    private int idArticulo;
    private double amount;
    private int quantity;

    public ArticuloPedido(int idArticulo, double amount, int quantity) {
        this.idArticulo = idArticulo;
        this.amount = amount;
        this.quantity = quantity;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public double getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }
}

