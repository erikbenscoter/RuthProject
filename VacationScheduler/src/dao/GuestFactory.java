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
import dataobjs.Guestx;

/**
 *
 * @author Glenn Benscoter
 */
public class GuestFactory 
{
  public static Vector getAllGuests(){
        Guestx guestToAdd;
        Vector guestVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM GUESTS";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress, firstName,lastName,phoneNumber;
        int creditCardNumber,numberPreviousRentals;
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            while(rs.next()){
                
                emailAddress = rs.getString("Email");
                firstName = rs.getString("First_Name");
                lastName = rs.getString("Last_Name");
                phoneNumber = rs.getString("Phone_Number");
                creditCardNumber = rs.getInt("Credit_Card_Number");
                numberPreviousRentals = rs.getInt("Previous_Rentals");

                guestToAdd = new Guestx(emailAddress, firstName, lastName, phoneNumber, creditCardNumber, numberPreviousRentals);
                guestVectorToReturn.add(guestToAdd);
                
            }
            return guestVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return guestVectorToReturn;
             
        }
    }
  public Vector getAllNames(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Vector output = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT FIRST_NAME,LAST_NAME FROM Guest");
            
            while(rs.next()){
                output.add(rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"));
            }
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
      public Vector getAllEmails(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Vector output = new Vector();
            
            Statement st = con.createStatement();
            System.out.println("SELECT EMAIL FROM GUEST" );
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM GUEST");
            
            while(rs.next()){
                output.add(rs.getString("EMAIL"));
                System.out.println(rs.getString("EMAIL"));
            }
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
  /*
  public void insert(Guestx g){
        Vector inserts = new Vector();
            inserts.add(g.emailAddress );
            inserts.add(g.firstName ) ;
            inserts.add(g.lastName ) ;
            inserts.add(g.phoneNumber ) ;
            inserts.add(g.creditCardNumber ) ;
            inserts.add(g.numberPreviousRentals ) ; 
        
        String command = "INSERT INTO GUESTS (EMAIL,FIRST_NAME,"
                +"LAST_NAME,PHONE_NUMBER,CREDIT_CARD_NUMBER,"
                +"PREVIOUS_RENTALS) VALUES(";
        
        String parameters= "";
        
        for(int i=0; i<inserts.size()-1;i++){
            if(i < inserts.size()-2)                            //string
                parameters = parameters +"'"+inserts.get(i) + "',";
            else                                                //not string
                parameters = parameters + inserts.get(i) + ",";

        }
        parameters = parameters + inserts.get(inserts.size()-1);
        
        command = command + parameters + ")";
        
        insert(command);
        
    }
    /*
      public static void insert(String insertCommand)
      {
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
  */
  
}
