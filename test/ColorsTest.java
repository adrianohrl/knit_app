import components.color.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 07:41pm
 */
public class ColorsTest 
{
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Color[] colors = new Color[10];
        colors[0] = new Color("Black");
        colors[1] = new Color("White");
        colors[2] = new Color("Yellow");
        colors[3] = new Color("Red");
        colors[4] = new Color("Purple");
        colors[5] = new Color("Gray");
        colors[6] = new Color("Green");
        colors[7] = new Color("Blue");
        colors[8] = new Color("Pink");
        colors[9] = new Color("Brown");
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < colors.length; i++)
            if (!colors[i].isRegistered())
                colors[i].add();
            else
                System.out.println("This color " + colors[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Colors myColls = Colors.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = 8; i < 10; i++)
        {
            Color color = new Color(colors[i].getName());
            if (color.isRegistered() && !color.isDeleted())
                color.delete();
            else 
                System.out.println("This colorection " + color.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Colors in Database***************/
        System.out.println("\r\n***************All Registered Colors in Database***************");
        Colors registeredColls = Colors.getAllRegistereds();
        SimpleObject[] colorllsss = registeredColls.toArray();
        System.out.print("Total # of Registered Colors in Database: " + Colors.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + colorllsss[i]);
        
        /***************All Delete Color in Database***************/
        System.out.println("\r\n***************All Delete Color in Database***************");
        Colors deletedColls = Colors.getAllDeleteds();
        System.out.println("Total # of Deleted Colors in Database: " + Colors.getTotalNumberOfDeleteds());
        for (Color deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Color Objects***************/
        System.out.println("\r\n***************Testing Color Objects***************");
        Color color = new Color("Black");
        if (color.isDeleted())
            System.out.println(color + " is deleted!!!");
        else if (color.isRegistered())
            System.out.println(color + " is registered!!!");
        else
            System.out.println(color + " has not been registered yet!!!");
        
        SimpleObject obj = color.getNew("Brown");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        
        if (color.equals(obj))
            System.out.println(obj + " and " + color + " are equals!!!");
        else
            System.out.println(obj + " and " + color + " are not equals!!!");
        
        if (!obj.isRegistered())
            color.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Colors registereds = Colors.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
