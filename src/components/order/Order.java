package components.order;  

import components.Datable;
import components.InException;
import components.SimpleObject;
import components.Supervisorable;
import components.UpException;
import components.responsible.Responsible;
import utilities.Database;
import java.util.Date;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Order extends SimpleObject implements Supervisorable, Datable
{
    private Responsible resp;
    private Date date;
    
    /**
     * 
     * @param id
     * @throws InException 
     */
    public Order(long id) throws InException
    {
        super(id);
    }
    
    /**
     * 
     * @param name
     * @throws InException 
     */
    public Order(String name) throws InException
    {
        super(name);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Order";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws InException
     */
    @Override
    public SimpleObject getNew(String name) throws InException
    {
        return new Order(name);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str = super.toString() + "";
        return str;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Order && this.equals((Order) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Order obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName());
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Responsible getResponsible()
    {
        return this.resp;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private Responsible getResponsible(long id) throws InException
    {
        if (id <= 0)
            throw new InException("Invalid ID!!!");
        String sql = "SELECT RespId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long respId = Database.getLongElement(sql);
        Responsible resp = new Responsible(respId);
        return resp;
    }
    
    /**
     * 
     * @param newResp
     * @throws UpException
     * @throws InException 
     */
    @Override
    public void updateResponsible(Responsible newResp) throws UpException, InException
    {
        if (newResp == null)
            throw new InException("Responsible must not be null!!!");
        long respId = newResp.getID();
        String sql = "UPDATE " + this.getClassName() + "s SET RespId = " + respId + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.resp = newResp;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getDate()
    {
        return this.date;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws InException 
     */
    private Date getDate(long id) throws InException
    {
        if (id <= 0)
            throw new InException("Invalid ID!!!");
        String sql = "SELECT Date FROM " + this.getClassName() + "s WHERE Id = " + id;
        Date date = Database.getDateElement(sql);
        return date;
    }
    
    /**
     * 
     * @param newDate
     * @throws UpException
     * @throws InException 
     */
    @Override
    public void updateDate(Date newDate) throws UpException, InException
    {
        if (newDate == null)
            throw new InException("Date must not be null!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Date = " + newDate + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.date = newDate;
    }
}
 