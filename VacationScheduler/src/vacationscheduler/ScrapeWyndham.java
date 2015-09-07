/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class ScrapeWyndham {
    static WebDriver firefoxWindow;
     public static Vector <Vector> getUserReservations(String p_username, String p_password) {

        //Declarations
        String webpageSrc;
        Vector <Vector> response;
        
        //create A firefox window
        firefoxWindow = initializeWindow();
        
        //log into wyndham
        logIn(p_username,p_password,firefoxWindow);

        //move to wyndham member reservation confirmations
         firefoxWindow.get( "https://www.myclubwyndham.com/ffr/secure/member/reservation_summary/reservationSummary.do" );
         
         //get the information on the page
         webpageSrc = firefoxWindow.getPageSource();

        //get all of the reservation information on the page
        response = getAllReservationsOnPage(webpageSrc);
        
        int pageItterator = 1;
        try{
            while(true){
                pageItterator++;
                WebElement nextPage = firefoxWindow.findElement(By.linkText(Integer.toString(pageItterator)));
                nextPage.click();
                webpageSrc = firefoxWindow.getPageSource();
                response = getAllReservationsOnPage(webpageSrc, response);
                
            }
        }catch(Exception e){
            
        }
         
        
         System.out.println("from function = " + response.get(0).size());
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
        
        userNameElement = firefoxWindow.findElement( By.name("userNamelabel") );
        userNameElement.sendKeys( p_userName );

        passwordElement = firefoxWindow.findElement( By.name("passwordlabel" ) );
        passwordElement.sendKeys( p_password );
        passwordElement.submit();
     }
     public static Vector getAllReservationsOnPage(String p_webpageSrc){
        Vector <Vector> allReservations = new Vector();
        getAllReservationsOnPage(p_webpageSrc,allReservations);
        return allReservations;
     }
     public static Vector getAllReservationsOnPage(String p_webpageSrc, Vector <Vector> p_vectorOfVectors){
         
         String currentReservation;
         Vector currentReservationVector = new Vector();
         
        //for every reservation on the page get the information from it
        for(int reservationItterator = 1; reservationItterator <= 10; reservationItterator ++){
             currentReservation = p_webpageSrc.split("name=\"selectedConfirmation\"")[reservationItterator];
             currentReservationVector = parseReservation(currentReservation);
             p_vectorOfVectors.add(currentReservationVector);
             System.out.println("");
        }
         System.err.println("here = " + p_vectorOfVectors.get(0).size());
        return p_vectorOfVectors;
     }
    public static Vector parseReservation(String currentReservation){
        
        
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
        
        Vector vectorToReturn = new Vector();
        vectorToReturn.add(confirmationNumber);
        vectorToReturn.add(checkInDate);
        vectorToReturn.add(numNights);
        vectorToReturn.add(resortName);
        vectorToReturn.add(unitType);
        vectorToReturn.add(bookedDate);
        vectorToReturn.add(traveler);
        vectorToReturn.add(upgradeState);
        
        return vectorToReturn;
        
        
        
    }
    public static void closeWindow(){
        firefoxWindow.close();
    }
}
