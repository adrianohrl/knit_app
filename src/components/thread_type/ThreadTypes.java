package components.thread_type;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 12:00 am
 */
public class ThreadTypes extends SimpleObjects<ThreadType>
{
    /**
     * 
     */
    public ThreadTypes()
    {
        super();
    }
    
    /**
     * 
     * @param objs 
     */
    public ThreadTypes(ArrayList<ThreadType> objs)
    {
        super(objs);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "ThreadTypes";
    }
    
    /**
     *
     * @return
     */
    public static ThreadTypes getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<ThreadType> objs = new ArrayList<ThreadType>();
        for (long id : ids)
            objs.add(new ThreadType(id));
        return new ThreadTypes(objs);
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
    public static ThreadTypes getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<ThreadType> objs = new ArrayList<ThreadType>();
        for (long id : ids)
            objs.add(new ThreadType(id));
        return new ThreadTypes(objs);
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
        for (ThreadType obj : this)
            str += "\r\n" + obj;
        return str;
    }
}