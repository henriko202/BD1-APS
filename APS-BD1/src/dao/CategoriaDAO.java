package dao;

import model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author henri
 */
public class CategoriaDAO extends dao.DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Categoria (nome) VALUES (?)";
    private final String sqlUpdate = "UPDATE Categoria SET nome = ? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM Categoria WHERE id = ?";
    private final String sqlList = "SELECT id, nome FROM Categoria ORDER BY id";
    private final String sqlFind = "SELECT id, nome FROM Categoria WHERE id = ?";

    public void insert(Categoria categoria) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, categoria.getNome());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }

    }

    public void update(Categoria categoria) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, categoria.getNome());
            ps.setString(2, Integer.toString(categoria.getId()));
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

    public ArrayList<Categoria> list() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Categoria> list = new ArrayList<>();
            Categoria categoria;
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                list.add(categoria);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

    public Categoria find(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            Categoria categoria = null;

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setNome(rs.getString("nome"));
            }
            return categoria;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

    public Categoria find(String nome) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, nome);

            rs = ps.executeQuery();
            Categoria categoria = null;

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
            }
            return categoria;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

}
