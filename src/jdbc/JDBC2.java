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

/**
 *
 * @author a
 */
public class JDBC2 {
        public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String login = "root";
        String password = "";
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
        
            String req = "insert into filiere values (null, 'info', 'info')";
       
            Statement st = cn.createStatement();
            st.executeUpdate(req);
           
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver introvable");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
