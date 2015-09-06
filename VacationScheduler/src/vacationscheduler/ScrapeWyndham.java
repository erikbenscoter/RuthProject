/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author erikbenscoter
 */
public class ScrapeWyndham {
     public static void main(String[] args) {
        WebDriver firefoxWindow = new FirefoxDriver();
        firefoxWindow.get( "https://www.myclubwyndham.com/ffr/index.do" );

        WebElement userNameElement;
        WebElement passwordElement;
        
        String webpageSrc;
        
        String currentReservation;
        
        userNameElement = firefoxWindow.findElement( By.name("userNamelabel") );
        userNameElement.sendKeys( "CarolynBenscoter" );

        passwordElement = firefoxWindow.findElement( By.name("passwordlabel" ) );
        passwordElement.sendKeys("sunnyboy1" );
        passwordElement.submit();

        firefoxWindow.get( "https://www.myclubwyndham.com/ffr/secure/member/reservation_summary/reservationSummary.do" );
        webpageSrc = firefoxWindow.getPageSource();

        for(int reservationItterator = 1; reservationItterator <= 10; reservationItterator ++){
            currentReservation = webpageSrc.split("name=\"selectedConfirmation\"")[reservationItterator];
            parseReservation(currentReservation);
            System.out.println("");
        }
        
    }
    public static void parseReservation(String currentReservation){
        
        
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
    }
}
