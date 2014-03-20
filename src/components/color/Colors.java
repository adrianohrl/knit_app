 package components.color; 

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 12:00 am
 */
public class Colors extends SimpleObjects<Color>
{
    public Colors()
    {
        super();
    }
    
    public Colors(ArrayList<Color> objs)
    {
        super(objs);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Colors";
    }
    
    /**
     *
     * @return
     */
    public static Colors getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Color> objs = new ArrayList<Color>();
        for (long id : ids)
            objs.add(new Color(id));
        return new Colors(objs);
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
    public static Colors getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Color> objs = new ArrayList<Color>();
        for (long id : ids)
            objs.add(new Color(id));
        return new Colors(objs);
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
        for (Color obj : this)
            str += "\r\n" + obj;
        return str;
    }
}