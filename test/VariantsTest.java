import components.variant.*;
import components.SimpleObject;
import java.util.Iterator;


/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 08:23pm
 */
public class VariantsTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Variant[] varts = new Variant[9];
        varts[0] = new Variant("Dark");
        varts[1] = new Variant("Deep Forest");
        varts[2] = new Variant("Blue Ocean");
        varts[3] = new Variant("Colorful");
        varts[4] = new Variant("Sadness");
        varts[5] = new Variant("Happiness");
        varts[6] = new Variant("Rainbow");
        varts[7] = new Variant("Sunny");
        varts[8] = new Variant("Rainy");
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < varts.length; i++)
            if (!varts[i].isRegistered())
                varts[i].add();
            else
                System.out.println("This variant " + varts[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Variants myColls = Variants.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = varts.length - 1; i < varts.length; i++)
        {
            Variant vart = new Variant(varts[i].getName());
            if (vart.isRegistered() && !vart.isDeleted())
                vart.delete();
            else 
                System.out.println("This vartection " + vart.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Variants in Database***************/
        System.out.println("\r\n***************All Registered Variants in Database***************");
        Variants registeredColls = Variants.getAllRegistereds();
        SimpleObject[] vartllsss = registeredColls.toArray();
        System.out.print("Total # of Registered Variants in Database: " + Variants.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + vartllsss[i]);
        
        /***************All Delete Variant in Database***************/
        System.out.println("\r\n***************All Delete Variant in Database***************");
        Variants deletedColls = Variants.getAllDeleteds();
        System.out.println("Total # of Deleted Variants in Database: " + Variants.getTotalNumberOfDeleteds());
        for (Variant deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Variant Objects***************/
        System.out.println("\r\n***************Testing Variant Objects***************");
        Variant vart = new Variant("Rainy");
        if (vart.isDeleted())
            System.out.println(vart + " is deleted!!!");
        else if (vart.isRegistered())
            System.out.println(vart + " is registered!!!");
        else
            System.out.println(vart + " has not been registered yet!!!");
        
        SimpleObject obj = vart.getNew("Light");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        
        if (vart.equals(obj))
            System.out.println(obj + " and " + vart + " are equals!!!");
        else
            System.out.println(obj + " and " + vart + " are not equals!!!");
        
        if (!obj.isRegistered())
            vart.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Variants registereds = Variants.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
