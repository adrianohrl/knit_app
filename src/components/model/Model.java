package components.model; 

import components.*;
import components.collection.Collection;
import components.responsible.Responsible;
import components.size.*;
import utilities.Utilities;
import utilities.Database;
import java.util.Date;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Model extends SimpleObject implements Referenceable, Collectionable, Supervisorable, Datable, Sizable
{
    private long ref;
    private Collection coll;
    private Responsible resp;
    private Date date;
    private Percentages percs;
    
    /**
     * 
     * @param name
     * @param ref
     * @param coll
     * @param resp
     * @param date
     * @param percs
     * @throws ModelInException 
     */
    public Model(String name, long ref, Collection coll, Responsible resp, Date date, Percentages percs) throws ModelInException
    {
        super(name);
        if (ref <= 0)
            throw new ModelInException("Invalid Reference!!!");
        this.ref = ref;
        if (coll == null || !coll.isRegistered())
            throw new ModelInException("Collection must not be null!!!");
        this.coll = coll;
        if (resp == null || !resp.isRegistered())
            throw new ModelInException("Responsible must not be null!!!");
        this.resp = resp;
        if (date == null && !Utilities.isValidDate(date))
            throw new ModelInException("Date must not be null!!!");
        this.date = date;
        if (percs == null && !Utilities.areValidPercentages(percs))
            throw new ModelInException("Invalid Percentages!!!");
        this.percs = percs;
    }
    
    /**
     * 
     * @param id
     * @throws ModelInException 
     */
    public Model(long id) throws ModelInException
    {
        super(id);
        this.ref = this.getRef(id);
        this.coll = this.getCollection(id);
        this.resp = this.getResponsible(id);
        this.date = this.getDate(id);
        this.percs = this.getPercentages(id);
    }
    
    /**
     * 
     * @param name
     * @throws ModelInException 
     */
    public Model(String name) throws ModelInException
    {
        super(name);
        if (super.getID() == 0)
            throw new ModelInException("");
        this.ref = this.getRef(super.getID());
        this.coll = this.getCollection(super.getID());
        this.resp = this.getResponsible(super.getID());
        this.date = this.getDate(super.getID());
        this.percs = this.getPercentages(super.getID());
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Model";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws ModelInException
     */
    @Override
    public SimpleObject getNew(String name) throws ModelInException
    {
        return new Model(name);
    }
    
    /**
     * 
     * @throws ModelUpException 
     */
    @Override
    public void add() throws ModelUpException
    {
        if (super.isDeleted())
            super.updateStatus(false);
        else if (!super.isRegistered())
        {
            String temp1 = "", temp2 = "";
            Sizes sizes = percs.getSizes();
            double[] values = percs.getValues();
            for (int i = 0; i < sizes.size(); i++)
            {
                temp1 += ", " + sizes.get(i);
                temp2 += ", " + values[i];
            }
            String sql = "INSERT INTO " + this.getClassName() + "s(Name, Ref, CollId, RespId, MachId, Date" + temp1 + ") VALUES (\"" + 
                    super.getName() + "\", " + this.ref + ", " + this.coll.getID() + ", " + this.resp.getID() + ", " + this.date + temp2 + ")";
            Database.update(sql);
            SimpleObject obj = this.getNew(super.getName());
            super.setID(obj.getID());
        }
        else
            throw new ModelUpException("This object had already been registered!!!");
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String temp = "";
        Sizes sizes = percs.getSizes();
        double[] values = percs.getValues();
        for (int i = 0; i < sizes.size(); i++)
            temp += ", " + sizes.get(i) + ": " + values[i] + "%";
        String str = super.toString() + ", Reference: " + this.ref + ", " + this.coll + ", " + this.resp + ", " + this.percs + ", Date: " + this.date + temp;
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
        return obj instanceof Model && this.equals((Model) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Model obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        long ref = obj.getRef();
        Collection coll = obj.getCollection();
        Responsible resp = obj.getResponsible();
        Date date = obj.getDate();
        Percentages percs = obj.getPercentages();
        return id == super.getID() && name.equalsIgnoreCase(super.getName()) &&
                ref == this.ref && coll.equals(this.coll) && resp.equals(resp) &&
                date.equals(this.date) && percs.equals(this.percs);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public long getRef()
    {
        return this.ref;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ModelInException 
     */
    private long getRef(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Ref FROM " + this.getClassName() + "s WHERE Id = " + id;
        long ref = Database.getLongElement(sql);
        return ref;
    }
    
    /**
     * 
     * @param newRef
     * @throws ModelInException 
     */
    @Override
    public void updateRef(long newRef) throws ModelInException
    {
        if (newRef <= 0)
            throw new ModelInException("Invalid Reference!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Ref = " + newRef + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.ref = newRef;
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
     * @throws ModelInException 
     */
    private Collection getCollection(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
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
    public void updateCollection(Collection newColl) throws ModelUpException, ModelInException
    {
        if (newColl == null)
            throw new ModelInException("Collection must not be null!!!");
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
    private Responsible getResponsible(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT RespId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long respId = Database.getLongElement(sql);
        Responsible resp = new Responsible(respId);
        return resp;
    }
    
    /**
     * 
     * @param newResp
     * @throws ModelUpException
     * @throws ModelInException 
     */
    @Override
    public void updateResponsible(Responsible newResp) throws ModelUpException, ModelInException
    {
        if (newResp == null)
            throw new ModelInException("Responsible must not be null!!!");
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
     * @throws ModelInException 
     */
    private Date getDate(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Date FROM " + this.getClassName() + "s WHERE Id = " + id;
        Date date = Database.getDateElement(sql);
        return date;
    }
    
    /**
     * 
     * @param newDate
     * @throws ModelUpException
     * @throws ModelInException 
     */
    @Override
    public void updateDate(Date newDate) throws ModelUpException, ModelInException
    {
        if (newDate == null)
            throw new ModelInException("Date must not be null!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Date = " + newDate + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.date = newDate;
    }   
    
    /**
     * 
     * @return 
     */
    @Override
    public Percentages getPercentages()
    {
        return this.percs;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ModelInException 
     */
    private Percentages getPercentages(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        Sizes sizes = Sizes.getPatternSizes();
        double[] percs = new double[sizes.size()];
        for (int i = 0; i < percs.length; i++)
        {
            Size size = (Size) sizes.get(i);
            String sql = "SELECT " + size.getName() + " FROM " + this.getClassName() + "s WHERE Id = " + id;
            percs[i] = Database.getDoubleElement(sql);
        }
        return new Percentages(percs);
    }
    
    /**
     * 
     * @param newValues
     * @throws ModelUpException
     * @throws ModelInException 
     */
    @Override
    public void updatePercentages(Percentages newValues) throws ModelUpException, ModelInException
    {
        if (newValues == null)
            throw new ModelInException("Percentages must not be null!!!");
        Sizes sizes = newValues.getSizes();
        double[] percs = newValues.getValues();
        for (int i = 0; i < percs.length; i++)
        {
            Size size = (Size) sizes.get(i);
            String sql = "UPDATE " + this.getClassName() + "s SET " + size.getName() + " = " + percs[i] + " WHERE Id = " + super.getID();
            Database.update(sql);
        }
        this.percs = newValues;
    }
}
 