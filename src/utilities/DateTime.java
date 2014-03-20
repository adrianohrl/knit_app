package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 01/05/2014 01:40 pm
 */
public class DateTime 
{
    /**
     * 
     */
   private final static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   /**
    * 
    */
   private DateTime()
   { }
   
   /**
    * 
    * @param date
    * @return 
    */
   public static String formatDateTime(Date date)
   {
      String temp = "\"" + DateTime.fmt.format(date) + "\"";
      return temp;
   }
   
   /**
    * 
    * @param oldDate
    * @param newDate
    * @return 
    */
   public static boolean isValidUpdateDate(Date oldDate, Date newDate)
   {
       boolean isValid = false;
       if ((oldDate != null) && (newDate != null))
           isValid = oldDate.before(oldDate);
       return isValid;
   }
}
