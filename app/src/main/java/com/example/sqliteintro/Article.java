package com.example.sqliteintro;

import java.io.Serializable;

public class Article implements Serializable {

    private String codiArticle;
    private String descripcio;
    private float PVP;
    private int stock;

    public Article(String codiArticle, String descripcio, float PVP, int stock) {
        this.codiArticle = codiArticle;
        this.descripcio = descripcio;
        this.PVP = PVP;
        this.stock = stock;
    }

    public String getCodiArticle() {
        return codiArticle;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public float getPVP() {
        return PVP;
    }

    public int getStock() {
        return stock;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setPVP(float PVP) {
        this.PVP = PVP;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
