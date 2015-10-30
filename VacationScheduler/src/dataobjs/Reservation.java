/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataobjs;
    
import dataobjs.Owner;
import dataobjs.Guest;
import java.util.Date;
import java.util.Vector;
import vacationscheduler.ScrapeWyndham;



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
    String ownerUserName;
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
    public Reservation(){
        
    }
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
     public Reservation(Vector <String> p_scrapedVector){
        this.location = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.RESORT.getIndex());
        this.dateOfReservation = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.CHECK_IN_DATE.getIndex());
        this.numberOfNights = Integer.parseInt(p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.NUMBER_NIGHTS.getIndex()));
        this.unitSize = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.SIZE.getIndex());
        this.confimationNumber = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.CONFIRMATION_NUMBER.getIndex());
        this.dateBooked = p_scrapedVector.get((int) ScrapeWyndham.scrapedIndicies.BOOKED.getIndex());
        
        //take care of special characters
        removeSpecialCharacters(this);
        
        
    }
    public static void removeSpecialCharacters(Reservation p_reservationInput){
        p_reservationInput.location = p_reservationInput.location.replace("'", "");
        
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(String dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

    public String getConfimationNumber() {
        return confimationNumber;
    }

    public void setConfimationNumber(String confimationNumber) {
        this.confimationNumber = confimationNumber;
    }

    public int getPointsRequiredForReservation() {
        return pointsRequiredForReservation;
    }

    public void setPointsRequiredForReservation(int pointsRequiredForReservation) {
        this.pointsRequiredForReservation = pointsRequiredForReservation;
    }

    public boolean isWasDiscounted() {
        return wasDiscounted;
    }

    public void setWasDiscounted(boolean wasDiscounted) {
        this.wasDiscounted = wasDiscounted;
    }

    public boolean isWasUpgraded() {
        return wasUpgraded;
    }

    public void setWasUpgraded(boolean wasUpgraded) {
        this.wasUpgraded = wasUpgraded;
    }

    public boolean isIsBuyerLinedUp() {
        return isBuyerLinedUp;
    }

    public void setIsBuyerLinedUp(boolean isBuyerLinedUp) {
        this.isBuyerLinedUp = isBuyerLinedUp;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmountRentedFor() {
        return totalAmountRentedFor;
    }

    public void setTotalAmountRentedFor(double totalAmountRentedFor) {
        this.totalAmountRentedFor = totalAmountRentedFor;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }
    
    
}
