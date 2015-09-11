package dao;

import Connections.RuthDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
/* import static DBConnection.getConnection;
import static DBConnection.insert;
*/
import vacationscheduler.Owner;

/* 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import mil.navy.ssp23.sail.dataobjs.UICMasterBean;
import mil.navy.ssp23.sail.dbconnections.GetDbConnection;
*/

/**
* <pre>
* Revision Notes:
*
* Programmer        Date            SPR#    Revision Description
* ----------        ------------    -----   -------------------------------------------
* Chris French      05/21/2015      41966   Error Handling
* </pre> 
*/
public class OwnersFactory {
/* 
 *   insert into owner table
*/
    public void insert(Owner own){
        Connection con = RuthDBConnection.getConnection();
        Vector inserts = new Vector();
        inserts.add(own.emailAddress);
        inserts.add(own.firstName);
        inserts.add(own.lastName);
        inserts.add(own.phoneNumber);
        inserts.add(own.userName);
        inserts.add(own.password);
        inserts.add(Integer.toString(own.pointsOwned));
        inserts.add(Integer.toString(own.currentAvailablePts));
        inserts.add(Double.toString(own.reimbursementRate));
        
        String command = "INSERT INTO OWNERS (EMAIL, FIRST_NAME, LAST_NAME,"
                +"PHONE_NUMBER, USER_NAME, PASSWORD, POINTS_OWNED, "
                +"CURRENT_POINTS, OWNER_REIMBURSEMENT_RATE) values(";
        String parameters = "";
        for (int i = 0; i< (inserts.size()-1); i++) {
            if(i < inserts.size()-3)
                parameters = parameters +"'"+inserts.get(i)+"'"+",";        //strings
            else
                parameters = parameters + inserts.get(i) + ",";             //not strings

        }
        parameters = parameters + inserts.get(inserts.size()-1);
        command = command + parameters + ")";
        System.out.println(command);
        insert(command);
    }
  /*
   *          get emails of all owners
  */
     public Vector getAllOwnerEmails(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT EMAIL FROM OWNERS");
            
            Vector output = new Vector();
            
            
            while(rs.next()){
                output.add(rs.getString("EMAIL"));
            }
            
            return output;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }   
    }
  /*
  *             get available point for all owners   
  */
         public Vector getAvailablePts(){
        Connection con = RuthDBConnection.getConnection();
        try{
            Vector avpts = new Vector();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CURRENT_POINTS FROM OWNERS");
            
            while(rs.next()){
                avpts.add(Double.toString(rs.getDouble("CURRENT_POINTS")));
                System.out.println(rs.getDouble("CURRENT_POINTS"));
            }
            
            return avpts;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
 /*
 *    Get all from owners based on email first name = email?
 */
    public static Owner get(String p_email){
        Owner ownerToReturn;
        String myQuery = "SELECT * FROM OWNERS WHERE First_Name = '"+p_email+"'";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress;
        String firstName;
        String lastName;
        String phoneNumber;
        String userName;
        String password;
        int pointsOwned;
        int currentAvailablePoints;
        double reimbursementRate;
        
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(myQuery);
            
            emailAddress = rs.getString("Email");
            firstName = rs.getString("First_Name");
            lastName = rs.getString("Last_Name");
            phoneNumber = rs.getString("Phone_Number");
            userName = rs.getString("User_Name");
            password = rs.getString("Password");
            pointsOwned = rs.getInt("Points_Owned");
            currentAvailablePoints = rs.getInt("Current_Points");
            reimbursementRate = rs.getDouble("Owner_Reimbursement_Rate");
            
            ownerToReturn = new Owner(emailAddress, firstName, lastName, phoneNumber, userName, password, pointsOwned, currentAvailablePoints, reimbursementRate);
            return ownerToReturn;


        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return new Owner();
        }
          
    }
 /*
  *              Get all ownere in a vector
 */   
        public static Vector getAllOwners(){
        Owner ownerToAdd;
        Vector ownerVectorToReturn = new Vector();
        String myQuery = "SELECT * FROM OWNERS";
        Connection con = RuthDBConnection.getConnection();
        ResultSet rs;
        Statement st;
        
        String emailAddress;
        String firstName;
        String lastName;
        String phoneNumber;
        String userName;
        String password;
        int pointsOwned;
        int currentAvailablePoints;
        double reimbursementRate;
        
        
        
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery(myQuery);

            while(rs.next()){
                
                emailAddress = rs.getString("Email");
                firstName = rs.getString("First_Name");
                lastName = rs.getString("Last_Name");
                phoneNumber = rs.getString("Phone_Number");
                userName = rs.getString("User_Name");
                password = rs.getString("Password");
                pointsOwned = rs.getInt("Points_Owned");
                currentAvailablePoints = rs.getInt("Current_Points");
                reimbursementRate = rs.getDouble("Owner_Reimbursement_Rate");

                ownerToAdd = new Owner(emailAddress, firstName, lastName, phoneNumber, userName, password, pointsOwned, currentAvailablePoints, reimbursementRate);
                ownerVectorToReturn.add(ownerToAdd);
                
            }
            return ownerVectorToReturn;


        }catch(Exception e){
            
             JOptionPane.showMessageDialog(null, "There was an error, please try again \n" + e);
             return ownerVectorToReturn;
             
        }
    }
    
         
/*         public static UICMasterBean getUICData(HttpSession mySession, String uic )
	{
                //System.out.println("DEBUG START UICMasterFactory - getUICData");
                Connection conn = null;
                UICMasterBean uicBean= new UICMasterBean();

		try
		{
                        conn= mil.navy.ssp23.sail.dbconnections.GetDbConnection.getConn(mySession);
                        PreparedStatement stmt = conn.prepareStatement(
                        "       SELECT UICMaster.UIC,                      "+
                        "              UICMaster.PseudoUICFlag,            "+
                        "              UICMaster.UICName,                  "+
                        "              UICMaster.ServicingCoast,           "+
                        "              UICMaster.RoutingIdentifier,        "+
                        "              UICMaster.RequisitionOrigin         "+
                        "         FROM UICMaster                           "+
                        "        WHERE UICMaster.UIC = ?                   ");
                        stmt.setString(1,uic);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
                        {
                            uicBean.setPseudoUICFlag(rs.getString("PseudoUICFlag"));
                            uicBean.setRequisitionOrigin(rs.getString("RequisitionOrigin"));
                            uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                            uicBean.setServicingCoast(rs.getString("ServicingCoast"));
                            uicBean.setUic(rs.getString("UIC"));
                            uicBean.setUicName(rs.getString("UICName"));
                       }// end while
                    stmt.close();
		}// end try
                catch (Exception e){
                    throw new RuntimeException(e);
                }
		finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e){
                           throw new RuntimeException(e);
                        }
                    }
                }
     		return uicBean;
   }//end getUICData
    
    */
    
    /*

   public static ArrayList<UICMasterBean> getAllUICs(HttpSession mySession )
	{
                //System.out.println("DEBUG START UICMasterFactory - getAllUICs");
                Connection conn = null;
                UICMasterBean uicBean;
                ArrayList<UICMasterBean> uicList= new ArrayList<UICMasterBean>();

		try
		{
                        conn= mil.navy.ssp23.sail.dbconnections.GetDbConnection.getConn(mySession);
                        PreparedStatement stmt = conn.prepareStatement(
                        "       SELECT UICMaster.UIC,                      "+
                        "              UICMaster.PseudoUICFlag,            "+
                        "              UICMaster.UICName,                  "+
                        "              UICMaster.ServicingCoast,           "+
                        "              UICMaster.RoutingIdentifier,        "+
                        "              UICMaster.RequisitionOrigin         "+
                        "         FROM UICMaster                           "+
                        "        WHERE RoutingIdentifier <> null           ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
                        {
                            uicBean = new UICMasterBean();
                            uicBean.setPseudoUICFlag(rs.getString("PseudoUICFlag"));
                            uicBean.setRequisitionOrigin(rs.getString("RequisitionOrigin"));
                            uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                            uicBean.setServicingCoast(rs.getString("ServicingCoast"));
                            uicBean.setUic(rs.getString("UIC"));
                            uicBean.setUicName(rs.getString("UICName"));
                            uicList.add(uicBean);
                       }// end while
                    stmt.close();
		}// end try
                catch (Exception e){
                    throw new RuntimeException(e);
                }
		finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
     		return uicList;
   }//end getAllUICs
    
    */

    /*
   public static UICMasterBean getRIData(HttpSession mySession, String ri )
	{
                //System.out.println("DEBUG START UICMasterFactory - getRIData");
                Connection conn = null;
                UICMasterBean uicBean= new UICMasterBean();

		try
		{
                        conn= mil.navy.ssp23.sail.dbconnections.GetDbConnection.getConn(mySession);
                        PreparedStatement stmt = conn.prepareStatement(
                        "       SELECT UICMaster.UIC,                      "+
                        "              UICMaster.PseudoUICFlag,            "+
                        "              UICMaster.UICName,                  "+
                        "              UICMaster.ServicingCoast,           "+
                        "              UICMaster.RoutingIdentifier,        "+
                        "              UICMaster.RequisitionOrigin         "+
                        "         FROM UICMaster                           "+
                        "        WHERE UICMaster.RoutingIdentifier = ?     ");
                        stmt.setString(1,ri);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
                        {
                            uicBean.setPseudoUICFlag(rs.getString("PseudoUICFlag"));
                            uicBean.setRequisitionOrigin(rs.getString("RequisitionOrigin"));
                            uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                            uicBean.setServicingCoast(rs.getString("ServicingCoast"));
                            uicBean.setUic(rs.getString("UIC"));
                            uicBean.setUicName(rs.getString("UICName"));
                       }// end while
                    stmt.close();
		}// end try
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return uicBean;
   }//end getRIData
    
    */
   
    /*
    public static ArrayList <UICMasterBean>  getUserRoutingList ( boolean firstOption, ArrayList <String> userRIDs, HttpSession mySession){
	        Connection conn = null;
                UICMasterBean uicBean = null;
                ArrayList <UICMasterBean> routList= new ArrayList <UICMasterBean>();
		try
		{
                    conn= GetDbConnection.getConn(mySession);
                    String statement =
                    "   SELECT UICMaster.RoutingIdentifier,                      "+
                    "          UICMaster.UIC,                                    "+
                    "          UICMaster.UICName                                 "+
                    "     FROM UICMaster                        "+
                    "    WHERE UICMaster.RoutingIdentifier in (";
                    for(int i = 1; i < userRIDs.size(); i++)
                    {
                        statement+= " ?,";
                    }
                    if(userRIDs.size() > 0) { statement += "?";}
                    else { statement += "''";}
                    statement += ")  "+
                    " ORDER BY UICMaster.RoutingIdentifier ASC  "; 

                    PreparedStatement stmt = conn.prepareStatement(statement);
                    for(int j = 0; j < userRIDs.size(); j++)
                    {
                        stmt.setString(j+1, userRIDs.get(j));
                    }
                    
                ResultSet rs = stmt.executeQuery();
                // firstOption - Provides a blank entry as the first option in a DDLB
                if (firstOption) {
                    uicBean = new UICMasterBean();
                    routList.add(uicBean);
                }
                while (rs.next()) {
                   uicBean = new UICMasterBean();
                   uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                   uicBean.setUic(rs.getString("UIC"));
                   uicBean.setUicName(rs.getString("UICName"));
                   routList.add(uicBean);
                }
                stmt.close();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return routList;
    }// end getUserRoutingList 
    
    */
    
    /*
   
    public static ArrayList<UICMasterBean> getAllUICsOrderByRI(HttpSession mySession )
	{
                //System.out.println("DEBUG START UICMasterFactory - getAllUICs");
                Connection conn = null;
                UICMasterBean uicBean;
                ArrayList<UICMasterBean> uicList= new ArrayList<UICMasterBean>();

		try
		{
                        conn= mil.navy.ssp23.sail.dbconnections.GetDbConnection.getConn(mySession);
                        PreparedStatement stmt = conn.prepareStatement(
                        "       SELECT UICMaster.UIC,                      "+
                        "              UICMaster.PseudoUICFlag,            "+
                        "              UICMaster.UICName,                  "+
                        "              UICMaster.ServicingCoast,           "+
                        "              UICMaster.RoutingIdentifier,        "+
                        "              UICMaster.RequisitionOrigin         "+
                        "         FROM UICMaster                           "+
                        "        WHERE RoutingIdentifier <> null           "+
                        "     ORDER BY UICMaster.RoutingIdentifier ASC     ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
                        {
                            uicBean = new UICMasterBean();
                            uicBean.setPseudoUICFlag(rs.getString("PseudoUICFlag"));
                            uicBean.setRequisitionOrigin(rs.getString("RequisitionOrigin"));
                            uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                            uicBean.setServicingCoast(rs.getString("ServicingCoast"));
                            uicBean.setUic(rs.getString("UIC"));
                            uicBean.setUicName(rs.getString("UICName"));
                            uicList.add(uicBean);
                       }// end while
                    stmt.close();
		}// end try
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
     		return uicList;
   }//end getAllUICsOrderByRI 
    
    */
    
    /*
    
    public static ArrayList<UICMasterBean>  getUserRIDs (Integer userNum, HttpSession mySession){
 	        Connection conn = null;
                UICMasterBean  uicBean = new UICMasterBean();
                ArrayList<UICMasterBean> uicBeans = new ArrayList<UICMasterBean>();
 		try
 		{
                     conn= GetDbConnection.getConn(mySession);
                     PreparedStatement stmt = conn.prepareStatement(
                    
                    "  SELECT UICMaster.RoutingIdentifier,          "+ 
                    "              UICMaster.UIC,                      "+
                    "              UICMaster.PseudoUICFlag,            "+
                    "              UICMaster.UICName,                  "+
                    "              UICMaster.ServicingCoast,           "+
                    "              UICMaster.RequisitionOrigin         "+
                    "    FROM SSUPUserRID,                           "+
                    "          UICMaster                           "+
                    " WHERE SSUPUserRID.SAILUserNum = ?             "+
                    "   AND SSUPUserRID.RoutingIdentifier = UICMaster.RoutingIdentifier ");
                    
                stmt.setInt(1, userNum);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                   uicBean = new UICMasterBean();
                            uicBean.setPseudoUICFlag(rs.getString("PseudoUICFlag"));
                            uicBean.setRequisitionOrigin(rs.getString("RequisitionOrigin"));
                            uicBean.setRoutingIdentifier(rs.getString("RoutingIdentifier"));
                            uicBean.setServicingCoast(rs.getString("ServicingCoast"));
                            uicBean.setUic(rs.getString("UIC"));
                            uicBean.setUicName(rs.getString("UICName"));
                            uicBeans.add(uicBean);
                  }
                stmt.close();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return uicBeans;
         
    }// end getUsersRID
    
    */

    private void insert(String command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}// end class UICMasterFactory

