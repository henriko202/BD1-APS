package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André Schwerz
 */
public abstract class DbConnection {
    
    public Connection getMyConnection() throws SQLException, ClassNotFoundException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(Config.URL, Config.LOGIN, Config.PASSWORD);
    }
   
    public Connection connect(){
        try {
            return this.getMyConnection();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
             System.out.println(ex.getMessage());
             return null;
        }
    }
    public void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}