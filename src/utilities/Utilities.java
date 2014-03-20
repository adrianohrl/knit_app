package utilities;

import components.model.Percentages;
import java.util.Date;
import java.util.Comparator;
import java.math.BigInteger;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 * @param <T>
 */
public class Utilities<T>
{        
    /**
     * 
     * @param date
     * @return 
     */
    public static boolean isValidDate(String date)
    {
        String[] parts = date.split("/");
        if (parts.length == 3 && parts[0].length() == 2 &&
            parts[1].length() == 2 && parts[2].length() == 2)
        {
            int year, month, day;
            try
            {
                year = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
            }
            catch (NumberFormatException e)
            {
                return false;
            }
            int latestDay = 28;
            if (year >= 0 && year <= 99)
            {
                switch (month)
                {
                    case 2:
                        if (year % 4 == 0)
                            latestDay = 29;
                        break;
                    case 4: case 6: case 9: case 11:
                        latestDay = 30;
                        break;
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                        latestDay = 31;
                        break;
                    default:
                        return false;
                }
                if (day >= 1 && day <= latestDay)
                    return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param date
     * @return 
     */
    public static boolean isValidDate(Date date)
    {
        String str = date.toString();
        return Utilities.isValidDate(str);
    }
    
    /**
     * 
     * @param percs
     * @return 
     */
    public static boolean areValidPercentages(Percentages percs)
    {
        return true;
    }
    
    /**
    * Sorts the specified array of T objects and the specified Comparator of T using the selection
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
     * @param <T>
     * @param sort
     * @param comp
    */
    public static <T> void selectionSort(T[] sort, Comparator<T> comp)
    {
        if (sort != null)
            selectionSort(sort, comp, sort.length);
    }
    
    /**
    * Sorts the specified array of T objects and the specified Comparator of T using the selection
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
     * @param <T>
     * @param sort
     * @param comp
     * @param n
    */
    public static <T> void selectionSort (T[] sort, Comparator<T> comp, int n)
    {
        //should check n to make sure that it doesn't exceed the array parameters
        int min;
        T temp;
        
        if (n > sort.length || n <= 0)
            n = sort.length;
        
        for (int index = 0; index < n - 1; index++)
        {
            min = index;
            for (int scan = index+1; scan < n; scan++)
                if (comp.compare(sort[scan], sort[min]) < 0)
                    min = scan;
            
            // Swap the values
            temp = sort[min];
            sort[min] = sort[index];
            sort[index] = temp;
        }
    }
    
    
    /**
    * Sorts the specified array of T objects and the specified Comparator of T using the insertion
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
     * @param <T>
     * @param sort
     * @param comp
    */
    public static <T> void insertionSort(T[] sort, Comparator<T> comp)
    {
        if (sort != null)
            insertionSort(sort, comp, sort.length);
    }
    
    /**
    * Sorts the specified array of T objects and the specified Comparator of T using the insertion
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
     * @param <T>
     * @param sort
     * @param comp
     * @param n
    */
    public static <T> void insertionSort (T[] sort, Comparator<T> comp, int n)
    {
        T temp;
        int position;
        
        if (n > sort.length || n <= 0)
            n = sort.length;
        
        for (int index = 1; index < n; index++)
        {
            temp = sort[index];
            position = index;
            
            // shift larger values to the right
            while (position > 0 && comp.compare(sort[position - 1], temp) > 0)
            {
                sort[position] = sort[position - 1];
                position--;
            }
            
            sort[position] = temp;
        }
    }
    
    /**
     * 
     * @param password
     * @return 
     */
    public static String getMD5(String password)
    {  
        String md5hash = null;  
        MessageDigest md = null;  
        try 
        {  
            md = MessageDigest.getInstance("MD5");  
        } 
        catch (NoSuchAlgorithmException e) 
        {  
            e.printStackTrace();  
        }  
        if (md != null)
        {
            md.update(password.getBytes(), 0, password.length());
            BigInteger bi = new BigInteger(1, md.digest());  
            md5hash = String.format("%1$032x", bi);         
        }
        return md5hash;  
    }  
    
    /**
     * 
     * @param email
     * @return 
     */
    public static boolean isValidEmail(String email)  
    {  
        boolean isValid = false;  
        if (email != null && email.length() > 0) 
        {  
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
            Matcher matcher = pattern.matcher(email);  
            if (matcher.matches())
                isValid = true;  
        }  
        return isValid;  
    }
    
    public static boolean isValidPassword(String password)
    {
        boolean isValid = false;
        if (password.length() >= 8 && password.length() <= 31)
            isValid = true;
        return isValid;
    }
}
