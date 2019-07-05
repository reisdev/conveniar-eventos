package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;

public class Subscription implements Serializable {
    private int CodEventoIsncricao;
    private int CodEvento;
    private String NomeEvento;
    private String NomeCategoriaInscricao;
    private String Isncrito;
    private int NumeroInscricao;

    public Subscription(int codEventoIsncricao, int codEvento, String nomeEvento, String nomeCategoriaInscricao, String isncrito, int numeroInscricao) {
        CodEventoIsncricao = codEventoIsncricao;
        CodEvento = codEvento;
        NomeEvento = nomeEvento;
        NomeCategoriaInscricao = nomeCategoriaInscricao;
        Isncrito = isncrito;
        NumeroInscricao = numeroInscricao;
    }

    public int getCodEventoIsncricao() {
        return CodEventoIsncricao;
    }

    public void setCodEventoIsncricao(int codEventoIsncricao) {
        CodEventoIsncricao = codEventoIsncricao;
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

    public String getIsncrito() {
        return Isncrito;
    }

    public void setIsncrito(String isncrito) {
        Isncrito = isncrito;
    }

    public int getNumeroInscricao() {
        return NumeroInscricao;
    }

    public void setNumeroInscricao(int numeroInscricao) {
        NumeroInscricao = numeroInscricao;
    }
}
