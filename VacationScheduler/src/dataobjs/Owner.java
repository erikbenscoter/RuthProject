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
}
