/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import Connections.RuthDBConnection;
import dao.OwnersFactory;
import dataobjs.Guest;
import generic.DateFormatUtility;
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
    static String host = "jdbc:sqlite:./RuthDB";
    
    //old code
    
    
    //String host = "jdbc:derby://localhost:1527/VPDB";
    //String host = "jdbc:derby:VPDB";

    String uName = "root";
    String pass = "password";
    
    
/*public static Connection getConnection(){
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
    */
   
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
    /*
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
    
    */
    public static void insert(Reservation reserve){
        Vector <String> paramVector = new Vector();
        String paramString = "";

        
        paramVector.add(reserve.ownerUserName);
        paramVector.add(reserve.confimationNumber);
        paramVector.add(DateFormatUtility.formatDateWyn(reserve.dateOfReservation));
        paramVector.add(Integer.toString(reserve.numberOfNights));
        paramVector.add(reserve.location);
        paramVector.add(reserve.unitSize);
        paramVector.add(reserve.dateBooked);
        
        for(int paramItterator = 0; paramItterator < paramVector.size() - 1; paramItterator++){
            paramString += "'"+ paramVector.get(paramItterator) + "', ";
        }
        paramString += "'"+ paramVector.get(paramVector.size() - 1)+ "'";
        
        String myInsertCommand = "INSERT INTO RESERVATIONS"
                + "(OWNER_USER_NAME,CONFIRMATION_NUMBER,DATE_OF_RESERVATION,NUMBER_OF_NIGHTS,LOCATION,UNIT_SIZE,DATE_BOOKED) "
                + "VALUES(" + paramString +")";
        String parametersString = "";
        
        System.out.println(myInsertCommand);
        DBConnection.insertSilent(myInsertCommand);
       
        
    }
    public void insert(Guest g){
        Vector inserts = new Vector();
            inserts.add(g.getEmailAddress() );
            inserts.add(g.getFirstName() ) ;
            inserts.add(g.getLastName() ) ;
            inserts.add(g.getPhoneNumber() ) ;
            inserts.add(g.getCreditCardNumber()) ;
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
 /*   
    public Vector getAllOwnerEmails(){
        Connection con = getConnection();
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
    */
    /*
    public Vector getAllNames(String tableName){
        Connection con = RuthDBConnection.getConnection();
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
    */
    /*
    public Vector getAllEmails(String tableName){
        Connection con = RuthDBConnection.getConnection();
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
    */
  /*  
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
 */   
    
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        Vector results = OwnersFactory.getAllOwnerEmails();
        for(Object result : results){
            System.out.println(result);
        }
        
    }
    /*
    public static Owner get(String p_email){
        Owner ownerToReturn;
        String myQuery = "SELECT * FROM OWNERS WHERE First_Name = '"+p_email+"'";
        Connection con = getConnection();
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
            return ownerToReturn;


        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return new Owner();
        }
          
    }
    */
 /*   
    public static Vector getAllOwners(){
        Owner ownerToAdd;
        Vector ownerVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM OWNERS";
        Connection con = getConnection();
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
            return ownerVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return ownerVectorToReturn;
             
        }
    }
    */
   /*
    public static Vector getAllGuests(){
        Guest guestToAdd;
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

                guestToAdd = new Guest(emailAddress, firstName, lastName, phoneNumber, creditCardNumber, numberPreviousRentals);
                guestVectorToReturn.add(guestToAdd);
                
            }
            return guestVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return guestVectorToReturn;
             
        }
    }
    */
    /*
    public static Vector GetAllLocations(){
        Connection con;
        Statement st;
        ResultSet rs;
        String myQuery;
        Vector returnVector;
        String locationToAdd;
        
        
        myQuery = "SELECT * FROM LOCATIONS";
        returnVector =  new Vector();
        
        
        try{
            con = RuthDBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            while(rs.next()){
                locationToAdd = rs.getString("Location");
                returnVector.add(locationToAdd);
            }
            return returnVector;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error please try again\n" + e);
            return new Vector();
        }
        
        
        
    }
    */
    /*
    public static Vector GetAllUnitSizes(){
        Connection con;
        Statement st;
        ResultSet rs;
        String myQuery;
        Vector returnVector;
        String locationToAdd;
        
        
        myQuery = "SELECT * FROM UNITSIZES";
        returnVector =  new Vector();
        
        
        try{
            con = RuthDBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            while(rs.next()){
                locationToAdd = rs.getString("UnitSize");
                returnVector.add(locationToAdd);
            }
            return returnVector;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error please try again\n" + e);
            return new Vector();
        }
        
        
        
    }
    */
    /*
    
    public static boolean doesReservationExistInDB(String p_confirmationNumber){
        String myQuery;
        Connection con;
        Statement st;
        ResultSet rs;
        boolean isInDB = true;
        
        myQuery = "SELECT COUNT(*) FROM RESERVATIONS WHERE CONFIRMATION_NUMBER = '"+p_confirmationNumber+"'";
        
        try{
        con = RuthDBConnection.getConnection();
        st = con.createStatement();
        rs = st.executeQuery(myQuery);
        
        int numberOfMatches = rs.getInt(1);
        rs.close();
        
        if(numberOfMatches == 0){
            isInDB = false;
        }else{
            isInDB = true;
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "DB ERROR " + e);
            System.exit(-1);
        }
        
        return isInDB;
        
    }
    */
  /*  
    public static void insertScrapedReservation(Vector<String> p_vectorInput, String p_ownerUserName){
        String myInsertCommand = "INSERT INTO RESERVATIONS"
                + "(OWNER_USER_NAME,CONFIRMATION_NUMBER,DATE_OF_RESERVATION,NUMBER_OF_NIGHTS,LOCATION,UNIT_SIZE,DATE_BOOKED,GUEST_EMAIL,WAS_UPGRADED) "
                + "VALUES(";
        String parametersString = "";
        
        parametersString += "'"+p_ownerUserName+"',";
        for(int itterator = 0; itterator < p_vectorInput.size() - 1; itterator ++){
            String currParam = p_vectorInput.get(itterator).replace("'", "");
            parametersString += "'" + currParam + "', "; 
        }
        parametersString += "'1'";
        
        myInsertCommand += parametersString;
        myInsertCommand += ")";
        
        System.out.println(myInsertCommand);
        DBConnection.insertSilent(myInsertCommand);
                
        
    }
    
   */
    
}
