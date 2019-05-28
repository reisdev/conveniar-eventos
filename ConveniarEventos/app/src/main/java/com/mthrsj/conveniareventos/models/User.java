package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class User implements Serializable {
    public String NomePessoa;
    public String Nacionalidade;
    public String CPF;
    public String DataNascimento;
    public String Sexo;
    public String RacaCor;
    public String EstadoCivil;
    public String GrauInstrucao;
    public String NumeroDependentes;
    public String Tipologradouro;
    public String Logradouro;
    public String Numero;
    public String Complemento;
    public String Bairro;
    public String CEP;
    public String Cidade;
    public String Estado;
    public String Pais;
    public String TelefoneResidencial;
    public String TelefoneComercial;
    public String TelefoneCelular;
    public String Fax;
    public String EmailContato;
    public String RG;
    public String OrgaoEmissor;
    public String DataExpedicao;
    public String TituloEleitor;
    public String SessaoEleitoral;
    public String ZonaEleitoral;
    public String PisPasep;
    public String DataCadastroPis;
    public Integer FlagServidorPublico;
    public String Siape;
    public Integer FlagClt;
    public String InstituicaoTrabalho;

    public User(String nomePessoa, String nacionalidade, String CPF, String dataNascimento, String sexo, String racaCor, String estadoCivil, String grauInstrucao, String numeroDependentes, String tipologradouro, String logradouro, String numero, String complemento, String bairro, String CEP, String cidade, String estado, String pais, String telefoneResidencial, String telefoneComercial, String telefoneCelular, String fax, String emailContato, String RG, String orgaoEmissor, String dataExpedicao, String tituloEleitor, String sessaoEleitoral, String zonaEleitoral, String pisPasep, String dataCadastroPis, Integer flagServidorPublico, String siape, Integer flagClt, String instituicaoTrabalho) {
        NomePessoa = nomePessoa;
        Nacionalidade = nacionalidade;
        this.CPF = CPF;
        DataNascimento = dataNascimento;
        Sexo = sexo;
        RacaCor = racaCor;
        EstadoCivil = estadoCivil;
        GrauInstrucao = grauInstrucao;
        NumeroDependentes = numeroDependentes;
        Tipologradouro = tipologradouro;
        Logradouro = logradouro;
        Numero = numero;
        Complemento = complemento;
        Bairro = bairro;
        this.CEP = CEP;
        Cidade = cidade;
        Estado = estado;
        Pais = pais;
        TelefoneResidencial = telefoneResidencial;
        TelefoneComercial = telefoneComercial;
        TelefoneCelular = telefoneCelular;
        Fax = fax;
        EmailContato = emailContato;
        this.RG = RG;
        OrgaoEmissor = orgaoEmissor;
        DataExpedicao = dataExpedicao;
        TituloEleitor = tituloEleitor;
        SessaoEleitoral = sessaoEleitoral;
        ZonaEleitoral = zonaEleitoral;
        PisPasep = pisPasep;
        DataCadastroPis = dataCadastroPis;
        FlagServidorPublico = flagServidorPublico;
        Siape = siape;
        FlagClt = flagClt;
        InstituicaoTrabalho = instituicaoTrabalho;
    }
}
