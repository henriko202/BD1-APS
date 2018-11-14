/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author henri
 */
public class Cliente {
    private int id;
    private Date data_nasc;
    private String logradouro;
    private String cidade;
    private String estado;
    private String pais;
    private String nome;
    private int telefone;

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getNome() {
        return nome;
    }

    public int getTelefone() {
        return telefone;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public int getId() {
        return id;
    }

    public Date getData_nasc() {
        return data_nasc;
    }
}
