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
import model.Cliente;

/**
 *
 * @author henriko202
 */
public class ClienteDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Cliente (nome, logradouro, bairro, cidade, estado, pais, telefone, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String sqlUpdate = "UPDATE Cliente SET nome = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, telefone=?, dataNasc=? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Cliente WHERE id = ?";
    private final String sqlList = "SELECT id, nome FROM Cliente ORDER BY id";
    private final String sqlFind = "SELECT id, nome FROM Cliente WHERE id = ?";

    public void insert(Cliente cliente) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getLogradouro());
            ps.setString(3, cliente.getBairro());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getEstado());
            ps.setString(6, cliente.getPais());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getData_nasc().toString());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Cliente cliente) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getLogradouro());
            ps.setString(3, cliente.getBairro());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getEstado());
            ps.setString(6, cliente.getPais());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getData_nasc().toString());
            ps.setInt(9, cliente.getId());
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

    public ArrayList<Cliente> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Cliente> list = new ArrayList<>();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setData_nasc(rs.getString("dataNasc"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setPais(rs.getString("pais"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));

                list.add(cliente);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

}
