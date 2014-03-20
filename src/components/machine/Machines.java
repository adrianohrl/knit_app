package components.machine;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 12:00 am
 */
public class Machines extends SimpleObjects<Machine>
{
    /**
     * 
     */
    public Machines()
    {
        super();
    }
    
    /**
     * 
     * @param objs
     * @throws MachineInException 
     */
    public Machines(ArrayList<Machine> objs)
    {
        super(objs);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Machines";
    }
    
    /**
     *
     * @return
     */
    public static Machines getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Machine> objs = new ArrayList<Machine>();
        for (long id : ids)
            objs.add(new Machine(id));
        return new Machines(objs);
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
    public static Machines getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Machine> objs = new ArrayList<Machine>();
        for (long id : ids)
            objs.add(new Machine(id));
        return new Machines(objs);
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
        for (Machine obj : this)
            str += "\r\n" + obj;
        return str;
    }
}