/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;
    
import java.util.Date;
import java.util.Vector;



/**
 *
 * @author erikbenscoter
 */
public class Reservation {
    public enum PaymentMethod{
        CASH,CREDIT,OTHER
    }
    
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
    PaymentMethod paymentMethod;
    double totalAmountRentedFor = 0;
    String dateBooked;
    
    public Reservation( Owner owner,String location,String dateOfReservation,int numberOfNights,
                        String unitSize,String confimationNumber,int pointsRequiredForReservation,
                        boolean wasDiscounted,boolean wasUpgraded, boolean isBuyerLinedUp,Guest guest,
                        double amountPaid, Date datePaid, PaymentMethod paymentMethod,double totalAmountRentedFor){
        this.owner = owner;
        this.location = location;
        this.dateOfReservation = dateOfReservation;
        //Use Date
        this.numberOfNights = numberOfNights;
        this.unitSize = unitSize;
        this.confimationNumber = confimationNumber;
        this.pointsRequiredForReservation = pointsRequiredForReservation;
        this.wasDiscounted = wasDiscounted;
        this.wasUpgraded = wasUpgraded;
        this.isBuyerLinedUp = isBuyerLinedUp;
        this.guest = guest;
        this.amountPaid = amountPaid;
        this.datePaid = datePaid;
        this.paymentMethod = paymentMethod;
        this.totalAmountRentedFor = totalAmountRentedFor;
    }
    
     public void totalReset( Owner owner,String location,String dateOfReservation,int numberOfNights,
                        String unitSize,String confimationNumber,int pointsRequiredForReservation,
                        boolean wasDiscounted,boolean wasUpgraded, boolean isBuyerLinedUp,Guest guest,
                        double amountPaid, Date datePaid, PaymentMethod paymentMethod, double totalAmountRentedFor){
        this.owner = owner;
        this.location = location;
        this.dateOfReservation = dateOfReservation;
        //Use Date
        this.numberOfNights = numberOfNights;
        this.unitSize = unitSize;
        this.confimationNumber = confimationNumber;
        this.pointsRequiredForReservation = pointsRequiredForReservation;
        this.wasDiscounted = wasDiscounted;
        this.wasUpgraded = wasUpgraded;
        this.isBuyerLinedUp = isBuyerLinedUp;
        this.guest = guest;
        this.amountPaid = amountPaid;
        this.datePaid = datePaid;
        this.paymentMethod = paymentMethod;
        this.totalAmountRentedFor = totalAmountRentedFor;
    }
     public convertVectorReservation(Vector <String> p_scrapedVector){
        this.location = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.RESORT.getIndex());
        this.dateOfReservation = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.CHECK_IN_DATE.getIndex());
        this.numberOfNights = Integer.parseInt(p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.NUMBER_NIGHTS.getIndex()));
        this.unitSize = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.SIZE.getIndex());
        this.confimationNumber = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.CONFIRMATION_NUMBER.getIndex());
        this.dateBooked = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.BOOKED.getIndex());
    }
    
    
}
