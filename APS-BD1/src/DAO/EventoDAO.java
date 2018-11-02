package dao;

import model.Evento;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author henriko-bcc-utfpr-cm
 */
public class EventoDAO extends DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Eventos(sigla, nome, data_inicio, data_fim, cursos_sigla) VALUES (?,?,?,?,?)";
    private final String sqlUpdate = "UPDATE Eventos SET nome= ?, data_inicio= ?, data_fim= ?, cursos_sigla= ? WHERE sigla = ?";
    private final String sqlRemove = "DELETE FROM Eventos WHERE sigla = ?";
    private final String sqlList = "SELECT sigla, nome, data_inicio, data_fim, cursos_sigla FROM Eventos ORDER BY cursos_sigla";
    private final String sqlFind = "SELECT sigla, nome, data_inicio, data_fim, cursos_sigla FROM Eventos WHERE sigla = ?";

    public void insert(Evento evento) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, evento.getSigla());
            ps.setString(2, evento.getNome());
            ps.setString(3, evento.getDataInicioMySQL());
            ps.setString(4, evento.getDataFimMySQL());
            ps.setString(5, evento.getCurso().getSigla());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void update(Evento evento) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            
            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getDataInicioMySQL());
            ps.setString(3, evento.getDataFimMySQL());
            ps.setString(4, evento.getCurso().getSigla());
            ps.setString(5, evento.getSigla());
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public void remove(String sigla) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, sigla);
            ps.execute();
        } finally {
            ps.close();
            close(conn);
        }
    }

    public List<Evento> list() throws SQLException, ClassNotFoundException, ParseException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            List<Evento> list = new ArrayList<>();
            Evento evento;
            
            CursoDAO cursoDAO = new CursoDAO();
            
            while (rs.next()) {
                evento = new Evento();
                evento.setSigla(rs.getString("sigla"));
                evento.setNome(rs.getString("nome"));
                evento.setDataInicioMySQL(rs.getString("data_inicio"));
                evento.setDataFimMySQL(rs.getString("data_fim"));
                evento.setCurso(cursoDAO.find(rs.getString("sigla_cursos")));
                list.add(evento);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }

    public Evento find(String sigla) throws SQLException, ParseException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn=connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, sigla);
            rs = ps.executeQuery();
            Evento evento = new Evento();
            CursoDAO cursoDAO = new CursoDAO();

            if (rs.next()) {
                evento.setSigla(rs.getString("sigla"));
                evento.setNome(rs.getString("nome"));
                evento.setDataInicioMySQL(rs.getString("data_inicio"));
                evento.setDataFimMySQL(rs.getString("data_fim"));
                evento.setCurso(cursoDAO.find(rs.getString("sigla_cursos")));
            }
            return evento;
        } finally {
            rs.close();
            ps.close();
            close(conn);
        }
    }
}