package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class Informacoes  implements Serializable {

    protected String NomeEventoInformacao;
    protected String DescEventoInformacao;
    protected int CodEvento;

    public Informacoes(String nomeEventoInformacao, String descEventoInformacao, int codEvento) {
        NomeEventoInformacao = nomeEventoInformacao;
        DescEventoInformacao = descEventoInformacao;
        CodEvento = codEvento;
    }

    public String getNomeEventoInformacao() {
        return NomeEventoInformacao;
    }

    public void setNomeEventoInformacao(String nomeEventoInformacao) {
        NomeEventoInformacao = nomeEventoInformacao;
    }

    public String getDescEventoInformacao() {
        return DescEventoInformacao;
    }

    public void setDescEventoInformacao(String descEventoInformacao) {
        DescEventoInformacao = descEventoInformacao;
    }

    public int getCodEvento() {
        return CodEvento;
    }

    public void setCodEvento(int codEvento) {
        CodEvento = codEvento;
    }
}
