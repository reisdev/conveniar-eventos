package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class Event implements Serializable {
    protected int CodEvent;
    protected String NomeEvento;
    protected String StatusEvento;
    protected String NumeroVagas;
    protected String NomeEventoInformacao;
    protected String DescEventoInformacao;

    public Event(int codEvent, String nomeEvento, String statusEvento, String numeroVagas, String nomeEventoInformacao, String descEventoInformacao) {
        CodEvent = codEvent;
        NomeEvento = nomeEvento;
        StatusEvento = statusEvento;
        NumeroVagas = numeroVagas;
        NomeEventoInformacao = nomeEventoInformacao;
        DescEventoInformacao = descEventoInformacao;
    }

    public int getCodEvent() {
        return CodEvent;
    }

    public String getNomeEvento() {
        return NomeEvento;
    }

    public String getStatusEvento() {
        return StatusEvento;
    }

    public String getNumeroVagas() {
        return NumeroVagas;
    }

    public String getNomeEventoInformacao() {
        return NomeEventoInformacao;
    }

    public String getDescEventoInformacao() {
        return DescEventoInformacao;
    }
}
