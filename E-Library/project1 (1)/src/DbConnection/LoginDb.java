/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class LoginDb {
      Connection conn=null;
    public static Connection ConnectDb(){
        String url="jdbc:mysql://127.0.0.1/project";
        String user="root";
        String pass="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "connection establish");
            return conn;
        } catch (Exception e) {
           // Logger.getLogger(mySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
    
}
