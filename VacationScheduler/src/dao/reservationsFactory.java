/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connections.RuthDBConnection;
import generic.DateFormatUtility;
import vacationscheduler.Reservation;
import java.sql.PreparedStatement;
import dataobjs.reservationsBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import vacationscheduler.DBConnection;

/**
 *
 * @author Glenn Benscoter
 */
public class reservationsFactory
{
/*    
        public static void insert(Reservation reserve){
        Vector <String> paramVector = new Vector();
        String paramString = "";

        
        paramVector.add(reserve.ownerUserName);
        paramVector.add(reserve.confimationNumber);
        paramVector.add(reserve.dateOfReservation);
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
  */  
    

    
   
    
       public static void insertScrapedReservation(Vector<String> p_vectorInput, String p_ownerUserName){
        String myInsertCommand = "INSERT INTO RESERVATIONS"
                + "(OWNER_USER_NAME,CONFIRMATION_NUMBER,DATE_OF_RESERVATION,NUMBER_OF_NIGHTS,LOCATION,UNIT_SIZE,DATE_BOOKED,GUEST_EMAIL,WAS_UPGRADED) "
                + "VALUES(";
        String parametersString = "";
        System.out.println("at the top of insertScrapedReservation **********");
        
        parametersString += "'"+p_ownerUserName+"',";
        for(int itterator = 0; itterator < p_vectorInput.size() - 1; itterator ++){
            String currParam = p_vectorInput.get(itterator).replace("'", "");
            parametersString += "'" + currParam + "', "; 
        }
        parametersString += "'1'";
        
        myInsertCommand += parametersString;
        myInsertCommand += ")";
        
        System.out.println(myInsertCommand);
        reservationsFactory.insertSilent(myInsertCommand);
        System.out.println("at the Bottom of insertScrapedReservation **********");
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
    public static void UpdateCurrentReservation(Reservation current_scraped_reservation)
  
    {
        System.out.println("at top of UpdateCurrentReservation = " + current_scraped_reservation);
        try
        {
        Connection conn = RuthDBConnection.getConnection();
        Vector <String> scrapedResVector = new Vector();
        String paramString = "";
        /*
        scrapedResVector.add(current_scraped_reservation.ownerUserName);
        scrapedResVector.add(current_scraped_reservation.confimationNumber);
        scrapedResVector.add(DateFormatUtility.formatDateWyn(current_scraped_reservation.dateOfReservation));
        scrapedResVector.add(Integer.toString(current_scraped_reservation.numberOfNights));
        scrapedResVector.add(current_scraped_reservation.location);
        scrapedResVector.add(current_scraped_reservation.unitSize);
        scrapedResVector.add(current_scraped_reservation.dateBooked);
    */
    /*    
        for(int paramItterator = 0; paramItterator < paramVector.size() - 1; paramItterator++){
            paramString += "'"+ paramVector.get(paramItterator) + "', ";
        }
        paramString += "'"+ paramVector.get(paramVector.size() - 1)+ "'";
    
        
        String myInsertCommand = "INSERT INTO RESERVATIONS"
                + "(OWNER_USER_NAME,CONFIRMATION_NUMBER,DATE_OF_RESERVATION,NUMBER_OF_NIGHTS,LOCATION,UNIT_SIZE,DATE_BOOKED) "
                + "VALUES(" + paramString +")";
        */
        
  
      String resconfimationNumber;
        resconfimationNumber= current_scraped_reservation.getConfimationNumber();

        System.out.println("Confirmation number = " + resconfimationNumber);
        PreparedStatement stmt = conn.prepareStatement(
                "Update reservations           "
               +"    set Unit_Size = ?         "
          /*     +"         set Guest = ?        "  */
               +"  where CONFIRMATION_NUMBER = ?");
        
           stmt.setString(1,current_scraped_reservation.getUnitSize());
       /*    stmt.setString(2,current_scraped_reservation.getGuest());  */
           stmt.setString(2,current_scraped_reservation.getConfimationNumber());      
          
           stmt.executeUpdate();
           stmt.close();
           
        System.out.println("after Update" );
     /*   
        
        String parametersString = "";
        
        System.out.println(myInsertCommand);
        DBConnection.insertSilent(myInsertCommand);
     */
        } 
        catch (Exception e)
                {System.out.println("Exception*****" + e );
                throw new RuntimeException (e);
                }
        System.out.println("at bottom of UpdateCurrentReservation" );
        return;
    }

    @Override
    public String toString() {
        return "reservationsFactory{" + '}';
    }
    public static String getReservationWithinDays(String Days )
    { 
        Connection con = RuthDBConnection.getConnection();
        return Days;
    }
}
