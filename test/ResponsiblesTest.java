import components.responsible.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 08:20pm
 */
public class ResponsiblesTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Responsible[] resps = new Responsible[4];
        resps[0] = new Responsible("Adriano Herique Rossette Leite");
        resps[1] = new Responsible("Marcos Adriano Leite");
        resps[2] = new Responsible("Adriano Canela");
        resps[3] = new Responsible("Paulo Lourenço Menicucci Bartholomei");
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < resps.length; i++)
            if (!resps[i].isRegistered())
                resps[i].add();
            else
                System.out.println("This responsible " + resps[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Responsibles myColls = Responsibles.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = resps.length - 1; i < resps.length; i++)
        {
            Responsible resp = new Responsible(resps[i].getName());
            if (resp.isRegistered() && !resp.isDeleted())
                resp.delete();
            else 
                System.out.println("This respection " + resp.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Responsibles in Database***************/
        System.out.println("\r\n***************All Registered Responsibles in Database***************");
        Responsibles registeredColls = Responsibles.getAllRegistereds();
        SimpleObject[] respllsss = registeredColls.toArray();
        System.out.print("Total # of Registered Responsibles in Database: " + Responsibles.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + respllsss[i]);
        
        /***************All Delete Responsible in Database***************/
        System.out.println("\r\n***************All Delete Responsible in Database***************");
        Responsibles deletedColls = Responsibles.getAllDeleteds();
        System.out.println("Total # of Deleted Responsibles in Database: " + Responsibles.getTotalNumberOfDeleteds());
        for (Responsible deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Responsible Objects***************/
        System.out.println("\r\n***************Testing Responsible Objects***************");
        Responsible resp = new Responsible("Adriano Henrique Rossette Leite");
        if (resp.isDeleted())
            System.out.println(resp + " is deleted!!!");
        else if (resp.isRegistered())
            System.out.println(resp + " is registered!!!");
        else
            System.out.println(resp + " has not been registered yet!!!");
        
        SimpleObject obj = resp.getNew("Eunice Rossette Tavares Leite");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        
        if (resp.equals(obj))
            System.out.println(obj + " and " + resp + " are equals!!!");
        else
            System.out.println(obj + " and " + resp + " are not equals!!!");
        
        if (!obj.isRegistered())
            resp.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Responsibles registereds = Responsibles.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }    
}
