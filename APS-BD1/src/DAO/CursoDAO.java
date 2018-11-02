package dao;

import model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author André  Schwerz
 */
public class CursoDAO extends DbConnection{
    private Connection conn;
    private final String sqlInsert = "INSERT INTO Cursos (sigla, nome) VALUES (?,?)";
    private final String sqlUpdate = "UPDATE Cursos SET nome = ? WHERE sigla = ? ";
    private final String sqlRemove = "DELETE FROM Cursos WHERE sigla = ?";
    private final String sqlList   = "SELECT sigla, nome FROM Cursos ORDER BY nome";
    private final String sqlFind   = "SELECT sigla, nome FROM Cursos WHERE sigla = ?";

    public void insert(Curso curso) throws SQLException{
        conn = connect();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, curso.getSigla());
            ps.setString(2, curso.getNome());
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }
        
    }
    
    public void update(Curso curso) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getSigla());
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }
    }
    
    public void remove(String sigla) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, sigla);
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }

    }
    
    public ArrayList<Curso> list() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Curso> list = new ArrayList<>();
            Curso curso;
            while (rs.next()){
                curso = new Curso();
                curso.setSigla(rs.getString("sigla"));
                curso.setNome(rs.getString("nome"));
                list.add(curso);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(conn);
        }
    }

    public Curso find(String sigla)throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, sigla);

            rs = ps.executeQuery();
            Curso curso = null ;

            if (rs.next()){
                curso = new Curso();
                curso.setSigla(rs.getString("sigla"));
                curso.setNome(rs.getString("nome"));
            }
            return curso;
        }
        finally{
            rs.close();
            ps.close();
            close(conn);
        }       
    }
}