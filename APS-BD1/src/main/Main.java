/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.Venda;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


/**
 *
 * @author henri
 */ 
public class Main {

    /**
     *main, you fucker
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException  {
         new Venda().setVisible(true);
    }

}
