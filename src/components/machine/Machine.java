package components.machine;  

import components.Costable;
import components.SimpleObject;
import utilities.Database;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Machine extends SimpleObject implements Costable
{
    private double cost;
    
    /**
     * 
     * @param name
     * @param cost
     * @throws MachineInException 
     */
    public Machine(String name, double cost) throws MachineInException
    {
        super(name);
        if (cost <= 0)
            throw new MachineInException("Invalid Cost Value!!!");
        this.cost = cost;
    }
    
    /**
     * 
     * @param id
     * @throws MachineInException 
     */
    public Machine(long id) throws MachineInException
    {
        super(id);
        this.cost = this.getCost(id);
    }
    
    /**
     * 
     * @param name
     * @throws MachineInException 
     */
    public Machine(String name) throws MachineInException
    {
        super(name);
        long id = getID();
        if (id > 0)
            this.cost = this.getCost(id);
        else
            throw new MachineInException("This machine has not been registered yet!!!");
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Machine";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws MachineInException
     */
    @Override
    public SimpleObject getNew(String name) throws MachineInException
    {
        return new Machine(name);
    }
    
    /**
     * 
     * @throws MachineUpException 
     */
    @Override
    public void register() throws MachineUpException
    {
        if (super.isDeleted())
            super.updateStatus(false);
        if (super.isRegistered())
            throw new MachineUpException("This object had already been registered!!!");
        String sql = "INSERT INTO " + this.getClassName() + "s(Name, Cost) VALUES (\"" + super.getName() + "\", " + this.cost + ")";
        Database.update(sql);
        SimpleObject obj = this.getNew(super.getName());
        super.setID(obj.getID());
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Machine && this.equals((Machine) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Machine obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        double cost = obj.getCost();
        return id == super.getID() && name.equalsIgnoreCase(super.getName()) && cost == this.cost;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public double getCost()
    {
        return this.cost;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws MachineInException 
     */
    private double getCost(long id) throws MachineInException
    {
        double cost = 0.0;
        if (id > 0)
        {
            String sql = "SELECT Cost FROM " + this.getClassName() + "s WHERE Id = " + id;
            cost = Database.getDoubleElement(sql);
        }
        else if (id < 0)
            throw new MachineInException("Invalid ID!!!");
        return cost;
    }
    
    /**
     * 
     * @param newCost
     * @throws MachineInException 
     */
    @Override
    public void updateCost(double newCost) throws MachineInException
    {
        if (newCost > 0)
        {
            String sql = "UPDATE " + this.getClassName() + "s SET Cost = " + newCost + " WHERE Id = " + super.getID();
            Database.update(sql);
            this.cost = newCost;
        }
        else
            throw new MachineInException("Invalid Cost Value!!!");
    }
}