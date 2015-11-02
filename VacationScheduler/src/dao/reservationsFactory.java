/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connections.RuthDBConnection;
import dataobjs.Guest;
import dataobjs.Owner;
import generic.DateFormatUtility;
import dataobjs.Reservation;
import java.sql.PreparedStatement;
import dataobjs.reservationsBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import vacationscheduler.DBConnection;

/**
 *
 * @author Erik Benscoter
 */
public class reservationsFactory
{
    
    public static void insert(Reservation reserve){
        Vector <String> paramVector = new Vector();
        String paramString = "";

        
        paramVector.add(reserve.getOwnerUserName());
        paramVector.add(reserve.getConfimationNumber());
        paramVector.add(Integer.toString(reserve.isWasUpgraded()));
        paramVector.add(DateFormatUtility.formatDateWyn(reserve.getDateOfReservation()));
        paramVector.add(Integer.toString(reserve.getNumberOfNights()));
        paramVector.add(reserve.getLocation());
        paramVector.add(reserve.getUnitSize());
        paramVector.add(reserve.getDateBooked());
        paramVector.add(reserve.getUpgradeState());
        paramVector.add(reserve.getNameOnGuestCert());
        
        for(int paramItterator = 0; paramItterator < paramVector.size() - 1; paramItterator++){
            paramString += "'"+ paramVector.get(paramItterator) + "', ";
        }
        paramString += "'"+ paramVector.get(paramVector.size() - 1)+ "'";
        
        
        
        
        String myInsertCommand = "INSERT INTO RESERVATIONS"
                + "(OWNER_USER_NAME,CONFIRMATION_NUMBER,WAS_UPGRADED,DATE_OF_RESERVATION,NUMBER_OF_NIGHTS,LOCATION,UNIT_SIZE,DATE_BOOKED,UPGRADE_STATUS,GUEST_CERTIF) "
                + "VALUES(" + paramString +")";
        String parametersString = "";
        
        System.out.println(myInsertCommand);
        DBConnection.insertSilent(myInsertCommand);
       
        
    }
    
    

    
   
    
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
    public static void UpdateScrapedInfo(Reservation p_rsrv)
    {
        Vector <String> paramVector = new Vector();
        Vector <String> columnNames = new Vector();
        String confirmationNumber = p_rsrv.getConfimationNumber();


        
        paramVector.add(p_rsrv.getOwnerUserName());
        paramVector.add(p_rsrv.getLocation());
        paramVector.add(DateFormatUtility.formatDateWyn(p_rsrv.getDateOfReservation()));
        paramVector.add(Integer.toString(p_rsrv.getNumberOfNights()));
        paramVector.add(p_rsrv.getUnitSize());
        paramVector.add(Integer.toString(p_rsrv.isWasUpgraded()));
        paramVector.add(p_rsrv.getDateBooked());
        paramVector.add(p_rsrv.getUpgradeState());
        paramVector.add(p_rsrv.getNameOnGuestCert());
        
		columnNames.add( "OWNER_USER_NAME" );
		columnNames.add( "LOCATION" );
		columnNames.add( "DATE_OF_RESERVATION" );
		columnNames.add( "NUMBER_OF_NIGHTS" );
		columnNames.add( "UNIT_SIZE" );
		columnNames.add( "WAS_UPGRADED" );
		columnNames.add( "DATE_BOOKED" );
		columnNames.add( "UPGRADE_STATUS" );
		columnNames.add( "GUEST_CERTIF" );

		String myUpdateCommand = "UPDATE RESERVATIONS SET ";

		for (int i = 0; i<columnNames.size(); i++ ) {
			
			//get the values we want as a pair
			String colName = columnNames.get(i);
			String value = paramVector.get(i);

			if(i != 0){
				myUpdateCommand += ", ";
			}

			myUpdateCommand += colName + " = '" + value + "'";

		}

		myUpdateCommand += " WHERE Confirmation_Number = '" + confirmationNumber + "'";

        
        System.err.print("UPDATE QUERY:: ");
        System.err.println(myUpdateCommand);
        DBConnection.insertSilent(myUpdateCommand);
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
    
    public static Reservation get(String confirmationNumber){
        Connection con = RuthDBConnection.getConnection();

        String myQuery = "SELECT * FROM Reservations where CONFIRMATION_NUMBER = '"+confirmationNumber + "' LIMIT 1";

        try{
            Statement statement= con.createStatement();
            ResultSet rs = statement.executeQuery(myQuery);
            
            String ownerUserName;
            Owner owner;
            String location;
            String dateOfReservation;
            //Use Date
            int numberOfNights;
            String unitSize;
            String confimationNumber;
            int pointsRequiredForReservation;
            boolean wasDiscounted, wasUpgraded, isBuyerLinedUp;
            Guest guest;
            double amountPaid = 0;
            Date datePaid;
            Reservation.PaymentMethod paymentMethod;
            double totalAmountRentedFor = 0;
            String dateBooked;
            
            Reservation r = new Reservation();
            
            rs.next();
            
            r.setOwnerUserName(rs.getString("Owner_User_Name"));
            r.setLocation(rs.getString("Location"));
            r.setDateOfReservation(rs.getString("Date_Of_Reservation"));
            r.setNumberOfNights(rs.getInt("Number_Of_Nights"));
            r.setUnitSize(rs.getString("UNIT_SIZE"));

            r.setConfimationNumber(rs.getString("CONFIRMATION_NUMBER"));
            r.setPointsRequiredForReservation(rs.getInt("POINTS_REQUIRED_FOR_RESERVATION"));
            r.setWasDiscounted(rs.getBoolean("WAS_DISCOUNTED"));
            r.setWasUpgraded(rs.getInt("WAS_UPGRADED"));
            r.setIsBuyerLinedUp(rs.getBoolean("IS_BUYER_LINED_UP"));
            
            r.setAmountPaid(rs.getDouble("AMOUNT_PAID"));
            //r.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
            r.setTotalAmountRentedFor(rs.getDouble("TOTAL_RENTING_FOR"));
            r.setDateBooked(rs.getString("DATE_BOOKED"));
            r.setUpgradeState(rs.getString("UPGRADE_STATUS"));
            r.setNameOnGuestCert(rs.getString("GUEST_CERTIF"));
            
           
            String guestEmail = rs.getString("GUEST_EMAIL");
            Guest reservationGuest = new Guest();
                reservationGuest.setEmailAddress(guestEmail);
            r.setGuest(reservationGuest);
            
            
            return r;
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        return new Reservation();
        
    }
    public static Vector<Reservation> getAll(String myQuery){
        Connection con = RuthDBConnection.getConnection();

        try{
            Statement statement= con.createStatement();
            ResultSet rs = statement.executeQuery(myQuery);
            Vector rtnVec = new Vector();
            
            String ownerUserName;
            Owner owner;
            String location;
            String dateOfReservation;
            //Use Date
            int numberOfNights;
            String unitSize;
            String confimationNumber;
            int pointsRequiredForReservation;
            boolean wasDiscounted, wasUpgraded, isBuyerLinedUp;
            Guest guest;
            double amountPaid = 0;
            Date datePaid;
            Reservation.PaymentMethod paymentMethod;
            double totalAmountRentedFor = 0;
            String dateBooked;
            
            
            while(rs.next()){            
                
                Reservation r = new Reservation();
                r.setOwnerUserName(rs.getString("Owner_User_Name"));
                r.setLocation(rs.getString("Location"));
                r.setDateOfReservation(rs.getString("Date_Of_Reservation"));
                r.setNumberOfNights(rs.getInt("Number_Of_Nights"));
                r.setUnitSize(rs.getString("UNIT_SIZE"));

                r.setConfimationNumber(rs.getString("CONFIRMATION_NUMBER"));
                r.setPointsRequiredForReservation(rs.getInt("POINTS_REQUIRED_FOR_RESERVATION"));
                r.setWasDiscounted(rs.getBoolean("WAS_DISCOUNTED"));
                r.setWasUpgraded(rs.getInt("WAS_UPGRADED"));
                r.setIsBuyerLinedUp(rs.getBoolean("IS_BUYER_LINED_UP"));

                r.setAmountPaid(rs.getDouble("AMOUNT_PAID"));
                //r.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
                r.setTotalAmountRentedFor(rs.getDouble("TOTAL_RENTING_FOR"));
                r.setDateBooked(rs.getString("DATE_BOOKED"));
                r.setUpgradeState(rs.getString("UPGRADE_STATUS"));
                r.setNameOnGuestCert(rs.getString("GUEST_CERTIF"));


                String guestEmail = rs.getString("GUEST_EMAIL");
                Guest reservationGuest = new Guest();
                    reservationGuest.setEmailAddress(guestEmail);
                r.setGuest(reservationGuest);
                
                rtnVec.add(r);
                
            }
            
            return rtnVec;
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        return new Vector();
        
    }
}
