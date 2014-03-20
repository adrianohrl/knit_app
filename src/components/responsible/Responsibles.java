package components.responsible;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Responsibles extends SimpleObjects<Responsible>
{
    public Responsibles()
    {
        super();
    }
    
    public Responsibles(ArrayList<Responsible> objects)
    {
        super(objects);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Responsibles";
    }
    
    /**
     *
     * @return
     */
    public static Responsibles getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Responsible> objs = new ArrayList<Responsible>();
        for (long id : ids)
            objs.add(new Responsible(id));
        return new Responsibles(objs);
    }
    
    /**
     *
     * @return
     */
    public static long getTotalNumberOfRegistereds()
    {
        String sql = "SELECT COUNT(*) FROM " + getClassName() + " WHERE isDeleted = false";
        long total = Database.getLongElement(sql);
        return total;
    }
    
    /**
     *
     * @return
     */
    public static Responsibles getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Responsible> objs = new ArrayList<Responsible>();
        for (long id : ids)
            objs.add(new Responsible(id));
        return new Responsibles(objs);
    }
    
    /**
     *
     * @return
     */
    public static long getTotalNumberOfDeleteds()
    {
        String sql = "SELECT COUNT(*) FROM " + getClassName() + " WHERE isDeleted = true";
        long total = Database.getLongElement(sql);
        return total;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str = getClassName() + ":";
        for (Responsible obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
