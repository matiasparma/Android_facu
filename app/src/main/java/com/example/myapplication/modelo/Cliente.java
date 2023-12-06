package com.example.myapplication.modelo;

public class Cliente {
    private String id;
    private String razonSocial;
    private String tipoUsuario;

    public Cliente(String id, String razonSocial, String tipoUsuario) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.tipoUsuario = tipoUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
