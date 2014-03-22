package components.fabric;

import components.*;
import components.collection.Collection;
import components.color.Colors;
import components.machine.Machine;
import components.machine.Machines;
import components.responsible.Responsible;
import components.thread_type.ThreadTypes;
import components.variant.Variants;
import utilities.Database;
import utilities.DateTime;
import java.util.Date;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Fabric extends SimpleObject implements Observable, Collectionable, Supervisorable, Manufacturable, Datable
{
    private String obs;
    private Collection coll;
    private Responsible resp;
    private Machine mach;
    private Date date;
    private FabricTable table;
    private FabricMachines machs;
    private MeasurementResults results;
    
    /**
     * 
     * @param name
     * @param obs
     * @param coll
     * @param resp
     * @param mach
     * @param date
     * @param thrds
     * @param vars
     * @param colors
     * @param machs
     * @param coneWeights
     * @param elapsedTimes
     * @param fabricWeights
     * @param fabricHeights
     * @param fabricWidths
     * @throws FabricInException 
     */
    public Fabric(String name, String obs, Collection coll, Responsible resp, Machine mach, Date date, 
            ThreadTypes thrds, Variants vars, Colors[] colors, Machines machs, Double[][] coneWeights, 
            Double[] elapsedTimes, Double[] fabricWeights, Double[] fabricHeights, Double[] fabricWidths) throws FabricInException
    {
        super(name);
        this.obs = obs;
        if (coll == null || !coll.isRegistered())
            throw new FabricInException("Collection must not be null!!!");
        this.coll = coll;
        if (resp == null || !resp.isRegistered())
            throw new FabricInException("Responsible must not be null!!!");
        this.resp = resp;
        if (mach == null || !mach.isRegistered())
            throw new FabricInException("Machine must not be null!!!");
        this.mach = mach;
        if (date == null)
            throw new FabricInException("Date must not be null!!!");
        this.date = date;
        this.table = new FabricTable(this, thrds, vars, colors);
        this.machs = new FabricMachines(this, machs);
        ConeResults cRes = new ConeResults(this, thrds, coneWeights);
        FabricResults fRes = new FabricResults(this, elapsedTimes, fabricWeights, fabricHeights, fabricWidths);
        this.results = new MeasurementResults(this, cRes, fRes);
    }
    
    /**
     * 
     * @param id
     * @throws FabricInException 
     */
    public Fabric(long id) throws FabricInException
    {
        super(id);
        this.obs = this.getObs(id);
        this.coll = this.getCollection(id);
        this.resp = this.getResponsible(id);
        this.mach = this.getMachine(id);
        this.date = this.getDate(id);
        this.table = this.getTable(this);
        this.machs = this.getMachines(this);
        this.results = this.getMeasurementResults(this);
    }
    
    /**
     * 
     * @param name
     * @throws FabricInException 
     */
    public Fabric(String name) throws FabricInException
    {
        super(name);
        if (!this.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        this.obs = this.getObs(super.getID());
        this.coll = this.getCollection(super.getID());
        this.resp = this.getResponsible(super.getID());
        this.mach = this.getMachine(super.getID());
        this.date = this.getDate(super.getID());
        this.table = this.getTable(this);
        this.machs = this.getMachines(this);
        this.results = this.getMeasurementResults(this);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Fabric";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws FabricInException
     */
    @Override
    public SimpleObject getNew(String name) throws FabricInException
    {
        return new Fabric(name);
    }
    
    /**
     * 
     * @throws FabricUpException 
     */
    @Override
    public void add() throws FabricUpException
    {
        if (super.isDeleted())
            super.updateStatus(false);
        else if (!super.isRegistered())
        {
            String sql = "INSERT INTO " + this.getClassName() + "s(Name, Obs, CollId, RespId, MachId, Date) VALUES (\"" + 
                    super.getName() + "\", \"" + this.obs + "\", " + this.coll.getID() + ", " + this.resp.getID() + ", " +
                    this.mach.getID() + ", " + DateTime.formatDateTime(this.date) + ")";
            Database.update(sql);
            super.setID(super.getID(super.getName()));
            super.setStatus(Status.IS_REGISTERED);
            this.table.add();
            this.machs.add();
            this.results.add();
        }
        else
            throw new FabricUpException("This object had already been registered!!!");
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str = super.toString() + ", Obs: " + this.obs + ", " + this.coll + ", " + this.resp + ", " + this.mach + ", Date: " + DateTime.formatDateTime(this.date); 
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
        return obj instanceof Fabric && this.equals((Fabric) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Fabric obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        String obs = obj.getObs();
        Collection coll = obj.getCollection();
        Responsible resp = obj.getResponsible();
        Machine mach = obj.getMachine();
        Date date = obj.getDate();
        return id == super.getID() && name.equalsIgnoreCase(super.getName()) &&
                obs.equalsIgnoreCase(this.obs) && coll.equals(this.coll) && 
                resp.equals(this.resp) && mach.equals(this.mach) && date.equals(this.date);
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
     * @throws FabricInException 
     */
    private String getObs(long id) throws FabricInException
    {
        String obs = "";
        if (id > 0)
        {
            String sql = "SELECT Obs FROM " + this.getClassName() + "s WHERE Id = " + id;
            obs = Database.getStringElement(sql);
        }
        else if (id < 0)
            throw new FabricInException("Invalid ID!!!");
        return obs;
    }
    
    /**
     * 
     * @param newObs
     * @throws FabricUpException 
     */
    @Override
    public void updateObs(String newObs) throws  FabricUpException
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
    public Collection getCollection()
    {
        return this.coll;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws FabricInException 
     */
    private Collection getCollection(long id) throws FabricInException
    {
        if (id <= 0)
            throw new FabricInException("Invalid ID!!!");
        String sql = "SELECT CollId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long collId = Database.getLongElement(sql);
        Collection coll = new Collection(collId);
        return coll;
    }
    
    /**
     * 
     * @param newColl 
     */
    @Override
    public void updateCollection(Collection newColl) throws FabricUpException, FabricInException
    {
        if (newColl == null)
            throw new FabricInException("Collection must not be null!!!");
        long collId = newColl.getID();
        String sql = "UPDATE " + this.getClassName() + "s SET CollId = " + collId + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.coll = newColl;
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
    private Responsible getResponsible(long id) throws FabricInException
    {
        if (id <= 0)
            throw new FabricInException("Invalid ID!!!");
        String sql = "SELECT RespId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long respId = Database.getLongElement(sql);
        Responsible resp = new Responsible(respId);
        return resp;
    }
    
    /**
     * 
     * @param newResp
     * @throws FabricUpException
     * @throws FabricInException 
     */
    @Override
    public void updateResponsible(Responsible newResp) throws FabricUpException, FabricInException
    {
        if (newResp == null)
            throw new FabricInException("Responsible must not be null!!!");
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
    public Machine getMachine()
    {
        return this.mach;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws FabricInException 
     */
    private Machine getMachine(long id) throws FabricInException
    {
        if (id <= 0)
            throw new FabricInException("Invalid ID!!!");
        String sql = "SELECT MachId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long machId = Database.getLongElement(sql);
        Machine mach = new Machine(machId);
        return mach;
    }
    
    /**
     * 
     * @param newMach
     * @throws FabricUpException
     * @throws FabricInException 
     */
    @Override
    public void updateMachine(Machine newMach) throws FabricUpException, FabricInException
    {
        if (newMach == null)
            throw new FabricInException("Machine must not be null!!!");
        long machId = newMach.getID();
        String sql = "UPDATE " + this.getClassName() + "s SET MachId = " + machId + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.mach = newMach;
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
     * @throws FabricInException 
     */
    private Date getDate(long id) throws FabricInException
    {
        if (id <= 0)
            throw new FabricInException("Invalid ID!!!");
        String sql = "SELECT Date FROM " + this.getClassName() + "s WHERE Id = " + id;
        Date date = Database.getDateElement(sql);
        return date;
    }
    
    /**
     * 
     * @param newDate
     * @return
     * @throws FabricInException 
     */
    public boolean isValidUpdateDate(Date newDate) throws FabricInException
    {
        if (newDate == null)
            throw new FabricInException("NewDate must not be null!!!");
        return DateTime.isValidUpdateDate(this.date, newDate);
    }
    
    /**
     * 
     * @param newDate
     * @throws FabricUpException
     * @throws FabricInException 
     */
    @Override
    public void updateDate(Date newDate) throws FabricUpException, FabricInException
    {
        if (!this.isValidUpdateDate(newDate))
            throw new FabricInException("Date must not be null!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Date = " + DateTime.formatDateTime(newDate) + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.date = newDate;
    }   
    
    /**
     * 
     * @return 
     */
    public FabricTable getTable()
    {
        return this.table;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private FabricTable getTable(Fabric fabric)
    {
        return new FabricTable(fabric);
    }
    
    /**
     * 
     * @return 
     */
    public FabricMachines getMachines()
    {
        return this.machs;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private FabricMachines getMachines(Fabric fabric)
    {
        return new FabricMachines(fabric);
    }
    
    /**
     * 
     * @return 
     */
    public MeasurementResults getMeasurementResults()
    {
        return this.results;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private MeasurementResults getMeasurementResults(Fabric fabric)
    {
        ConeResults cRes = new ConeResults(fabric);
        FabricResults fRes = new FabricResults(fabric);
        return new MeasurementResults(fabric, cRes, fRes);
    }
}
 