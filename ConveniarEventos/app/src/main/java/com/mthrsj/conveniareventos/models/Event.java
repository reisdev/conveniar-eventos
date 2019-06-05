package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class Event implements Serializable {
    protected int CodEvent;
    protected String NomeEvento;
    protected String StatusEvento;
    protected String NumeroVagas;
    protected Category informacoes;

    public Event(int codEvent, String nomeEvento, String statusEvento, String numeroVagas, Category informacoes) {
        CodEvent = codEvent;
        NomeEvento = nomeEvento;
        StatusEvento = statusEvento;
        NumeroVagas = numeroVagas;
        this.informacoes = informacoes;
    }

    public Event(Event e){
        CodEvent = e.CodEvent;
        NomeEvento = e.NomeEvento;
        StatusEvento = e.StatusEvento;
        NumeroVagas = e.NumeroVagas;
        this.informacoes = e.informacoes;
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

    public Category getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(Category informacoes) {
        this.informacoes = informacoes;
    }
}
