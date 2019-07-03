package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;

public class User implements Serializable {
    private int CodPessoaEvento;
    private int NumRegistro;
    private String Nome;
    private String Cracha;
    private String Email;
    private String TelefoneCelular;
    private String TelefoneCasa;
    private String TelefoneEmpresa;
    private String Endereco;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String Pais;

    public User(int codPessoaEvento, int numRegistro, String nome, String cracha, String email, String telefoneCelular, String telefoneCasa, String telefoneEmpresa, String endereco, String bairro, String cidade, String estado, String pais) {
        CodPessoaEvento = codPessoaEvento;
        NumRegistro = numRegistro;
        Nome = nome;
        Cracha = cracha;
        Email = email;
        TelefoneCelular = telefoneCelular;
        TelefoneCasa = telefoneCasa;
        TelefoneEmpresa = telefoneEmpresa;
        Endereco = endereco;
        Bairro = bairro;
        Cidade = cidade;
        Estado = estado;
        Pais = pais;
    }

    public int getCodPessoaEvento() {
        return CodPessoaEvento;
    }

    public void setCodPessoaEvento(int codPessoaEvento) {
        CodPessoaEvento = codPessoaEvento;
    }

    public int getNumRegistro() {
        return NumRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        NumRegistro = numRegistro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCracha() {
        return Cracha;
    }

    public void setCracha(String cracha) {
        Cracha = cracha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefoneCelular() {
        return TelefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        TelefoneCelular = telefoneCelular;
    }

    public String getTelefoneCasa() {
        return TelefoneCasa;
    }

    public void setTelefoneCasa(String telefoneCasa) {
        TelefoneCasa = telefoneCasa;
    }

    public String getTelefoneEmpresa() {
        return TelefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        TelefoneEmpresa = telefoneEmpresa;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }
}
