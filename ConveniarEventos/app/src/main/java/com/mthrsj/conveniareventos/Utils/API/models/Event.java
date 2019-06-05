package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {
    private int CodEvent;
    private String NomeEvento;
    private String StatusEvento;
    private String NumeroVagas;
    private List<Informacoes> Informacoes;

    public Event(int codEvent, String nomeEvento, String statusEvento, String numeroVagas, List<Informacoes> informacoes) {
        CodEvent = codEvent;
        NomeEvento = nomeEvento;
        StatusEvento = statusEvento;
        NumeroVagas = numeroVagas;
        Informacoes.addAll(informacoes);
    }

    public Event(Event e){
        CodEvent = e.CodEvent;
        NomeEvento = e.NomeEvento;
        StatusEvento = e.StatusEvento;
        NumeroVagas = e.NumeroVagas;
        Informacoes = e.Informacoes;
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

    public List<Informacoes> getInformacoes() {
        return Informacoes;
    }

    public void setInformacoes(List<Informacoes> informacoes) {
        Informacoes = informacoes;
    }
}
