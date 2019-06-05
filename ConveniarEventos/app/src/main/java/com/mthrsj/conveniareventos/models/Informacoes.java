package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class Informacoes implements Serializable {
    private String NomeEventoInformacao;
    private String DescEventoInformacao;
    private int CodEvento;

    public Informacoes(String nomeEventoInformacao, String descEventoInformacao) {
        NomeEventoInformacao = nomeEventoInformacao;
        DescEventoInformacao = descEventoInformacao;
    }

    public Informacoes(Informacoes copy) {
        NomeEventoInformacao = copy.getNomeEventoInformacao();
        DescEventoInformacao = copy.getDescEventoInformacao();
        CodEvento = copy.CodEvento;
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
