package dao;

import model.Pessoa;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author André Schwerz
 */
public class PessoaDAO extends DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Pessoas(cpf, nome, email, registro, observacao, cursos_sigla) VALUES (?,?,?,?,?,?)";
    private final String sqlUpdate = "UPDATE Pessoas SET nome= ?, email= ?, registro= ?, observacao= ?, cursos_sigla= ?  WHERE cpf = ?";
    private final String sqlRemove = "DELETE FROM Pessoas WHERE cpf = ?";
    private final String sqlList = "SELECT cpf, nome, email, registro, observacao, cursos_sigla FROM Pessoas ORDER BY nome";
    private final String sqlFind = "SELECT cpf, nome, email, registro, observacao, cursos_sigla FROM Pessoas WHERE cpf = ?";

    public void insert(Pessoa pessoa) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, pessoa.getRegistro());
            ps.setString(5, pessoa.getObservacao());
            if (pessoa.getCurso() == null) {
                ps.setString(6, null);
            } else {
                ps.setString(6, pessoa.getCurso().getSigla());
            }
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void update(Pessoa pessoa) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getRegistro());
            ps.setString(4, pessoa.getObservacao());
            if (pessoa.getCurso() != null) {
                ps.setString(5, pessoa.getCurso().getSigla());
            } else {
                ps.setString(5, null);
            }
            ps.setString(6, pessoa.getCpf());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void remove(String cpf) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, cpf);
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public ArrayList<Pessoa> list() throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Pessoa> list = new ArrayList<>();
            Pessoa pessoa;

            CursoDAO cursoDao = new CursoDAO();
            
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setRegistro(rs.getString("registro"));
                pessoa.setObservacao(rs.getString("observacao"));
                pessoa.setCurso(cursoDao.find(rs.getString("cursos_sigla")));
                list.add(pessoa);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

    public Pessoa find(String cpf) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            Pessoa pessoa = null;
            CursoDAO cursoDao = new CursoDAO();
            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setRegistro(rs.getString("registro"));
                pessoa.setObservacao(rs.getString("observacao"));
                pessoa.setCurso(cursoDao.find(rs.getString("cursos_sigla")));
            }
            return pessoa;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }
}