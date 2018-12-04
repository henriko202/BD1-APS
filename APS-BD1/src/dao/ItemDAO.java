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
import model.Item;

/**
 *
 * @author henriko202
 */
public class ItemDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Item (venda, produto, desconto, quantidade) VALUES (?, ?, ?, ?)";
    private final String sqlUpdate = "UPDATE Item SET venda = ?, produto = ?, desconto = ?, quantidade = ? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Item WHERE id = ?";
    private final String sqlList = "SELECT id, venda, produto, desconto, quantidade FROM Item ORDER BY id";

    public void insert(Item item) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, item.getVenda());
            ps.setInt(2, item.getProduto());
            ps.setDouble(3, item.getDesconto());
            ps.setInt(4, item.getQuantidade());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Item item) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setInt(1, item.getVenda());
            ps.setInt(2, item.getProduto());
            ps.setDouble(3, item.getDesconto());
            ps.setInt(4, item.getQuantidade());
            ps.setInt(5, item.getId());
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

    public ArrayList<Item> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Item> list = new ArrayList<>();
            Item item;
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("id"));
                item.setVenda(rs.getInt("venda"));
                item.setProduto(rs.getInt("produto"));
                item.setDesconto(rs.getDouble("desconto"));
                item.setQuantidade(rs.getInt("quantidade"));
                list.add(item);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }
}
