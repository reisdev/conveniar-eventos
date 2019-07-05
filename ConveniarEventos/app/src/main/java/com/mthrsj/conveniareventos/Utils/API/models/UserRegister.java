package com.mthrsj.conveniareventos.Utils.API.models;

import java.io.Serializable;

public class UserRegister implements Serializable {

    private int NumRegistro;
    private String Nome;
    private String Senha;
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

    public UserRegister(String nome, String senha, String email) {
        Nome = nome;
        Senha = senha;
        Email = email;
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

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
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
