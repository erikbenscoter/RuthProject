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
public class Owner {
    public String emailAddress, firstName,lastName,phoneNumber,userName,password;
    public int pointsOwned, currentAvailablePts;
    public double reimbursementRate;
    public Owner(){
        emailAddress = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        userName = "";
        password = "";
    }
    public Owner(String ea, String fn, String ln, String pn, String un, String pwd, int po, int cap, double rr){
        emailAddress =ea.trim();
        firstName = fn.trim();
        lastName = ln.trim();
        phoneNumber = pn.trim();
        userName = un.trim();
        password = pwd.trim();
        pointsOwned = po;
        currentAvailablePts = cap;
        reimbursementRate = rr;
    }
     public void totalReset(String ea, String fn, String ln, String pn, String un, String pwd, int po, int cap, double rr){
        emailAddress =ea.trim();
        firstName = fn.trim();
        lastName = ln.trim();
        phoneNumber = pn.trim();
        userName = un.trim();
        password = pwd.trim();
        pointsOwned = po;
        currentAvailablePts = cap;
        reimbursementRate = rr;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPointsOwned() {
        return pointsOwned;
    }

    public void setPointsOwned(int pointsOwned) {
        this.pointsOwned = pointsOwned;
    }

    public int getCurrentAvailablePts() {
        return currentAvailablePts;
    }

    public void setCurrentAvailablePts(int currentAvailablePts) {
        this.currentAvailablePts = currentAvailablePts;
    }

    public double getReimbursementRate() {
        return reimbursementRate;
    }

    public void setReimbursementRate(double reimbursementRate) {
        this.reimbursementRate = reimbursementRate;
    }
     
     
}
