/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connections.RuthDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Glenn Benscoter
 */
public class LocationsFactory 
{
 public static Vector GetAllLocations(){
        Connection con;
        Statement st;
        ResultSet rs;
        String myQuery;
        Vector returnVector;
        String locationToAdd;
        
        
        myQuery = "SELECT * FROM RESERVATIONS";
        returnVector =  new Vector();
        
        
        try{
            con = RuthDBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            while(rs.next()){
                locationToAdd = rs.getString("Location");
                returnVector.add(locationToAdd);
            }
            con.close();
            return returnVector;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error please try again\n" + e);
            return new Vector();
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
}
