/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ProdutoFornecedor;

/**
 *
 * @author henriko202
 */
public class ProdutoFornecedorDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO ProdutoFornecido (produto, fornecedor) VALUES (?, ?)";
    private final String sqlUpdate = "UPDATE ProdutoFornecido SET fornecedor = ?, produto = ? WHERE produto = ? AND fornecedor = ? ";
    private final String sqlRemove = "DELETE FROM ProdutoFornecido WHERE produto = ? AND fornecedor = ?";
    private final String sqlList = "SELECT produto, fornecedor FROM ProdutoFornecido ORDER BY produto";
    

    public void insert(ProdutoFornecedor prodF) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, prodF.getProduto());
            ps.setInt(2, prodF.getFornecedor());

            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(ProdutoFornecedor prodF) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setInt(1, prodF.getProduto());
            ps.setInt(2, prodF.getFornecedor());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void remove(int produto, int fornecedor) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, Integer.toString(produto));
            ps.setString(2, Integer.toString(fornecedor));
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public ArrayList<ProdutoFornecedor> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<ProdutoFornecedor> list = new ArrayList<>();
            ProdutoFornecedor prodF;
            while (rs.next()) {
                prodF = new ProdutoFornecedor();
                prodF.setProduto(rs.getInt("produto"));
                prodF.setFornecedor(rs.getInt("fornecedor"));
                
                list.add(prodF);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }
}
