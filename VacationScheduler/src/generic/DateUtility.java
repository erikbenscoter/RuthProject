/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.ssp23.sail.generic;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author donald.campagna
 */
public class DateUtility {

    /***************************************************************/
/*          Get the current time                         */
/***************************************************************/
public static String getTimeNow() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=
    new SimpleDateFormat("HH:mm:ss");
  String timeNow = formatter.format(currentDate.getTime());
  return timeNow;
  }// end getDateTimeNow

/***************************************************************/
/*          Get the current date  time                         */
/***************************************************************/
public static String getDateTimeNow() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=
    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  String dateTimeNow = formatter.format(currentDate.getTime());
 // System.out.println("Now the date time is :=>  " + dateTimeNow);
  return dateTimeNow;
  }// end getDateTimeNow



/***************************************************************/
/*          Get the current date  time                         */
/***************************************************************/
public static String getDateTimeNow2() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=
    new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
  String dateTimeNow = formatter.format(currentDate.getTime());
 // System.out.println("Now the date time is :=>  " + dateTimeNow);
  return dateTimeNow;
  }// end getDateTimeNow

/***************************************************************/
/*          Get the current date                               */
/***************************************************************/

public static String  getDateNow() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd");
  String dateNow = formatter.format(currentDate.getTime());
  return (dateNow);
  }// end getDateNow

/***************************************************************/
/*          Get the current date MM/dd/yyyy format             */
/***************************************************************/

public static String  getDate() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=  new SimpleDateFormat("MM/dd/yyyy");
  String dateNow = formatter.format(currentDate.getTime());
  return (dateNow);
  }// end getDateNow

/***************************************************************/
/*          Get the current date MM/dd/yyyy format             */
/***************************************************************/

public static String  getDateyyyymmdd() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=  new SimpleDateFormat("yyyy-MM-dd");
  String dateNow = formatter.format(currentDate.getTime());
  return (dateNow);
  }// end getDateNow

/***************************************************************/
/*          Get the current date MM.dd.yyyy format             */
/***************************************************************/

public static String  getDateDot() {
  Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MM.dd");
  String dateNow = formatter.format(currentDate.getTime());
  return (dateNow);
  }// end getDateNow

/***************************************************************/
/*          Get the number of days between two dates           */
/***************************************************************/

  public static int  getDateDiff(Calendar c1, Calendar c2) {
    int diffDay = diff(c1, c2);
    //System.out.println("Different Day : " + diffDay);
    return diffDay;
  }

private static int diff(Calendar c1, Calendar c2) {
    int diffDay = 0;

    if (c1.before(c2)) {
      diffDay = countDiffDay(c1, c2);
     }else{
      diffDay = countDiffDay(c2, c1);}
      return diffDay;
}
 /*      Get the number of days between two dates          */

  private static int countDiffDay(Calendar c1, Calendar c2) {
    int returnInt = 0;
    while (!c1.after(c2)) {
      c1.add(Calendar.DAY_OF_MONTH, 1);
      returnInt++;}
    if (returnInt > 0) {
      returnInt = returnInt - 1; }
    return (returnInt);
  }
 /*      End get the number of days between two dates          */

/***************************************************************/
/*          Get the current java.sql.Date                      */
/*  java.sql.Date descends from java.util.Date, but uses only  */
/*  the year, month and day values.                            */
/***************************************************************/
  public static java.sql.Date getCurrentJavaSqlDate(){
    Calendar currentDate = Calendar.getInstance();
    java.sql.Date jsdate = new java.sql.Date(currentDate.getTime().getTime());
    return jsdate;
}


  public static java.sql.Timestamp getCurrentJavaSqlTimestamp() {
    java.util.Date date = new java.util.Date();
    return new java.sql.Timestamp(date.getTime());
  }


/***************************************************************/
/*          Get YYYYMM for Current Date + or -  #days          */
/*          ex: days param = +61 or -61                        */
/***************************************************************/
public static String getDateRanger(Integer days) {
       Calendar calcDate = Calendar.getInstance();
       calcDate.add(Calendar.DATE, days);
       SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MM");
       String pDate = formatter.format(calcDate.getTime());
       return pDate;
  }// end getDateRanger

/***************************************************************/
/*          Get MM/dd/yyyy for Current Date + or -  #days          */
/*          ex: days param = +61 or -61                        */
/***************************************************************/
public static String getDateRangerDay(Integer days) {
       Calendar calcDate = Calendar.getInstance();
       calcDate.add(Calendar.DATE, days);
       SimpleDateFormat formatter=  new SimpleDateFormat("MM/dd/yyyy");
       String pDate = formatter.format(calcDate.getTime());
       return pDate;
  }// end getDateRangerDay

/***************************************************************/
/*          Get YYYY.MM.DD for Current Date + or -  #days          */
/*          ex: days param = +61 or -61                        */
/***************************************************************/
public static String getDateRangerDot(Integer days) {
       Calendar calcDate = Calendar.getInstance();
       calcDate.add(Calendar.DATE, days);
       SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MM.dd");
       String pDate = formatter.format(calcDate.getTime());
       return pDate;
  }// end getDateRangerDot

/***************************************************************/
/*          Get Current Year YYYY                              */
/***************************************************************/
public static Integer getCurrentYear() {
       java.util.Calendar calendar = java.util.Calendar.getInstance();
       int yr = calendar.get(java.util.Calendar.YEAR);
       return yr;
}

/***************************************************************/
/*          Get Current Day Of The Year JJJ                    */
/***************************************************************/

public static Integer getDayOfYear() {
       java.util.Calendar calendar = java.util.Calendar.getInstance();
       int jul = calendar.get(java.util.Calendar.DAY_OF_YEAR);
       return jul;
}

/***************************************************************/
/*          Get Julian Date                                    */
/***************************************************************/

public static String getJulianDay(String date) {
       java.util.Calendar calendar = java.util.Calendar.getInstance();
       calendar.set(Integer.parseInt(date.substring(6)), 
                    Integer.parseInt(date.substring(0, 2)) - 1, Integer.parseInt(date.substring(3, 5)));
       Integer jul = calendar.get(java.util.Calendar.DAY_OF_YEAR);
       return jul.toString();
}

/***************************************************************/
/*          Add a number of Days to a date                     */
/***************************************************************/

  public static String addDays(int year, int month, int day, int days) {
        GregorianCalendar  curCal = new GregorianCalendar();
        curCal.set(year, month - 1, day);
        curCal.add(Calendar.DAY_OF_YEAR, days);
        return curCal.get(Calendar.YEAR) + "." + (curCal.get(Calendar.MONTH) + 1)+ "." + curCal.get(Calendar.DAY_OF_MONTH);
  }

  /***************************************************************/
/*          Validate String Parameter is a Valid Date          */
/***************************************************************/

  public static String addMonths(int year, int month, int day, int months) {
        GregorianCalendar  curCal = new GregorianCalendar();
        curCal.set(year, month - 1 , day);
        curCal.add(Calendar.MONTH, months);
        return curCal.get(Calendar.YEAR) + "." + (curCal.get(Calendar.MONTH) + 1)+ "." + curCal.get(Calendar.DAY_OF_MONTH);
  }

  /***************************************************************/
/*          Validate String Parameter is a Valid Date          */
/***************************************************************/

  public static boolean isValidDateLenient(String inDate) {

    if (inDate == null)
      return false;

    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    try {
        //parse the inDate parameter
        dateFormat.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat2.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}


    return false;
  }

/***************************************************************/
/*          Validate String Parameter is a Valid Date          */
/***************************************************************/

  public static boolean isValidDate(String inDate) {

    if (inDate == null)
      return false;

    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("M/dd/yyyy");
    SimpleDateFormat dateFormat3 = new SimpleDateFormat("MM/d/yyyy");
    SimpleDateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateFormat5 = new SimpleDateFormat("yyyy-M-dd");
    SimpleDateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-d");
    dateFormat.setLenient(false);
    dateFormat2.setLenient(false);
    dateFormat3.setLenient(false);
    dateFormat4.setLenient(false);
    dateFormat5.setLenient(false);
    dateFormat6.setLenient(false);

    try {
        //parse the inDate parameter
        dateFormat.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat2.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat3.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat4.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat5.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}
    try {
        //parse the inDate parameter
        dateFormat6.parse(inDate.trim());
        return true;
    }
    catch (ParseException pe) {}

    return false;
  }

  public static boolean isPostDated(String date) {

            int iPos1 = -1;
            int iPos2 = -1;
            String sMonth;
            String sDay;
            String sYear;
            String sDate;

           if (date != null && !date.trim().equals("")) {
               iPos1 = date.indexOf("/");

               if (iPos1 != -1) {
                   iPos2  = date.indexOf("/", iPos1 + 1);
               }

               if (iPos1 == -1 || iPos2 == -1) {
                   return false;
               }
               else {
                   // insure date is in mm/dd/yyyy format
                   sMonth = date.substring(0, iPos1);
                   sDay   = date.substring(iPos1 + 1, iPos2);

                   sYear  = date.substring(iPos2 + 1);

                   if (sMonth.length() == 1) sMonth = "0"  + sMonth;
                   if (sDay.length()   == 1) sDay   = "0"  + sDay;
                   if (sYear.length()  == 2) sYear  = "20" + sYear;

                   sDate = sMonth + "/" + sDay + "/" + sYear;
                   date = sDate;
               }

               if (isValidDate(date)) {
                   int intYear = Integer.parseInt(sYear);
                   int intMon  = Integer.parseInt(sMonth);
                   int intDay  = Integer.parseInt(sDay);
                   Calendar c1 = Calendar.getInstance();
                   Calendar c2 = Calendar.getInstance();

                   c1.set(intYear, intMon, intDay, 0, 0, 0);

                   String dateNow = getDateTimeNow();

                   sYear  = dateNow.substring(0,4);
                   sMonth = dateNow.substring(5,7);
                   sDay   = dateNow.substring(8,10);
                   intYear = Integer.parseInt(sYear);
                   intMon  = Integer.parseInt(sMonth);
                   intDay  = Integer.parseInt(sDay);

                   c2.set(intYear, intMon, intDay, 0, 0, 0);

                   if (c2.before(c1)) {
                      return false;
                   }
                   return true;

               }
               else {
                   return false;
               }
           }
           else
            {
              return false;
            }

  }
  
  public static boolean isPostDatedNew(String date) {

      if (!isValidDate(date)) {
          return false;
      }
      
      Calendar calIn = Calendar.getInstance();
      Calendar calNow = Calendar.getInstance();
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
      Date dateIn = new Date();
      Date dateNow = new Date();
      
      try {
        dateIn = format.parse(date);
        dateNow = format.parse(getDate());
      }
      catch (Exception e) {
          return false;
      }

      calIn.setTime(dateIn);
      calNow.setTime(dateNow);

      if (calNow.after(calIn) || calNow.equals(calIn)) {
          return true;
      }
      
      return false;

  }

}//end DateUtility
