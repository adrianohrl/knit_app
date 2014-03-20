package components.thread_type;  

import components.Costable;
import components.DensityMeasurable;
import components.Observable;
import components.SimpleObject;
import utilities.Database;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class ThreadType extends SimpleObject implements Observable, Costable, DensityMeasurable
{
    private double cost;
    private String obs;
    private double density;
    
    /**
     * 
     * @param name
     * @param cost
     * @param obs
     * @param density
     * @throws ThreadTypeInException 
     */
    public ThreadType(String name, double cost, String obs, double density) throws ThreadTypeInException
    {
        super(name);
        if (cost <= 0)
            throw new ThreadTypeInException("Invalid cost value!!!");
        this.cost = cost;
        this.obs = obs;
        if (density <= 0)
            throw new ThreadTypeInException("Invalid density value!!!");
        this.density = density;
    }
    
    /**
     * 
     * @param id
     * @throws ThreadTypeInException 
     */
    public ThreadType(long id) throws ThreadTypeInException
    {
        super(id);
        this.obs = this.getObs(id);
        this.cost = this.getCost(id);
        this.density = this.getDensity(id);
    }
    
    /**
     * 
     * @param name
     * @throws ThreadTypeInException 
     */
    public ThreadType(String name) throws ThreadTypeInException
    {
        super(name);
        long id = super.getID();
        if (id <= 0)
            throw new ThreadTypeInException("This Thread Type has not been registered yet!!!");
        this.obs = this.getObs(id);
        this.cost = this.getCost(id);
        this.density = this.getDensity(id);            
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "ThreadType";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws ThreadTypeInException
     */
    @Override
    public SimpleObject getNew(String name) throws ThreadTypeInException
    {
        return new ThreadType(name);
    }
    
    /**
     * 
     * @throws ThreadTypeUpException 
     */
    @Override
    public void add() throws ThreadTypeUpException
    {
        if (super.isDeleted())
            super.updateStatus(false);
        else if (!super.isRegistered())
        {
            String sql = "INSERT INTO " + this.getClassName() + "s(Name, Cost, Obs, Density) VALUES (\"" + super.getName() + "\", " + this.cost + ", \"" + this.obs + "\", " + this.density + ")";
            Database.update(sql);
            SimpleObject obj = this.getNew(super.getName());
            super.setID(obj.getID());
        }
        else
            throw new ThreadTypeUpException("This object had already been registered!!!");
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof ThreadType && this.equals((ThreadType) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(ThreadType obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        double cost = obj.getCost();
        String obs = obj.getObs();
        return id == super.getID() && name.equalsIgnoreCase(super.getName()) && cost == this.cost && obs.equalsIgnoreCase(obs);
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
     * @throws ThreadTypeInException 
     */
    private double getCost(long id) throws ThreadTypeInException
    {
        double cost = 0.0;
        if (id <= 0)
            throw new ThreadTypeInException("Invalid ID!!!");
        String sql = "SELECT Cost FROM " + this.getClassName() + "s WHERE Id = " + id;
        cost = Database.getDoubleElement(sql);
        return cost;
    }
    
    /**
     * 
     * @param newCost
     * @throws ThreadTypeInException 
     */
    @Override
    public void updateCost(double newCost) throws  ThreadTypeUpException, ThreadTypeInException
    {
        if (newCost <= 0)
            throw new ThreadTypeInException("Invalid Cost Value!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Cost = " + newCost + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.cost = newCost;           
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getObs()
    {
        return this.obs;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ThreadTypeInException 
     */
    private String getObs(long id) throws ThreadTypeInException
    {
        String obs = "";
        if (id <= 0)
            throw new ThreadTypeInException("Invalid ID!!!");
        String sql = "SELECT Obs FROM " + this.getClassName() + "s WHERE Id = " + id;
        obs = Database.getStringElement(sql); 
        return obs;
    }
    
    /**
     * 
     * @param newObs
     * @throws ThreadTypeUpException 
     */
    @Override
    public void updateObs(String newObs) throws  ThreadTypeUpException
    {
        String sql = "UPDATE " + this.getClassName() + "s SET Obs = " + newObs + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.obs = newObs;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public double getDensity()
    {
        return this.density;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ThreadTypeInException 
     */
    private double getDensity(long id) throws ThreadTypeInException
    {
        double density = 0;
        if (id <= 0)
            throw new ThreadTypeInException("Invalid ID!!!");
        String sql = "SELECT Density FROM " + this.getClassName() + "s WHERE Id = " + id;
        density = Database.getDoubleElement(sql); 
        return density;
    }
    
    /**
     * 
     * @param newDensity
     * @throws ThreadTypeInException
     * @throws ThreadTypeUpException 
     */
    @Override
    public void updateDensity(double newDensity) throws ThreadTypeInException, ThreadTypeUpException
    {
        if (newDensity <= 0)
            throw new ThreadTypeInException("Invalid new density value!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Density = " + newDensity + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.density = newDensity; 
    }
}
