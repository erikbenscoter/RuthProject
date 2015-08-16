/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author erikbenscoter
 */
public class DBConnection {
    
    //new code
    Connection conn = null;
    String host = "jdbc:sqlite:RuthDB";
    
    //old code
    
    
    //String host = "jdbc:derby://localhost:1527/VPDB";
    //String host = "jdbc:derby:VPDB";

    String uName = "root";
    String pass = "password";
    
    
public Connection getConnection(){
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
    public void insert(String insertCommand){
        try{
            
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.execute(insertCommand);
            JOptionPane.showMessageDialog(null, "Thank you your changes have been made\n");
            con.close();
        }catch(Exception e){
            System.out.println("trouble with the connection!!" + e);
            JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
        }
        
       
    }
    public void insert(Owner own){
        Vector inserts = new Vector();
        inserts.add(own.emailAddress);
        inserts.add(own.firstName);
        inserts.add(own.lastName);
        inserts.add(own.phoneNumber);
        inserts.add(own.userName);
        inserts.add(own.password);
        inserts.add(Integer.toString(own.pointsOwned));
        inserts.add(Integer.toString(own.currentAvailablePts));
        inserts.add(Double.toString(own.reimbursementRate));
        
        String command = "INSERT INTO OWNERS (EMAIL, FIRST_NAME, LAST_NAME,"
                +"PHONE_NUMBER, USER_NAME, PASSWORD, POINTS_OWNED, "
                +"CURRENT_POINTS, OWNER_REIMBURSEMENT_RATE) values(";
        String parameters = "";
        for (int i = 0; i< (inserts.size()-1); i++) {
            if(i < inserts.size()-3)
                parameters = parameters +"'"+inserts.get(i)+"'"+",";        //strings
            else
                parameters = parameters + inserts.get(i) + ",";             //not strings

        }
        parameters = parameters + inserts.get(inserts.size()-1);
        command = command + parameters + ")";
        System.out.println(command);
        insert(command);
        
    }
    public void insert(Reservation reserve){
        Vector inserts = new Vector();
            inserts.add(reserve.owner);
            inserts.add(reserve.location);
            inserts.add(reserve.dateOfReservation);
            //Use Date
            inserts.add(reserve.numberOfNights );
            inserts.add(reserve.unitSize );
            inserts.add(reserve.confimationNumber );
            inserts.add(reserve.pointsRequiredForReservation );
            inserts.add(reserve.wasDiscounted );
            inserts.add(reserve.wasUpgraded );
            inserts.add(reserve.isBuyerLinedUp );
            inserts.add(reserve.guest );
            inserts.add(reserve.amountPaid );
            inserts.add(reserve.datePaid );
            inserts.add(reserve.paymentMethod );
        
    }
    public void insert(Guest g){
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
    public Vector getAllOwnerEmails(){
        Connection con = this.getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM OWNERS");
            
            Vector output = new Vector();
            
            
            while(rs.next()){
                output.add(rs.getString("EMAIL"));
            }
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }   
    }
    public Vector getAllNames(String tableName){
        Connection con = this.getConnection();
        try{
            Vector output = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT FIRST_NAME,LAST_NAME FROM " + tableName);
            
            while(rs.next()){
                output.add(rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"));
            }
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public Vector getAllEmails(String tableName){
        Connection con = this.getConnection();
        try{
            Vector output = new Vector();
            
            Statement st = con.createStatement();
            System.out.println("SELECT EMAIL FROM " + tableName);
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM " + tableName);
            
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
    
    public Vector getAvailablePts(){
        Connection con = this.getConnection();
        try{
            Vector avpts = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CURRENT_POINTS FROM OWNERS");
            
            while(rs.next()){
                avpts.add(Double.toString(rs.getDouble("CURRENT_POINTS")));
                System.out.println(rs.getDouble("CURRENT_POINTS"));
            }
            
            return avpts;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        Vector results = db.getAllOwnerEmails();
        for(Object result : results){
            System.out.println(result);
        }
        
    }
    
}
