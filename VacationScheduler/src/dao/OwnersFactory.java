package dao;

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
    public static UICMasterBean getUICData(HttpSession mySession, String uic )
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
    
}// end class UICMasterFactory

