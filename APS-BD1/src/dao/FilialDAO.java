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
import model.Filial;

/**
 *
 * @author henriko202
 */
public class FilialDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Filial (nome, logradouro, bairro, cidade, estado, pais, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String sqlUpdate = "UPDATE Filial SET nome = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, telefone=? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Filial WHERE id = ?";
    private final String sqlList = "SELECT id, nome, logradouro, bairro, cidade, estado, pais, telefone FROM Filial ORDER BY id";

    public void insert(Filial filial) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, filial.getNome());
            ps.setString(2, filial.getLogradouro());
            ps.setString(3, filial.getBairro());
            ps.setString(4, filial.getCidade());
            ps.setString(5, filial.getEstado());
            ps.setString(6, filial.getPais());
            ps.setString(7, filial.getTelefone());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Filial filial) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, filial.getNome());
            ps.setString(2, filial.getLogradouro());
            ps.setString(3, filial.getBairro());
            ps.setString(4, filial.getCidade());
            ps.setString(5, filial.getEstado());
            ps.setString(6, filial.getPais());
            ps.setString(7, filial.getTelefone());
            ps.setInt(8, filial.getId());
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

    public ArrayList<Filial> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Filial> list = new ArrayList<>();
            Filial filial;
            while (rs.next()) {
                filial = new Filial();
                filial.setId(rs.getInt("id"));
                filial.setNome(rs.getString("nome"));
                filial.setTelefone(rs.getString("telefone"));
                filial.setLogradouro(rs.getString("logradouro"));
                filial.setPais(rs.getString("pais"));
                filial.setEstado(rs.getString("estado"));
                filial.setCidade(rs.getString("cidade"));
                filial.setBairro(rs.getString("bairro"));

                list.add(filial);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

    
   
}
