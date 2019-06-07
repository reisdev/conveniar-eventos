package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;

public class URLS implements Serializable {
    private String Autenticacao;
    private String Transparencia;
    private String Eventos;
    private String Coordenador;
    private String Fundacao;
    private String Fornecedor;

    public URLS(){
        Autenticacao = "";
        Transparencia = "";
        Eventos = "";
        Coordenador = "";
        Fundacao = "";
        Fornecedor = "";
    }

    public URLS(String autenticacao, String transparencia, String eventos, String coordenador, String fundacao, String fornecedor) {
        Autenticacao = autenticacao;
        Transparencia = transparencia;
        Eventos = eventos;
        Coordenador = coordenador;
        Fundacao = fundacao;
        Fornecedor = fornecedor;
    }

    public URLS(URLS c){
        Autenticacao = c.Autenticacao;
        Transparencia = c.Transparencia;
        Eventos = c.Eventos;
        Coordenador = c.Coordenador;
        Fundacao = c.Fundacao;
        Fornecedor = c.Fornecedor;
    }

    public String getAutenticacao() {
        return Autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        Autenticacao = autenticacao;
    }

    public String getTransparencia() {
        return Transparencia;
    }

    public void setTransparencia(String transparencia) {
        Transparencia = transparencia;
    }

    public String getEventos() {
        return Eventos;
    }

    public void setEventos(String eventos) {
        Eventos = eventos;
    }

    public String getCoordenador() {
        return Coordenador;
    }

    public void setCoordenador(String coordenador) {
        Coordenador = coordenador;
    }

    public String getFundacao() {
        return Fundacao;
    }

    public void setFundacao(String fundacao) {
        Fundacao = fundacao;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        Fornecedor = fornecedor;
    }
}
