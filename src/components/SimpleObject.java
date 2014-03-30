package components;  

import utilities.Database;

/**
 * Abstract class SimpleObject - This class contain basic methods that all 
 * SimpleObjetcs must have.
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 08:00 pm
 */
public abstract class SimpleObject
{    
    /**
     * Stores SimpleObject id based on Database, if this is not registered yet 
     * its id is ZERO.
     */
    private long id;
    
    /**
     * Stores SimpleObject name.
     */
    private String name;
    
    /**
     * Stores SimpleObject status:<br>
     * 
     * <b>NOT_REGISTERED<\b> means this SimpleObject has not been registered on 
     * Database yet.<br>
     * <b>REGISTERED<\b> means this SimpleObject has already been registered on 
     * Database.<br>
     * <b>DELETED<\b> means this SimpleObject has already been registered on 
     * Database, although it is deleted.<br>
     */
    private Status status;
    
    /**
     * Build a new SimpleObject based on its id. When using this SimpleObject 
     * constructor, the programmer must make sure this id is valid and it 
     * belongs to an existent SimpleObject on Database. Otherwise, this 
     * constructor throws an InException.
     * 
     * @param id must be greater than zero and existent on Database.
     * @throws InException when ID is invalid.
     */
    public SimpleObject(long id) throws InException
    {
        if (id <= 0)
            throw new InException("Invalid ID!!!");
        this.id = id;
        this.name = this.getName(id);
        this.status = this.getStatus(id);
    }
    
    /**
     * Build a new SimpleObject based on its name. This constructor can be used 
     * when the programmer wants to build either unregistered SimpleObjects or 
     * registered ones. If the input name doesn't belong to a registered 
     * SimpleObject on Database, its ID is zero.
     * 
     * @param name must not be empty or null.
     * @throws InException when name is invalid or null.
     */
    public SimpleObject(String name) throws InException
    {
        this.id = this.getID(name);
        this.name = name;
        this.status = this.getStatus(this.id);
    }
    
    /**
     * Gets this SimpleObject current ID. If it is zero, it means it has not 
     * been registered on Database yet. Otherwise, it is positive.
     * 
     * @return a long number that represents this SimpleObject ID.
     */
    public long getID()
    {
        return this.id;
    }
    
    /**
     * Gets the SimpleObject ID whose name is the input String based on 
     * Database. If the input name does not exist on Database, the return is 
     * ZERO.
     * 
     * @param name must not be empty or null.
     * @return a long number that represents the SimpleObject ID whose name is 
     * the input String.
     * @throws InException when name is not valid or when it is null.
     */
    protected final long getID(String name) throws InException
    {
        if (name == null)
            throw new InException("Name must not be null!!!");
        if (name.equals(""))
            throw new InException("Invalid Name!!!");
        String sql = "SELECT Id FROM " + this.getClassName() + "s WHERE Name = \"" + name + "\"";
        long id = Database.getLongElement(sql);
        return id;
    }
    
    /** 
     * Sets the SimpleObject ID to newID.
     * 
     * @param newID must be a positive number.
     * @throws InException when newID is not positive.
     */
    protected void setID(long newID) throws InException
    {
        if (newID <= 0)
            throw new InException("Invalid ID!!!");
        this.id = newID;
    }
    
    /**
     * Gets this SimpleObject current Name.
     * 
     * @return a String that represents this SimpleObject ID.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the SimpleObject Name whose ID is the input long number based on 
     * Database. If the input ID is not a positive number, an InException is 
     * thrown. However, if the input ID is positive and it does not exist on 
     * Database, the output String will be null.
     * 
     * @param id must be a positive number.
     * @return a String that represents the SimpleObject name whose ID is the
     * input parameter.
     * @throws InException when id is not a positive number.
     */
    private String getName(long id) throws InException
    {
        if (id <= 0)
            throw new InException("Invalid ID!!!");
        String sql = "SELECT Name FROM " + this.getClassName() + "s WHERE Id = " + id;
        String name = Database.getStringElement(sql);
        return name;
    }
    
    /** 
     * Gets this SimpleObject current Name. 
     * 
     * @return a Status enum that represents the current registration status 
     * of this SimpleObject.
     */
    public Status getStatus()
    {
        return this.status;
    }
    
    /**
     * Gets the SimpleObject Status whose ID is the input long number based on 
     * Database. If the input ID is not a positive number, an InException is 
     * thrown. However, if the input ID is positive and it does not exist on 
     * Database, the output Status is IS_NOT_REGISTERED.
     * 
     * @param id must be a positive number.
     * @return a Status that represents the SimpleObject registration status 
     * whose ID is the input parameter.
     * @throws InException when id is not a positive number.
     */
    protected final Status getStatus(long id) throws InException
    {
        if (id < 0)
            throw new InException("Invalid ID!!!");
        else if (id == 0)
            return Status.IS_NOT_REGISTERED;
        String sql = "SELECT isDeleted FROM " + this.getClassName() + "s WHERE Id = " + id;
        boolean isDeleted = Database.getBooleanElement(sql);
        if (isDeleted)
            return Status.IS_DELETED;
        if (this.getName(id) == null)
            return Status.IS_NOT_REGISTERED;
        return Status.IS_REGISTERED;
    }
    
    /**
     * Sets this SimpleObject Status to newStatus.
     * 
     * @param newStatus must not be null.
     * @throws InException when newStatus is null.
     */
    protected void setStatus(Status newStatus) throws InException
    {
        if (newStatus == null)
            throw new InException("New Status must not be null!!!");
        this.status = newStatus;
    }
    
    /**
     * Gets the specific SimpleObject class name.
     * 
     * @return a String that represents this specific SimpleObject class name.
     */
    protected abstract String getClassName();
    
    /**
     * Verifies if this SimpleObject is registered on database.
     * 
     * @return a boolean in which, whenever it is true, this SimpleObject 
     * is registered. Otherwise, it is not registered or deleted on database.
     */
    public boolean isRegistered()
    {
        return this.status != Status.IS_NOT_REGISTERED;
    }
    
    /**
     * Verifies if the input SimpleObject is registered on database.
     * 
     * @param obj must not be null.
     * @return a boolean in which, whenever it is true, the input SimpleObject 
     * is registered. Otherwise, it is not registered or is considered deleted 
     * on database.
     * @throws InException when obj is null.
     */
    public static boolean isRegistered(SimpleObject obj) throws InException
    {
        if (obj == null)
            throw new InException("SimpleObject obj must not be null!!!");
        return obj.status != Status.IS_NOT_REGISTERED;
    }
    
    /**
     * Adds this SimpleObject in database.
     * 
     * @throws UpException when this SimpleObject is already registered.
     */
    public void register() throws UpException
    {
        if (this.isDeleted())
            this.updateStatus(false);
        if (!this.isRegistered())
            throw new UpException("This object had already been registered!!!");
        String sql = "INSERT INTO " + this.getClassName() + "s(Name) VALUES (\"" + this.name + "\")";
        Database.update(sql);
        this.id = this.getID(this.name);
    }
    
    /**
     * Verifies if this SimpleObject has been considered deleted on database.
     * 
     * @return a boolean in which, whenever is true, this SimpleObject is 
     * considered deleted. Otherwise, it is registered or not registered on
     * database.
     */
    public boolean isDeleted()
    {
        return this.status == Status.IS_DELETED;
    }
    
    /**
     * Verifies if the input SimpleObject has been considered deleted on 
     * database.
     * 
     * @param obj must not be null.
     * @return a boolean in which, whenever is true, the input SimpleObject obj
     * is considered deleted. Otherwise, it is registered or not registered on
     * database.
     * @throws InException when obj is null.
     */
    public static boolean isDeleted(SimpleObject obj) throws InException
    {
        if (obj == null)
            throw new InException("SimpleObject obj must not be null!!!");
        return obj.status == Status.IS_DELETED;
    }
    
    /**
     * Considers this SimpleObject as deleted on database.
     * 
     * @throws UpException when this SimpleObject is not registered or even when
     * it has been already deleted.
     */
    public void delete() throws UpException
    {
        if (!this.isRegistered())
            throw new UpException("This object has not been registered yet!!!");
        else if(this.isDeleted())
            throw new UpException("This object had already been deleted!!!");
        else 
            this.updateStatus(true);
    }
    
    /**
     * Gets a new specific SimpleObject object based on its name.
     * 
     * @param name must not be null.
     * @return a specific SimpleObject object base on input parameter.
     * @throws InException when input name is null.
     */
    public abstract SimpleObject getNew(String name) throws InException;
    
    /**
     * Updates this SimpleObject name to newName in database. And then it sets 
     * this SimpleObject name to newName.
     * 
     * @param newName must not be null and a valid name.
     * @throws UpException when a SimpleObject whose name is newName is already 
     * registered.
     * @throws InException when the input newName is not a valid name.
     */
    public void updateName(String newName) throws UpException, InException
    {
        if(newName.equals(""))
            throw new InException("Invalid New Name!!!");
        try
        {
            SimpleObject obj = this.getNew(newName);
            if(SimpleObject.isRegistered(obj))
                throw new UpException("This object had already been updated!!!");
        }
        catch (InException ie)
        {
            String sql = "UPDATE " + this.getClassName() + "s SET Name = \"" + newName + "\" WHERE Name = \"" + this.name + "\"";
            Database.update(sql);
            this.name = newName;
        }
    }
    
    /**
     * Updates this SimpleObject status on database. If the input status is 
     * false, it is considered deleted. Otherwise, it becomes registered again. 
     * Finally, this method sets this SimpleObject status to IS_DELETED when 
     * true, or IS_REGISTERED when false.
     * 
     * @param status a boolean that represents this SimpleObject new status. 
     * When it is true, updates to IS_DELETED, but when it is false, updates to 
     * IS_REGISTERED.
     */
    public void updateStatus(boolean status)
    {
        String sql = "UPDATE " + this.getClassName() + "s SET isDeleted = " + status + " WHERE Id = " + this.id;
        Database.update(sql);
        if (status)
            this.status = Status.IS_DELETED;
        else
            this.status = Status.IS_REGISTERED;
    }
    
    /**
     * Generates the String that represents this SimpleObject.
     * 
     * @return a String that represents this SimpleObject.
     */
    @Override
    public String toString()
    {
        String str = this.getName();
        if (this.isDeleted())
            str += "*";
        return str;
    }
    
    /**
     * Verifies if the input Object is equals to this SimpleObject.
     * 
     * @param obj can be any Object object.
     * @return a boolean that is true when the input Object object is equals to
     * this SimpleObject.
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof SimpleObject && this.equals((SimpleObject) obj);
    }
    
    /**
     * Verifies if the input SimpleObject object is equals to this SimpleObject.
     * 
     * @param obj must not be null.
     * @return a boolean that is true when the input SimpleObject object is 
     * equals to this SimpleObject.
     */
    public abstract boolean equals(SimpleObject obj);
}
