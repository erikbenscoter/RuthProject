/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author erikbenscoter
 */
public class ConfirmationParser {
    static String inputToParse;
    
    Vector linesOfInformation = new Vector();
   
    public static EmailReservationInformation parseIt(String p_email, ReservationForm p_reservationForm){
        inputToParse = p_email;
        inputToParse = inputToParse.split("Resort Address")[1];
        inputToParse = inputToParse.split("If you cannot .*")[0];
        String[] attributes = inputToParse.split("\t");
        
        String fullName = attributes[0].trim();
        String location = attributes[1];
        String arrivalDate = attributes[2];
        String departureDate = attributes[3];
        String confirmationNumber = attributes[4].trim();
        String addressOfResort = attributes[5];
        
        EmailReservationInformation eri = new EmailReservationInformation();
        eri.account = fullName;
        eri.resort = location;
        eri.arrivalDate = arrivalDate;
        eri.departureDate = departureDate;
        eri.resortAddress = addressOfResort;
       
        //parse start day
        String month = arrivalDate.split("/")[0].trim();
        String day = arrivalDate.split("/")[1].trim();
        String year = arrivalDate.split("/")[2].trim();
        
        //parse end date
        String depMonth = departureDate.split("/")[0].trim();
        String depDay = departureDate.split("/")[1].trim();
        String depYear = departureDate.split("/")[2].trim();
        
        //set start date
        p_reservationForm.Combobox_Month.setSelectedItem(month);
        p_reservationForm.Combobox_Day.setSelectedItem(day);
        p_reservationForm.Combobox_Year.setSelectedItem(year);
        
        //set name
        p_reservationForm.ComboBox_Owner.setSelectedItem(fullName);
        
        //set confirmation number
        p_reservationForm.TextBox_ConfirmationNumber.setText(confirmationNumber);
        
        //calculate the difference between start and end
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        
        start.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        end.set(Integer.parseInt(depYear),Integer.parseInt(depMonth), Integer.parseInt(depDay));
        
        Date endDate = end.getTime();
        Date startDate = start.getTime();
        
        long diffTime = endDate.getTime()-startDate.getTime();
        ;
        
        
        p_reservationForm.TextBox_NumberOfNights.setText(Long.toString(TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS)));
        
        
        return eri;
        
                
    }
}
