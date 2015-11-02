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
    boolean wasDiscounted, isBuyerLinedUp;
    int wasUpgraded;
    String upgradeState;
    Guest guest;
    double amountPaid = 0;
    Date datePaid;
    PaymentMethod paymentMethod;
    double totalAmountRentedFor = 0;
    String nameOnGuestCert = "";

   
    String dateBooked;
    public Reservation(){
        location = "";
        dateOfReservation = "";
        ownerUserName = "";
        unitSize = "";
        confimationNumber = "";
        
    }
    public Reservation( Owner owner,String location,String dateOfReservation,int numberOfNights,
                        String unitSize,String confimationNumber,int pointsRequiredForReservation,
                        boolean wasDiscounted,int wasUpgraded, boolean isBuyerLinedUp,Guest guest,
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
                        boolean wasDiscounted,int wasUpgraded, boolean isBuyerLinedUp,Guest guest,
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
     
     public enum scrapedIndicies{
        CONFIRMATION_NUMBER (0),
        CHECK_IN_DATE(1),
        NUMBER_NIGHTS(2),
        RESORT(3),
        SIZE(4),
        BOOKED(5),
        TRAVELER(6),
        UPGRADE(7)
                ;
        private final int index;
      private scrapedIndicies(int input)
      {
          index = input;
      }  
      public int getIndex()
      {
          return index;
      }
    };
     
     
     public Reservation(Vector <String> p_scrapedVector){
        this.location = p_scrapedVector.get((int) scrapedIndicies.RESORT.getIndex());
        this.dateOfReservation = p_scrapedVector.get((int) scrapedIndicies.CHECK_IN_DATE.getIndex());
        this.numberOfNights = Integer.parseInt(p_scrapedVector.get((int) scrapedIndicies.NUMBER_NIGHTS.getIndex()));
        this.unitSize = p_scrapedVector.get((int) scrapedIndicies.SIZE.getIndex());
        this.confimationNumber = p_scrapedVector.get((int) scrapedIndicies.CONFIRMATION_NUMBER.getIndex());
        this.dateBooked = p_scrapedVector.get((int) scrapedIndicies.BOOKED.getIndex());
        
        //take care of special characters
        removeSpecialCharacters(this);
        
        
    }
     
    public Vector <String> toVector(){
        Vector <String> rtnVal = new Vector();
        rtnVal.add(scrapedIndicies.CONFIRMATION_NUMBER.getIndex(),confimationNumber);
        rtnVal.add(scrapedIndicies.CHECK_IN_DATE.getIndex(), dateOfReservation);
        rtnVal.add(scrapedIndicies.NUMBER_NIGHTS.getIndex(), Integer.toString(numberOfNights));
        rtnVal.add(scrapedIndicies.RESORT.getIndex(), location);
        rtnVal.add(scrapedIndicies.SIZE.getIndex(),unitSize);
        rtnVal.add(scrapedIndicies.BOOKED.getIndex(),dateBooked);
        rtnVal.add(scrapedIndicies.TRAVELER.getIndex(),getNameOnGuestCert());
        rtnVal.add(scrapedIndicies.UPGRADE.getIndex(),upgradeState);

        
        return rtnVal;
                
        
        
        
    }
    public static void removeSpecialCharacters(Reservation p_reservationInput){
        p_reservationInput.location = p_reservationInput.location.replace("'", "");
        
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        removeSpecialCharacters(this);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        removeSpecialCharacters(this);
    }

    public String getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(String dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
                removeSpecialCharacters(this);

    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
                removeSpecialCharacters(this);

    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
                removeSpecialCharacters(this);

    }

    public String getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
                removeSpecialCharacters(this);

    }

    public String getConfimationNumber() {
        return confimationNumber;
    }

    public void setConfimationNumber(String confimationNumber) {
        this.confimationNumber = confimationNumber;
                removeSpecialCharacters(this);

    }

    public int getPointsRequiredForReservation() {
        return pointsRequiredForReservation;
    }

    public void setPointsRequiredForReservation(int pointsRequiredForReservation) {
        this.pointsRequiredForReservation = pointsRequiredForReservation;
                removeSpecialCharacters(this);

    }

    public boolean isWasDiscounted() {
        return wasDiscounted;
    }

    public void setWasDiscounted(boolean wasDiscounted) {
        this.wasDiscounted = wasDiscounted;
                removeSpecialCharacters(this);

    }

    public int isWasUpgraded() {
        return wasUpgraded;
    }

    public void setWasUpgraded(int wasUpgraded) {
        this.wasUpgraded = wasUpgraded;
                removeSpecialCharacters(this);

    }

    public boolean isIsBuyerLinedUp() {
        return isBuyerLinedUp;
    }

    public void setIsBuyerLinedUp(boolean isBuyerLinedUp) {
        this.isBuyerLinedUp = isBuyerLinedUp;
                removeSpecialCharacters(this);

    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
                removeSpecialCharacters(this);

    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
                removeSpecialCharacters(this);

    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
                removeSpecialCharacters(this);

    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
                removeSpecialCharacters(this);

    }

    public double getTotalAmountRentedFor() {
        return totalAmountRentedFor;
    }

    public void setTotalAmountRentedFor(double totalAmountRentedFor) {
        this.totalAmountRentedFor = totalAmountRentedFor;
                removeSpecialCharacters(this);

    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
                removeSpecialCharacters(this);

    }
    
     public String getUpgradeState() {
        return upgradeState;
    }

    public void setUpgradeState(String upgradeState) {
        //System.err.println(upgradeState);
        if(!(upgradeState.contains("Reservation Upgraded") || upgradeState.contains("Not in Upgrade Window"))){
            upgradeState = "upgrades may be available";
        }
        
        if(upgradeState.contains("Reservation Upgraded")){
            this.wasUpgraded = 1;
        }else{
            this.wasUpgraded = 0;
        }
        this.upgradeState = upgradeState;
        removeSpecialCharacters(this);

    }

    public String getNameOnGuestCert() {
        return nameOnGuestCert;
    }

    public void setNameOnGuestCert(String nameOnGuestCert) {
        this.nameOnGuestCert = nameOnGuestCert;
    }
    
    
}
