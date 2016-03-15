/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import static dao.reservationsFactory.wasReservationAlreadyPulled;
import dataobjs.Guest;
import dataobjs.Reservation;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 *
 * @author erikbenscoter
 */
public class ScrapeWyndham
{
    static WebDriver firefoxWindow;
    
    
    public static Vector <Reservation> getUserReservations(String p_username, String p_password, Vector <Reservation> p_startingVector){
        
        String webpageSrc;

        //create A firefox window
        if (firefoxWindow == null){
            firefoxWindow = initializeWindow();
        }
        //log into wyndham
        logIn(p_username,p_password,firefoxWindow);

        //move to wyndham member reservation confirmations
         firefoxWindow.get( "https://www.myclubwyndham.com/ffr/secure/member/reservation_summary/reservationSummary.do" );
         
         //get the information on the page
         webpageSrc = firefoxWindow.getPageSource();

        //get all of the reservation information on the page
        getAllReservationsOnPage(webpageSrc, p_startingVector);
        
        int pageItterator = 1;
        try{
            while(true){
                pageItterator++;
                System.out.println("clicking page " + pageItterator);
                WebElement nextPage = firefoxWindow.findElement(By.linkText(Integer.toString(pageItterator)));
                nextPage.click();
                int oldSizeOfVector = p_startingVector.size();
                int newSizeOfVector=-10;
                do{
                    webpageSrc = firefoxWindow.getPageSource();
                    getAllReservationsOnPage(webpageSrc, p_startingVector);
                    newSizeOfVector = p_startingVector.size();
                }while(newSizeOfVector == oldSizeOfVector); //if the page didn't load quick enough
                
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
         
        
         System.out.println("from function = " + p_startingVector.size());
         logout(firefoxWindow);
         return p_startingVector;
    }
    public static Vector <Reservation> getUserReservations(String p_username, String p_password) {

        //Declarations
        
        Vector <Reservation> response = new Vector();
        response = getUserReservations(p_username, p_password, response);
        return response;
         
    }
     public static WebDriver initializeWindow(){
         
       WebDriver firefoxWindow = new FirefoxDriver();
     //   WebDriver firefoxWindow = new ChromeDriver();
     //   WebDriver firefoxWindow = new InternetExplorerDriver();
        firefoxWindow.get( "https://www.myclubwyndham.com/ffr/index.do" );
        return firefoxWindow;
     }
     public static void logIn(String p_userName, String p_password, WebDriver firefoxWindow){
        WebElement userNameElement;
        WebElement passwordElement;
        
        try{
            userNameElement = firefoxWindow.findElement( By.name("userNamelabel") );
            userNameElement.sendKeys( p_userName );

            passwordElement = firefoxWindow.findElement( By.name("passwordlabel" ) );
            passwordElement.sendKeys( p_password );
            passwordElement.submit();
        }catch(Exception e){
            logIn(p_userName, p_password, firefoxWindow);
        }
     }
     public static Vector getAllReservationsOnPage(String p_webpageSrc){
        Vector <Reservation> allReservations = new Vector();
        getAllReservationsOnPage(p_webpageSrc,allReservations);
        return allReservations;
     }
     public static Vector getAllReservationsOnPage(String p_webpageSrc, Vector <Reservation> p_vectorOfReservations){
         
         String currentReservation;
         //Vector currentReservationVector = new Vector();
         Reservation reservationObject;
         int numReservations;
         
        numReservations = p_webpageSrc.split("selectedConfirmation").length;
        System.out.println("number of reservations detected = " + numReservations);
        
        //for every reservation on the page get the information from it
        for(int reservationItterator = 1; reservationItterator < numReservations; reservationItterator ++){
             currentReservation = p_webpageSrc.split("name=\"selectedConfirmation\"")[reservationItterator];
             
             reservationObject = parseReservation(currentReservation);
             
             if( !wasReservationAlreadyPulled( reservationObject.getConfimationNumber() ) ){
    
                int pointsRequired = getPointsRequired(reservationObject.getConfimationNumber());
                reservationObject.setPointsRequiredForReservation(pointsRequired);
    
             }
             
             
            
             
             
             p_vectorOfReservations.add(reservationObject);
             System.out.println("");
        }
         System.err.println("reservations from page = " + p_vectorOfReservations.size());
        return p_vectorOfReservations;
     }
    public static Reservation parseReservation(String currentReservation){
        
        
        String confirmationNumber;
        String checkInDate;
        String numNights;
        String resortName;
        String unitType;
        String bookedDate;
        String traveler;
        String upgradeState;
        
        Pattern confirmationNumberPattern = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9]");
        Matcher confirmationNumberMatcher = confirmationNumberPattern.matcher(currentReservation);
        confirmationNumberMatcher.find();
        confirmationNumber = confirmationNumberMatcher.group(0);
        
        
        Pattern checkInDatePattern = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
        Matcher checkInDateMatcher = checkInDatePattern.matcher(currentReservation);
        checkInDateMatcher.find();
        checkInDate = checkInDateMatcher.group(0);
        
        numNights = currentReservation.split(checkInDate)[1];
        Pattern numNightsPattern = Pattern.compile("[0-9]+");
        Matcher numNightsMatcher = numNightsPattern.matcher(numNights);
        numNightsMatcher.find();
        numNights = numNightsMatcher.group(0);
        
        
        resortName = currentReservation.split("reservationConfirm_ResortName")[1];
        Pattern resortNamePattern = Pattern.compile(".+..td");
        Matcher resortNameMatcher = resortNamePattern.matcher(resortName);
        resortNameMatcher.find();
        resortName = resortNameMatcher.group(0);
        resortName = resortName.replace("</td", "");
        resortName = resortName.replace("\">", "");
        
        unitType = currentReservation.split(resortName)[1];
        unitType = unitType.split("<td>")[1];
        unitType = unitType.split("</td>")[0];
        
        bookedDate = currentReservation.split(unitType)[1];
        Pattern bookedDatePattern = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
        Matcher bookedDateMatcher = bookedDatePattern.matcher(bookedDate);
        bookedDateMatcher.find();
        bookedDate = bookedDateMatcher.group(0);
        
        traveler = currentReservation.split(bookedDate)[1];
        Pattern travelerPattern = Pattern.compile("(Guest|Owner): .* .*");
        Matcher travelerMatcher = travelerPattern.matcher(traveler);
        travelerMatcher.find();
        traveler = travelerMatcher.group(0);
        
        
        Pattern guestOrOwnerPattern = Pattern.compile("Guest:");
        Matcher guestMatcher = guestOrOwnerPattern.matcher(traveler);
        boolean isThereAGuestCertificate = guestMatcher.find();
        
        
       
        
        upgradeState = currentReservation.split(traveler)[1];
        upgradeState = upgradeState.split("<td ")[1];
        upgradeState = upgradeState.split(">")[1];
        upgradeState = upgradeState.split("</td")[0];
        upgradeState = upgradeState.trim();
        
        
        System.out.println( "confirmation number: " + confirmationNumber );
        System.out.println( "checkInDate: " + checkInDate );
        System.out.println( "number of nights: " + numNights );
        System.out.println( "resort name: " + resortName );
        System.out.println( "unit type: " + unitType );
        System.out.println( "booked date: " + bookedDate );
        System.out.println( "traveler: " + traveler );
        System.out.println( "upgrade state: " + upgradeState);
        
//        Vector vectorToReturn = new Vector();
//        vectorToReturn.add(confirmationNumber);
//        vectorToReturn.add(checkInDate);
//        vectorToReturn.add(numNights);
//        vectorToReturn.add(resortName);
//        vectorToReturn.add(unitType);
//        vectorToReturn.add(bookedDate);
//        vectorToReturn.add(traveler);
//        vectorToReturn.add(upgradeState);
        
        Reservation rsrv = new Reservation();
        rsrv.setConfimationNumber(confirmationNumber);
        rsrv.setDateOfReservation(checkInDate);
        rsrv.setNumberOfNights(Integer.parseInt(numNights));
        rsrv.setLocation(resortName);
        rsrv.setUnitSize(unitType);
        rsrv.setDateBooked(bookedDate);
        rsrv.setUpgradeState(upgradeState);
        rsrv.setIsBuyerLinedUp(isThereAGuestCertificate);
        
        if(isThereAGuestCertificate){
            Guest guest = new Guest();
            String guestName = (traveler.split("Guest:")[1]);
            String guestFirstName = guestName.split(" ")[1];
            String guestLastName = guestName.split(" ")[2];
                System.err.println("First Name = " + guestFirstName);
                System.err.println("Last Name = " + guestLastName);
            if(guestFirstName == null){
                guestFirstName = "";
            }
            if(guestLastName == null){
                guestLastName = "";
            }

            
            guest.setFirstName(guestFirstName);
            guest.setLastName(guestLastName);
            
            rsrv.setGuest(guest);
            rsrv.setNameOnGuestCert(guestFirstName + " " + guestLastName);

        }
        else{
            Guest guest = new Guest();
            guest.setFirstName("_OWNER_");
            guest.setLastName(traveler);
            rsrv.setGuest(guest);
            rsrv.setNameOnGuestCert(guest.getFirstName() + " " + guest.getLastName());
            
        }
        
        
        
        
        return rsrv;
        
    }
    public static int getPointsRequired(String p_confirmationNumber){
        
        int pointsRequired = 0;
        
        System.out.println("+++++++ confirmation number " + p_confirmationNumber);
        
        WebElement confirmationLink = firefoxWindow.findElement(By.linkText(p_confirmationNumber));
        confirmationLink.click();
        
        try {
            //wait
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScrapeWyndham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String webpageSrc = firefoxWindow.getPageSource();
        pointsRequired = parsePointsRequired( webpageSrc );
        
        firefoxWindow.navigate().back();
        
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScrapeWyndham.class.getName()).log(Level.SEVERE, null, ex);
        }
        firefoxWindow.getPageSource();
        
        
        
        return pointsRequired;
    }
    
    public static int parsePointsRequired(String p_webpageSrc){
        
        int pointsRequired = 0;
        
        String stringForParsing = "";
        
        stringForParsing = p_webpageSrc.split("Points Used:")[1];
        stringForParsing = stringForParsing.split("</strong>")[1];
        stringForParsing = stringForParsing.split("</p>")[0];
        stringForParsing = stringForParsing.trim();
        
        pointsRequired = (int) Integer.parseInt(stringForParsing);
        
        
        System.out.println("POINTS ::: " + pointsRequired);
        
        return pointsRequired;
    }
    
    
    public static void logout(WebDriver p_firefoxWindow){
        WebElement logoutBtn = p_firefoxWindow.findElement(By.linkText("Logout"));
        logoutBtn.click();
    }
    public static void closeWindow(){
        firefoxWindow.close();
    }
}
