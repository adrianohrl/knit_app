import components.size.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 08:22pm
 */
public class SizesTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Size[] sizes = new Size[8];
        sizes[0] = new Size("XXS");
        sizes[1] = new Size(SizeLabels.XS);
        sizes[2] = new Size("S");
        sizes[3] = new Size("M");
        sizes[4] = new Size(SizeLabels.L);
        sizes[5] = new Size("XL");
        sizes[6] = new Size("XXL");
        sizes[7] = new Size(SizeLabels.U);
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < sizes.length; i++)
            if (!sizes[i].isRegistered())
                sizes[i].add();
            else
                System.out.println("This size " + sizes[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Sizes myColls = Sizes.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = sizes.length - 1; i < sizes.length; i++)
        {
            Size size = new Size(sizes[i].getName());
            if (size.isRegistered() && !size.isDeleted())
                size.delete();
            else 
                System.out.println("This sizeection " + size.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Sizes in Database***************/
        System.out.println("\r\n***************All Registered Sizes in Database***************");
        Sizes registeredColls = Sizes.getAllRegistereds();
        SimpleObject[] sizellsss = registeredColls.toArray();
        System.out.print("Total # of Registered Sizes in Database: " + Sizes.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + sizellsss[i]);
        
        /***************All Delete Size in Database***************/
        System.out.println("\r\n***************All Delete Size in Database***************");
        Sizes deletedColls = Sizes.getAllDeleteds();
        System.out.println("Total # of Deleted Sizes in Database: " + Sizes.getTotalNumberOfDeleteds());
        for (Size deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Size Objects***************/
        System.out.println("\r\n***************Testing Size Objects***************");
        Size size = new Size("U");
        if (size.isDeleted())
            System.out.println(size + " is deleted!!!");
        else if (size.isRegistered())
            System.out.println(size + " is registered!!!");
        else
            System.out.println(size + " has not been registered yet!!!");
        
        SimpleObject obj = size.getNew("XXXL");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        
        if (size.equals(obj))
            System.out.println(obj + " and " + size + " are equals!!!");
        else
            System.out.println(obj + " and " + size + " are not equals!!!");
        
        if (!obj.isRegistered())
            size.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Sizes registereds = Sizes.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
