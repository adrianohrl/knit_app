package components.size; 

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Sizes extends SimpleObjects<Size>
{
    public Sizes()
    {
        super();
    }
    
    public Sizes(ArrayList<Size> objects)
    {
        super(objects);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Sizes";
    }
    
    /**
     *
     * @return
     */
    public static Sizes getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Size> objs = new ArrayList<Size>();
        for (long id : ids)
            objs.add(new Size(id));
        return new Sizes(objs);
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
    public static Sizes getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Size> objs = new ArrayList<Size>();
        for (long id : ids)
            objs.add(new Size(id));
        return new Sizes(objs);
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
    
    public static Sizes getPatternSizes()
    {
        ArrayList<Size> objs = new ArrayList<Size>();
        SizeLabels[] labels = SizeLabels.values();
        for (SizeLabels label : labels)
            objs.add(new Size(label));
        Sizes sizes = new Sizes(objs);
        return sizes;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str = getClassName() + ":";
        for (Size obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
