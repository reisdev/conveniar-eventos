package com.mthrsj.conveniareventos.models;

import java.io.Serializable;

public class User implements Serializable {
    private String NomePessoa;
    private String Nacionalidade;
    private  String CPF;
    private String DataNascimento;
    private String Sexo;
    private String RacaCor;
    private String EstadoCivil;
    private String GrauInstrucao;
    private String NumeroDependentes;
    private String Tipologradouro;
    private String Logradouro;
    private String Numero;
    private String Complemento;
    private String Bairro;
    private String CEP;
    private String Cidade;
    private String Estado;
    private String Pais;
    private String TelefoneResidencial;
    private String TelefoneComercial;
    private String TelefoneCelular;
    private String Fax;
    private String EmailContato;
    private String RG;
    private String OrgaoEmissor;
    private String DataExpedicao;
    private String TituloEleitor;
    private String SessaoEleitoral;
    private String ZonaEleitoral;
    private String PisPasep;
    private String DataCadastroPis;
    private Integer FlagServidorPublico;
    private String Siape;
    private Integer FlagClt;
    private String InstituicaoTrabalho;

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
