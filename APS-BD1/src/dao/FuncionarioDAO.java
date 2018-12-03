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
import model.Funcionario;

/**
 *
 * @author henriko202
 */
public class FuncionarioDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Funcionario (nome, logradouro, bairro, cidade, estado, pais, telefone, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String sqlUpdate = "UPDATE Funcionario SET nome = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, telefone=?, salario=? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Funcionario WHERE id = ?";
    private final String sqlList = "SELECT id, nome FROM Funcionario ORDER BY id";
    private final String sqlFind = "SELECT id, nome FROM Funcionario WHERE id = ?";

    public void insert(Funcionario cliente) throws SQLException {
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
            ps.setDouble(8, cliente.getSalario());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Funcionario cliente) throws SQLException {
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
            ps.setDouble(8, cliente.getSalario());
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

    public ArrayList<Funcionario> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Funcionario> list = new ArrayList<>();
            Funcionario cliente;
            while (rs.next()) {
                cliente = new Funcionario();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSalario(rs.getDouble("salario"));
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
