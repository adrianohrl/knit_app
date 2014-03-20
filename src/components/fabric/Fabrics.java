package components.fabric; 

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Fabrics extends SimpleObjects<Fabric>
{
    public Fabrics()
    {
        super();
        
    }
    
    public Fabrics(ArrayList<Fabric> objects)
    {
        super(objects);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Fabrics";
    }
    
    /**
     *
     * @return
     */
    public static Fabrics getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Fabric> objs = new ArrayList<Fabric>();
        for (long id : ids)
            objs.add(new Fabric(id));
        return new Fabrics(objs);
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
    public static Fabrics getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Fabric> objs = new ArrayList<Fabric>();
        for (long id : ids)
            objs.add(new Fabric(id));
        return new Fabrics(objs);
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
        for (Fabric obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
