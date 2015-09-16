/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.ssp23.sail.generic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author scott.manship
 */
public class DateFormatUtility {
    
    public static String formatDate(String sDate){
        if(sDate != null && !"".equals(sDate)){
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
                try{
                    date = formatter.parse(sDate);
                    sDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
                }catch(ParseException pe){pe.printStackTrace();}
        }  
        return sDate;
    }
    public static String trimDate(String sDate){
        if(sDate != null && !"".equals(sDate)){
            sDate = sDate.substring(0, 10);
            if(sDate.contains("-")){
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                    try{
                        date = formatter.parse(sDate);
                        sDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
                    }catch(ParseException pe){pe.printStackTrace();}
            }
            return sDate;
        }else{
            return "";
        }  
    }
    
}