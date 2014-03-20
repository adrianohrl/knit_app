package utilities;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/30/2013 09:35 am
 */
public class Currency
{
   private final static NumberFormat fmt = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
   
   /**
    * 
    * @param val
    * @return 
    */
   public static String formatCurrency(double val)
   {
      String temp = "R$" + Currency.fmt.format(val);
      return temp;
   }
}