/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author erikbenscoter
 */
public class RuthDBConnection {
    
    //new code
    Connection conn = null;
    static String host = "jdbc:sqlite:./RuthDB";
    
    //old code
    
    
    //String host = "jdbc:derby://localhost:1527/VPDB";
    //String host = "jdbc:derby:VPDB";

    String uName = "root";
    String pass = "password";
 
    
public static Connection getConnection(){
       try{
                Class.forName("org.sqlite.JDBC").newInstance();
                Connection conn = DriverManager.getConnection(host);
                return conn;
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            Connection con = DriverManager.getConnection(host, uName,pass);
//            return con;
       }catch(Exception e){
           System.out.println(e);
           return null;
       }
       
    }
public static void insert(String insertCommand){
        try{
            
            Connection con = RuthDBConnection.getConnection();
            Statement statement = con.createStatement();
            statement.execute(insertCommand);
            JOptionPane.showMessageDialog(null, "Thank you your changes have been made\n");
            con.close();
        }catch(Exception e){
            System.out.println("trouble with the connection!!" + e);
            JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
        }
        
      
    }
    public static void insertSilent(String insertCommand){
        try{
            
            Connection con = RuthDBConnection.getConnection();
            Statement statement = con.createStatement();
            statement.execute(insertCommand);
            
            con.close();
        }catch(Exception e){
            System.out.println("trouble with the connection!!" + e);
            JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
        }
        
       
    }
}