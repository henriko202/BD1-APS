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
import model.Produto;

/**
 *
 * @author henriko202
 */
public class ProdutoDAO extends dao.DbConnection {
    private Connection conn;
    private final String sqlInsert = "INSERT INTO Produto (nome, categoria, preco) VALUES (?, ?, ?)";
    private final String sqlUpdate = "UPDATE Produto SET nome = ?, categoria =?, preco =? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Produto WHERE id = ?";
    private final String sqlList = "SELECT id, nome, categoria, preco FROM Produto ORDER BY id";
    private final String sqlFind = "SELECT id, nome, categoria FROM Categoria WHERE id = ?";
    

    public void insert(Produto produto) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getCategoria());
            ps.setDouble(3, produto.getPreco());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Produto produto) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getCategoria());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getId());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void remove(int id) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, Integer.toString(id));
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public ArrayList<Produto> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Produto> list = new ArrayList<>();
            Produto produto;
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getInt("categoria"));
                produto.setPreco(rs.getDouble("preco"));
                list.add(produto);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }
    
}
