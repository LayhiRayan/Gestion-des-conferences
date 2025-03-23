/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a
 */
public class JDBC {

   
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String login = "root";
        String password = "";
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
        
            String req = "create table filiere(id int primary key auto_increment, code varchar (50), libelle varchar (100))";
       
            Statement st = cn.createStatement();
            st.executeUpdate(req);
           
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver introvable");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
