package components.variant;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 12:00 am
 */
public class Variants extends SimpleObjects<Variant>
{
    public Variants()
    {
        super();
    }
    
    public Variants(ArrayList<Variant> objs)
    {
        super(objs);
    }
    
    /**
     * 
     * @return
     */
    protected static String getClassName()
    {
        return "Variants";
    }
    
    /**
     * 
     * @return
     */
    public static Variants getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Variant> objs = new ArrayList<Variant>();
        for (long id : ids)
            objs.add(new Variant(id));
        return new Variants(objs);
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
    public static Variants getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Variant> objs = new ArrayList<Variant>();
        for (long id : ids)
            objs.add(new Variant(id));
        return new Variants(objs);
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
        for (Variant obj : this)
            str += "\r\n" + obj;
        return str;
    }
}