/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.sql.Connection;
import java.sql.DriverManager;


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
}