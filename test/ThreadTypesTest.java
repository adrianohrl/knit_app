import components.thread_type.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 08:23pm
 * @version 1.0.0001
 */
public class ThreadTypesTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        ThreadType[] thrds = new ThreadType[5];
        thrds[0] = new ThreadType("Cotton", 41.29, "Este é o algodão.", 1.5);
        thrds[1] = new ThreadType("Viscolycra", 54.50, "Esse é chamado viscolaicra mesmo.", 5.3);
        thrds[2] = new ThreadType("Wool", 35.99, "Traduzindo esse temos a lã.", 6.6);
        thrds[3] = new ThreadType("Vistrech", 24.90, "O povo chama esse de vistrech mesmo.", 3.7);
        thrds[4] = new ThreadType("Mousse", 31.99, "Esse é conhecido como muci.", .2);
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < thrds.length; i++)
            if (!thrds[i].isRegistered())
                thrds[i].register();
            else
                System.out.println("This thrdection " + thrds[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        ThreadTypes myThdrs = ThreadTypes.getAllRegistereds();
        Iterator<ThreadType> it = myThdrs.iterator();
        while (it.hasNext())
            System.out.println("Obs.:" + it.next().getObs());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = thrds.length - 1; i < thrds.length; i++)
        {
            ThreadType thrd = new ThreadType(thrds[i].getName());
            if (thrd.isRegistered() && !thrd.isDeleted())
                thrd.delete();
            else 
                System.out.println("This thread type " + thrd.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered ThreadTypes in Database***************/
        System.out.println("\r\n***************All Registered ThreadTypes in Database***************");
        ThreadTypes registeredColls = ThreadTypes.getAllRegistereds();
        SimpleObject[] thrdllsss = registeredColls.toArray();
        System.out.print("Total # of Registered ThreadTypes in Database: " + ThreadTypes.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + thrdllsss[i]);
        
        /***************All Delete ThreadType in Database***************/
        System.out.println("\r\n***************All Delete ThreadType in Database***************");
        ThreadTypes deletedColls = ThreadTypes.getAllDeleteds();
        System.out.println("Total # of Deleted ThreadTypes in Database: " + ThreadTypes.getTotalNumberOfDeleteds());
        for (ThreadType deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing ThreadType Objects***************/
        System.out.println("\r\n***************Testing ThreadType Objects***************");
        ThreadType thrd = new ThreadType("Buclê", 35.50, "Cheio de nódulos.", .01);
        if (thrd.isDeleted())
            System.out.println(thrd + " is deleted!!!");
        else if (thrd.isRegistered())
            System.out.println(thrd + " is registered!!!");
        else
            System.out.println(thrd + " has not been registered yet!!!");
        
        SimpleObject obj = thrd.getNew("Mousse");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        ((ThreadType) obj).updateObs("Esse é caro");
        ((ThreadType) obj).updateCost(49.85);
        
        if (thrd.equals(obj))
            System.out.println(obj + " and " + thrd + " are equals!!!");
        else
            System.out.println(obj + " and " + thrd + " are not equals!!!");
        
        if (!obj.isRegistered())
            thrd.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        ThreadTypes registereds = ThreadTypes.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
