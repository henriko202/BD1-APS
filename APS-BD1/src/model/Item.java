/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author henriko202
 */
public class Item {

    private int id;
    private int venda;
    private int produto;
    private double desconto;
    private int quantidade;

    public int getId() {
        return id;
    }

    public int getVenda() {
        return venda;
    }

    public int getProduto() {
        return produto;
    }

    public double getDesconto() {
        return desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
