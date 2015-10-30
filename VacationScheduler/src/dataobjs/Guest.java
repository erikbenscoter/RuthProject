/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataobjs;

/**
 *
 * @author erikbenscoter
 */
public class Guest {
    String emailAddress, firstName,lastName,phoneNumber;
    int creditCardNumber,numberPreviousRentals;
    
    public Guest(){
        emailAddress = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        
    }
    
    public Guest(String ea, String fn, String ln, String pn, int ccn, int npr){
        emailAddress =ea;
        firstName = fn;
        lastName = ln;
        phoneNumber = pn;
        creditCardNumber = ccn;
        numberPreviousRentals = npr;
       
    }
    public void totalReset(String ea, String fn, String ln, String pn, int ccn, int npr){
        emailAddress =ea;
        firstName = fn;
        lastName = ln;
        phoneNumber = pn;
        creditCardNumber = ccn;
        numberPreviousRentals = npr;        
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getNumberPreviousRentals() {
        return numberPreviousRentals;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setNumberPreviousRentals(int numberPreviousRentals) {
        this.numberPreviousRentals = numberPreviousRentals;
    }
    
    
}
