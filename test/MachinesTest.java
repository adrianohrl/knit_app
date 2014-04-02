import components.machine.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/03/2014 08:20pm
 * @version 1.0.0001
 */
public class MachinesTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Machine[] machs = new Machine[12];
        machs[0] = new Machine("Stoll 1", 24.49);
        machs[1] = new Machine("Stoll 2", 26.49);
        machs[2] = new Machine("Stoll 3", 20.90);
        machs[3] = new Machine("Stoll 4", 19.90);
        machs[4] = new Machine("PS 1", 17.49);
        machs[5] = new Machine("PS 2", 16.90);
        machs[6] = new Machine("PS 3", 16.49);
        machs[7] = new Machine("PS 4", 17.90);
        machs[8] = new Machine("Coppo 1", 12.90);
        machs[9] = new Machine("Coppo 2", 12.49);
        machs[10] = new Machine("Coppo 3", 13.90);
        machs[11] = new Machine("Coppo 4", 13.49);
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < machs.length; i++)
            if (!machs[i].isRegistered())
                machs[i].register();
            else
                System.out.println("This machine " + machs[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Machines myColls = Machines.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = machs.length - 2; i < machs.length; i++)
        {
            Machine mach = new Machine(machs[i].getName());
            if (mach.isRegistered() && !mach.isDeleted())
                mach.delete();
            else 
                System.out.println("This machection " + mach.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Machines in Database***************/
        System.out.println("\r\n***************All Registered Machines in Database***************");
        Machines registeredColls = Machines.getAllRegistereds();
        SimpleObject[] machllsss = registeredColls.toArray();
        System.out.print("Total # of Registered Machines in Database: " + Machines.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + machllsss[i]);
        
        /***************All Delete Machine in Database***************/
        System.out.println("\r\n***************All Delete Machine in Database***************");
        Machines deletedColls = Machines.getAllDeleteds();
        System.out.println("Total # of Deleted Machines in Database: " + Machines.getTotalNumberOfDeleteds());
        for (Machine deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Machine Objects***************/
        System.out.println("\r\n***************Testing Machine Objects***************");
        Machine mach = new Machine("Coppo 5", 12.49);
        if (mach.isDeleted())
            System.out.println(mach + " is deleted!!!");
        else if (mach.isRegistered())
            System.out.println(mach + " is registered!!!");
        else
            System.out.println(mach + " has not been registered yet!!!");
        mach.updateCost(14.49);
        
        SimpleObject obj = mach.getNew("Coppo 4");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        ((Machine) obj).updateCost(11.49);
        
        
        if (mach.equals(obj))
            System.out.println(obj + " and " + mach + " are equals!!!");
        else
            System.out.println(obj + " and " + mach + " are not equals!!!");
        
        if (!obj.isRegistered())
            mach.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Machines registereds = Machines.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
