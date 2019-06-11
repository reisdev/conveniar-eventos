package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {
    private int CodEvent;
    private String NomeEvento;
    private String Situacao;
    private String DataInicio;
    private String Categoria;
    private String DataFim;
    private int NumeroVagas;
    private Informacoes Informacoes;

    public Event(int codEvent, String nomeEvento, String Situacao, int numeroVagas, Informacoes informacoes, String dataInicio, String dataFim) {
        CodEvent = codEvent;
        NomeEvento = nomeEvento;
        Situacao = Situacao;
        NumeroVagas = numeroVagas;
        Informacoes = informacoes;
        DataInicio = dataInicio;
        DataFim = dataFim;
    }

    public Event(Event e) {
        CodEvent = e.CodEvent;
        NomeEvento = e.NomeEvento;
        Situacao = e.Situacao;
        NumeroVagas = e.NumeroVagas;
        Informacoes = e.Informacoes;
        DataInicio = e.DataInicio;
        DataFim = e.DataFim;
    }

    public String getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(String dataInicio) {
        DataInicio = dataInicio;
    }

    public String getDataFim() {
        return DataFim;
    }

    public void setDataFim(String dataFim) {
        DataFim = dataFim;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public int getCodEvent() {
        return CodEvent;
    }

    public void setCodEvent(int codEvent) {
        CodEvent = codEvent;
    }

    public String getNomeEvento() {
        return NomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        NomeEvento = nomeEvento;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        Situacao = Situacao;
    }

    public int getNumeroVagas() {
        return NumeroVagas;
    }

    public void setNumeroVagas(int numeroVagas) {
        NumeroVagas = numeroVagas;
    }

    public Informacoes getInformacoes() {
        return Informacoes;
    }

    public void setInformacoes(Informacoes informacoes) {
        Informacoes = informacoes;
    }

}