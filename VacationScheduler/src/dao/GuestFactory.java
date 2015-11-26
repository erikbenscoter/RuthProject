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
import dataobjs.Guest;

/**
 *
 * @author Glenn Benscoter
 */
public class GuestFactory 
{
  public static Vector getAllGuests(){
        Guest guestToAdd;
        Vector guestVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM GUESTS";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress, firstName,lastName,phoneNumber,creditCardNumber;
        int numberPreviousRentals;
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            while(rs.next()){
                
                emailAddress = rs.getString("Email");
                firstName = rs.getString("First_Name");
                lastName = rs.getString("Last_Name");
                phoneNumber = rs.getString("Phone_Number");
                creditCardNumber = rs.getString("Credit_Card_Number");
                numberPreviousRentals = rs.getInt("Previous_Rentals");

                guestToAdd = new Guest(emailAddress, firstName, lastName, phoneNumber, creditCardNumber, numberPreviousRentals);
                guestVectorToReturn.add(guestToAdd);
                
            }
            con.close();
            return guestVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return guestVectorToReturn;
             
        }
    }
  public static Guest getGuests(String email){
        Guest guestToReturn;
        String myQuery = "SELECT * FROM GUESTS WHERE EMAIL = '"+ email +"'";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress, firstName,lastName,phoneNumber, creditCardNumber;
        int numberPreviousRentals;
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            rs.next();
                
            emailAddress = rs.getString("Email");
            firstName = rs.getString("First_Name");
            lastName = rs.getString("Last_Name");
            phoneNumber = rs.getString("Phone_Number");
            creditCardNumber = rs.getString("Credit_Card_Number");
            numberPreviousRentals = rs.getInt("Previous_Rentals");

            guestToReturn = new Guest();
                guestToReturn.setEmailAddress(emailAddress);
                guestToReturn.setFirstName(firstName);
                guestToReturn.setLastName(lastName);
                guestToReturn.setPhoneNumber(phoneNumber);
                guestToReturn.setCreditCardNumber(creditCardNumber);
                guestToReturn.setNumberPreviousRentals(numberPreviousRentals);
                
            con.close();
            
            System.err.println(myQuery);
            
            guestToReturn.print();
            
            return guestToReturn;

        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return new Guest();
             
        }
    }
    public static Vector getGuest(String p_firstName, String p_lastName){
        Guest guestToAdd;
        Vector guestVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM GUESTS WHERE First_Name = '"+p_firstName+"' AND Last_Name= '" + p_lastName +"' LIMIT 1";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress, firstName,lastName,phoneNumber, creditCardNumber;
        int numberPreviousRentals;
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            while(rs.next()){
                
                emailAddress = rs.getString("Email");
                firstName = rs.getString("First_Name");
                lastName = rs.getString("Last_Name");
                phoneNumber = rs.getString("Phone_Number");
                creditCardNumber = rs.getString("Credit_Card_Number");
                numberPreviousRentals = rs.getInt("Previous_Rentals");

                guestToAdd = new Guest(emailAddress, firstName, lastName, phoneNumber, creditCardNumber, numberPreviousRentals);
                guestVectorToReturn.add(guestToAdd);
                
            }
            con.close();
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
            con.close();
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
            con.close();
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
  
  public static void insert(Guest g){
        Vector inserts = new Vector();
            inserts.add(g.getEmailAddress() );
            inserts.add(g.getFirstName() ) ;
            inserts.add(g.getLastName() ) ;
            inserts.add(g.getPhoneNumber() ) ;
            inserts.add(g.getCreditCardNumber() ) ;
            inserts.add(g.getNumberPreviousRentals() ) ; 
        
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
  
    public static void update(Guest g){
        Vector <String> columnNames = new Vector();
        Vector <String> values = new Vector();
        String myUpdateCommand = "Update Guests Set ";
        
        //add all the column names
            //columnNames.add( "EMAIL" );               //email is primary key for ther where clause
        columnNames.add( "FIRST_NAME" );
        columnNames.add( "LAST_NAME" );
        columnNames.add( "PHONE_NUMBER" );
        columnNames.add( "CREDIT_CARD_NUMBER" );
        columnNames.add( "PREVIOUS_RENTALS" );

        //add all the values
            //values.add(g.getEmailAddress());         //email is primary key for the werhere clause
        values.add(g.getFirstName());
        values.add(g.getLastName());
        values.add(g.getPhoneNumber());
        values.add(g.getCreditCardNumber());
        values.add( Integer.toString( g.getNumberPreviousRentals() ) );
        
        //create the query
        for(int i = 0; i < columnNames.size(); i++){
            String columnName = columnNames.get(i);
            String value = values.get(i);
            
            if(i != 0){
                myUpdateCommand = myUpdateCommand + ", ";
            }
            
            myUpdateCommand = myUpdateCommand + columnName + " = '" + value + "' ";
        }
        
        myUpdateCommand = myUpdateCommand + " WHERE EMAIL = '" + g.getEmailAddress() + "'";
        
        System.err.println(myUpdateCommand);
        
        insert(myUpdateCommand);
    }
    
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
  
  
}
