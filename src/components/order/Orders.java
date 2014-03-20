package components.order;  

import components.SimpleObjects;
import utilities.Database;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 11:00 am
 */
public class Orders extends SimpleObjects<Order>
{
    public Orders()
    {
        super();
    }
    
    public Orders(ArrayList<Order> objects)
    {
        super(objects);
    }
    
    /**
     *
     * @return
     */
    protected static String getClassName()
    {
        return "Orders";
    }
    
    /**
     *
     * @return
     */
    public static Orders getAllRegistereds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = false";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Order> objs = new ArrayList<Order>();
        for (long id : ids)
            objs.add(new Order(id));
        return new Orders(objs);
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
    public static Orders getAllDeleteds()
    {
        String sql = "SELECT Id FROM " + getClassName() + " WHERE isDeleted = true";
        long[] ids = Database.getLongArray(sql);
        ArrayList<Order> objs = new ArrayList<Order>();
        for (long id : ids)
            objs.add(new Order(id));
        return new Orders(objs);
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
        for (Order obj : this)
            str += "\r\n" + obj;
        return str;
    }
}
