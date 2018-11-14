/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author henri
 */
public class ProdutoFornecedor {
    private int produto;
    private int fornecedor;

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getProduto() {
        return produto;
    }

    public int getFornecedor() {
        return fornecedor;
    }
}
