import components.collection.*;
import components.SimpleObject;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 */
public class CollectionsTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        /***************Let's get started!!!***************/
        System.out.println("\r\n***************Let's get started!!!***************");
        Collection[] colls = new Collection[15];
        colls[0] = new Collection("Winter 2014");
        colls[1] = new Collection("Spring 2014");
        colls[2] = new Collection("Summer 2014");
        colls[3] = new Collection("Fall 2014");
        colls[4] = new Collection("Winter 2013");
        colls[5] = new Collection("Spring 2013");
        colls[6] = new Collection("Summer 2013");
        colls[7] = new Collection("Fall 2013");
        colls[8] = new Collection("Winter 2012");
        colls[9] = new Collection("Spring 2012");
        colls[10] = new Collection("Summer 2012");
        colls[11] = new Collection("Fall 2012");
        colls[12] = new Collection("Winter 2014");
        colls[13] = new Collection("Spring 2013");
        colls[14] = new Collection("Summer 2012");
        
        /***************Registration***************/
        System.out.println("\r\n***************Registration***************");
        for (int i = 0; i < colls.length; i++)
            if (!colls[i].isRegistered())
                colls[i].add();
            else
                System.out.println("This collection " + colls[i].getName() + " has already been registered!!!");
        
        /***************Testing Iterator***************/
        System.out.println("\r\n***************Testing Iterator***************");
        Collections myColls = Collections.getAllRegistereds();
        Iterator it = myColls.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        /***************Deleting***************/
        System.out.println("\r\n***************Deleting***************");
        for (int i = 9; i < 15; i++)
        {
            Collection coll = new Collection(colls[i].getName());
            if (coll.isRegistered() && !coll.isDeleted())
                coll.delete();
            else 
                System.out.println("This collection " + coll.getName() + " has already been deleted!!!");
        }
        
        /***************All Registered Collections in Database***************/
        System.out.println("\r\n***************All Registered Collections in Database***************");
        Collections registeredColls = Collections.getAllRegistereds();
        SimpleObject[] collllsss = registeredColls.toArray();
        System.out.print("Total # of Registered Collections in Database: " + Collections.getTotalNumberOfRegistereds());
        for (int i = 0; i < registeredColls.size(); i++)
            System.out.println("\r\n" + collllsss[i]);
        
        /***************All Delete Collection in Database***************/
        System.out.println("\r\n***************All Delete Collection in Database***************");
        Collections deletedColls = Collections.getAllDeleteds();
        System.out.println("Total # of Deleted Collections in Database: " + Collections.getTotalNumberOfDeleteds());
        for (Collection deletedColl : deletedColls)
            System.out.println("\r\n" + deletedColl);
        
        /***************Testing Collection Objects***************/
        System.out.println("\r\n***************Testing Collection Objects***************");
        Collection coll = new Collection("Winter 2014");
        if (coll.isDeleted())
            System.out.println(coll + " is deleted!!!");
        else if (coll.isRegistered())
            System.out.println(coll + " is registered!!!");
        else
            System.out.println(coll + " has not been registered yet!!!");
        
        SimpleObject obj = coll.getNew("Winter 2015");
        if (obj.isDeleted())
            System.out.println(obj + " is deleted!!!");
        else if (obj.isRegistered())
            System.out.println(obj + " is registered!!!");
        else
            System.out.println(obj + " has not been registered yet!!!");
        
        if (coll.equals(obj))
            System.out.println(obj + " and " + coll + " are equals!!!");
        else
            System.out.println(obj + " and " + coll + " are not equals!!!");
        
        if (!obj.isRegistered())
            coll.updateName(obj.getName());
        else
            System.out.println(obj + " has already been registered!!!");
        Collections registereds = Collections.getAllRegistereds();
        System.out.println("\r\n" + registereds);
    }
}
