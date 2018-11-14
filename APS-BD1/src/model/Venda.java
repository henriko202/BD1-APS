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
public class Venda {
    private int id;
    private int cliente;
    private int filial;
    private int funcionario;
    private Date data;

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getCliente() {
        return cliente;
    }

    public int getFilial() {
        return filial;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public Date getData() {
        return data;
    }
}
