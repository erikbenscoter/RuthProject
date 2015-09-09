/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataobjs;

/**
 *
 * @author donald.campagna
 */
public class OwnersBean {

       private String Email;
       private String First_Name;
       private String Last_Name;
       private String Phone_Number;
       private String User_Name;
       private String Password;
       private String Points_Owned;
       private String Current_Points;
       private String Owner_Reimbursement_Rate;
       
    public OwnersBean()
    {
       Email                    = "";
       First_Name               = "";
       Last_Name                = "";
       Phone_Number             = "";
       User_Name                = "";
       Password                 = "";
       Points_Owned             = "";
       Current_Points           = "";
       Owner_Reimbursement_Rate = "";
    }    
       
       

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String First_Name) {
        this.First_Name = First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String Phone_Number) {
        this.Phone_Number = Phone_Number;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPoints_Owned() {
        return Points_Owned;
    }

    public void setPoints_Owned(String Points_Owned) {
        this.Points_Owned = Points_Owned;
    }

    public String getCurrent_Points() {
        return Current_Points;
    }

    public void setCurrent_Points(String Current_Points) {
        this.Current_Points = Current_Points;
    }

    public String getOwner_Reimbursement_Rate() {
        return Owner_Reimbursement_Rate;
    }

    public void setOwner_Reimbursement_Rate(String Owner_Reimbursement_Rate) {
        this.Owner_Reimbursement_Rate = Owner_Reimbursement_Rate;
    }
 
  
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof OwnersBean)) return false;
        OwnersBean a = (OwnersBean) obj;
        return (Email == a.Email || (Email!=null && Email.equals(a.Email))) 
                && (First_Name == a.First_Name || (First_Name!=null && First_Name.equals(a.First_Name)))
                && (Last_Name == a.Last_Name || (Last_Name!=null && Last_Name.equals(a.Last_Name)))
                && (Phone_Number == a.Phone_Number || (Phone_Number!=null && Phone_Number.equals(a.Phone_Number)))
                && (User_Name == a.User_Name || (User_Name!=null && User_Name.equals(a.User_Name)))
                && (Password == a.Password || (Password!=null && Password.equals(a.Password)))
                && (Points_Owned == a.Points_Owned || (Points_Owned!=null && Points_Owned.equals(a.Points_Owned)))
                && (Current_Points == a.Current_Points || (Current_Points!=null && Current_Points.equals(a.Current_Points)))
                && (Owner_Reimbursement_Rate == a.Owner_Reimbursement_Rate || (Owner_Reimbursement_Rate!=null && Owner_Reimbursement_Rate.equals(a.Owner_Reimbursement_Rate))) ;
    }
    
}// class
