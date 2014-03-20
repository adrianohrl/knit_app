package components.model;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Models extends SimpleObjects<Model>
{
    public Models()
    {
        super();
    }
    
    public Models(ArrayList<Model> objects)
    {
        super(objects);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Models";
    }
    
    /**
     *
     * @return
     */
    public static Models getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Model> objs = new ArrayList<Model>();
        for (long id : ids)
            objs.add(new Model(id));
        return new Models(objs);
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
    public static Models getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Model> objs = new ArrayList<Model>();
        for (long id : ids)
            objs.add(new Model(id));
        return new Models(objs);
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
        for (Model obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
