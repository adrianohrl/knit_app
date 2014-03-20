 package components.collection; 

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Collections extends SimpleObjects<Collection>
{
    /**
     *
     */
    public Collections()
    {
        super();
    }
    
    /**
     *
     * @param objs
     * @throws CollectionInException
     */
    public Collections(ArrayList<Collection> objs)
    {
        super(objs);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Collections";
    }
    
    /**
     *
     * @return
     */
    public static Collections getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Collection> objs = new ArrayList<Collection>();
        for (long id : ids)
            objs.add(new Collection(id));
        return new Collections(objs);
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
    public static Collections getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Collection> objs = new ArrayList<Collection>();
        for (long id : ids)
            objs.add(new Collection(id));
        return new Collections(objs);
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
        for (Collection obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
