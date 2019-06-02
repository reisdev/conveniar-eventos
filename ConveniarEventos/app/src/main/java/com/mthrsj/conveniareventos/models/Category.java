package com.mthrsj.conveniareventos.models;

public class Category {

    protected int CodEventoCategoria;
    protected String NomeEventoCategoria;

    public Category(int codEventoCategoria, String nomeEventoCAtegoria) {
        CodEventoCategoria = codEventoCategoria;
        NomeEventoCategoria = nomeEventoCAtegoria;
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
