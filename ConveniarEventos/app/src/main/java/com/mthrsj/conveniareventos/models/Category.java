package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class Category implements Serializable {

    protected int CodEventoCategoria;
    protected String NomeEventoCategoria;

    public Category(int cod, String nome) {
        CodEventoCategoria = cod;
        NomeEventoCategoria = nome;
    }

    public int getCodEventoCategoria() {
        return CodEventoCategoria;
    }

    public void setCodEventoCategoria(int codEventoCategoria) {
        CodEventoCategoria = codEventoCategoria;
    }

    public String getNomeEventoCategoria() {
        return NomeEventoCategoria;
    }

    public void setNomeEventoCAtegoria(String nomeEventoCAtegoria) {
        NomeEventoCategoria = nomeEventoCAtegoria;
    }
}
