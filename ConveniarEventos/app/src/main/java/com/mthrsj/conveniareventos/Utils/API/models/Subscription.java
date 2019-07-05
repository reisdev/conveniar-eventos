package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;

public class Subscription implements Serializable {
    private int CodEventoInscricao;
    private int CodEvento;
    private String NomeEvento;
    private String NomeStatus;

    private String NomeCategoriaInscricao;
    private String Inscrito;
    private int NumeroInscricao;

    public Subscription(int codEventoInscricao, int codEvento, String nomeStatus, String nomeEvento, String nomeCategoriaInscricao, String inscrito, int numeroInscricao) {
        CodEventoInscricao = codEventoInscricao;
        CodEvento = codEvento;
        NomeEvento = nomeEvento;
        NomeStatus = nomeStatus;
        NomeCategoriaInscricao = nomeCategoriaInscricao;
        Inscrito = inscrito;
        NumeroInscricao = numeroInscricao;
    }

    public int getCodEventoInscricao() {
        return CodEventoInscricao;
    }

    public void setCodEventoIsncricao(int codEventoIsncricao) {
        CodEventoInscricao = codEventoIsncricao;
    }

    public int getCodEvento() {
        return CodEvento;
    }

    public void setCodEvento(int codEvento) {
        CodEvento = codEvento;
    }

    public String getNomeEvento() {
        return NomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        NomeEvento = nomeEvento;
    }

    public String getNomeCategoriaInscricao() {
        return NomeCategoriaInscricao;
    }

    public void setNomeCategoriaInscricao(String nomeCategoriaInscricao) {
        NomeCategoriaInscricao = nomeCategoriaInscricao;
    }

    public int getNumeroInscricao() {
        return NumeroInscricao;
    }

    public void setNumeroInscricao(int numeroInscricao) {
        NumeroInscricao = numeroInscricao;
    }

    public String getNomeStatus() {
        return NomeStatus;
    }

    public String getInscrito() {
        return Inscrito;
    }
}
