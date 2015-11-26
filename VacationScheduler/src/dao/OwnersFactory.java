package dao;

import Connections.RuthDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

import dataobjs.Owner;

public class OwnersFactory {
/* 
 *   insert into owner table
*/
    public static void insert(Owner own){
        Connection con = RuthDBConnection.getConnection();
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
        OwnersFactory.insert(command);
    }
  /*
   *          get emails of all owners
  */
     public static Vector getAllOwnerEmails(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM OWNERS");
            
            Vector output = new Vector();
            
            
            while(rs.next()){
                output.add(rs.getString("EMAIL"));
            }
            con.close();
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }   
    }
  /*
  *             get available point for all owners   
  */
         public Vector getAvailablePts(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Vector avpts = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CURRENT_POINTS FROM OWNERS");
            
            while(rs.next()){
                avpts.add(Double.toString(rs.getDouble("CURRENT_POINTS")));
                System.out.println(rs.getDouble("CURRENT_POINTS"));
            }
            con.close();
            return avpts;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
 /*
 *    Get all from owners based on email first name = email?
 */
    public static Owner get(String p_email)
    {
        Owner ownerToReturn;
        String myQuery = "SELECT * FROM OWNERS WHERE First_Name = '"+p_email+"'";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress;
        String firstName;
        String lastName;
        String phoneNumber;
        String userName;
        String password;
        int pointsOwned;
        int currentAvailablePoints;
        double reimbursementRate;
        
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            emailAddress = rs.getString("Email");
            firstName = rs.getString("First_Name");
            lastName = rs.getString("Last_Name");
            phoneNumber = rs.getString("Phone_Number");
            userName = rs.getString("User_Name");
            password = rs.getString("Password");
            pointsOwned = rs.getInt("Points_Owned");
            currentAvailablePoints = rs.getInt("Current_Points");
            reimbursementRate = rs.getDouble("Owner_Reimbursement_Rate");
            
            ownerToReturn = new Owner(emailAddress, firstName, lastName, phoneNumber, userName, password, pointsOwned, currentAvailablePoints, reimbursementRate);
            con.close();
            return ownerToReturn;


        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return new Owner();
        }
          
    }
 /*
  *              Get all ownere in a vector
 */   
        public static Vector getAllOwners(){
        Owner ownerToAdd;
        Vector ownerVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM OWNERS";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress;
        String firstName;
        String lastName;
        String phoneNumber;
        String userName;
        String password;
        int pointsOwned;
        int currentAvailablePoints;
        double reimbursementRate;
        
        
        
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            while(rs.next()){
                
                emailAddress = rs.getString("Email");
                firstName = rs.getString("First_Name");
                lastName = rs.getString("Last_Name");
                phoneNumber = rs.getString("Phone_Number");
                userName = rs.getString("User_Name");
                password = rs.getString("Password");
                pointsOwned = rs.getInt("Points_Owned");
                currentAvailablePoints = rs.getInt("Current_Points");
                reimbursementRate = rs.getDouble("Owner_Reimbursement_Rate");

                ownerToAdd = new Owner(emailAddress, firstName, lastName, phoneNumber, userName, password, pointsOwned, currentAvailablePoints, reimbursementRate);
                ownerVectorToReturn.add(ownerToAdd);
                
            }
            con.close();
            return ownerVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return ownerVectorToReturn;
             
        }
    }
      public Vector getAllNames(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Vector output = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT FIRST_NAME,LAST_NAME FROM Owners");
            
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
            System.out.println("SELECT EMAIL FROM OWNERS" );
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM OWNERS");
            
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
    
    public static Owner getByUserName(String userName)
    {
        Owner ownerToReturn;
        String myQuery = "SELECT * FROM OWNERS WHERE User_Name = '"+userName+"'";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress;
        String firstName;
        String lastName;
        String phoneNumber;
        String password;
        int pointsOwned;
        int currentAvailablePoints;
        double reimbursementRate;
        
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            emailAddress = rs.getString("Email");
            firstName = rs.getString("First_Name");
            lastName = rs.getString("Last_Name");
            phoneNumber = rs.getString("Phone_Number");
            userName = rs.getString("User_Name");
            password = rs.getString("Password");
            pointsOwned = rs.getInt("Points_Owned");
            currentAvailablePoints = rs.getInt("Current_Points");
            reimbursementRate = rs.getDouble("Owner_Reimbursement_Rate");
            
            ownerToReturn = new Owner(emailAddress, firstName, lastName, phoneNumber, userName, password, pointsOwned, currentAvailablePoints, reimbursementRate);
            con.close();
            return ownerToReturn;


        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return new Owner();
        }
          
    }
 

   
    
}// end class

