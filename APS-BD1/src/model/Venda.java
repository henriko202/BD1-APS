/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.text.DateFormat;

/**
 *
 * @author henri
 */
public class Venda {

    private int id;
    private int cliente;
    private int filial;
    private int funcionario;
    private String data;

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

    public void setData(String data) {
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

    public String getDataMySQL() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String temp = dateFormat.format(this.data);

        String day = temp.substring(0, 2);
        String month = temp.substring(3, 5);
        String year = temp.substring(6, 10);
        return year + '/' + month + '/' + day;
    }

    public String getData() {
        return data;
    }
}
