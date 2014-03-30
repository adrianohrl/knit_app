package components.model;

import components.fabric.Fabric;
import utilities.Database;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/25/2014 10:12pm
 * @version 1.0.0002
 */
public class ModelPart extends ModelComponent
{
    private long id;
    private String name;
    private Fabric fabr;
    private String program;
    private double consumption;
    private String unit;
    private String obs;
    
    /**
     * 
     * @param mod
     * @param fabr
     * @param name
     * @param program
     * @param consumption
     * @param unit
     * @param obs 
     */
    public ModelPart(Model mod, Fabric fabr, String name, String program, double consumption, String unit, String obs) throws ModelInException
    {
        super(mod);
        if (mod.isRegistered())
            throw new ModelInException("This model has already been registered. Please, in this case, use other constructor!!!");
        this.id = 0;
        if (fabr == null)
            throw new ModelInException("Fabric must not be null!!!");
        this.fabr = fabr;
        if (name == null || name.equals(""))
            throw new ModelInException("Invalid part name!!!");
        this.name = name;
        if (program == null || program.equals(""))
            throw new ModelInException("Invalid program name!!!");
        this.program = program;
        if (consumption <= 0)
            throw new ModelInException("Consumption must be a positive real number!!!");
        this.consumption = consumption;
        if (unit == null || unit.equals(""))
            throw new ModelInException("Invalid consumption unit!!!");
        this.unit = unit;
        if (obs == null)
            this.obs = "";
        else
            this.obs = obs;
    }
    
    /**
     * 
     * @param mod
     * @param name
     * @throws ModelInException 
     */
    public ModelPart(Model mod, String name) throws ModelInException
    {
        super(mod);
        this.name = name;
        this.id = this.getID(name);
        if (this.id <= 0)
            throw new ModelInException("This model doesn't have a part whose name is " + name + "!!!");
        this.fabr = this.getFabric(this.id);
        this.program = this.getProgram(this.id);
        this.consumption = this.getConsumption(this.id);
        this.unit = this.getUnit(this.id);
        this.obs = this.getObs(this.id);
    }
    
    /**
     * 
     * @param mod
     * @param id
     * @throws ModelInException 
     */
    public ModelPart(Model mod, long id) throws ModelInException
    {
        super(mod);
        if (id <= 0)
            throw new ModelInException("Invalid Model Part ID!!!");
        this.id = id;
        this.name = this.getName(id);
        this.fabr = this.getFabric(id);
        this.program = this.getProgram(id);
        this.consumption = this.getConsumption(id);
        this.unit = this.getUnit(id);
        this.obs = this.getObs(id);
    }
    
    /**
     * 
     * @return 
     */
    public long getID()
    {
        return this.id;
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    private long getID(String name)
    {
        if (name == null)
            throw new ModelInException("Name must not be null!!!");
        if (name.equals(""))
            throw new ModelInException("Invalid Name!!!");
        Model mod = super.getModel();
        String sql = "SELECT Id FROM " + this.getClassName() + "s WHERE Name = \"" + name + "\" AND ModId = " + mod.getID();
        long id = Database.getLongElement(sql);
        return id;
    }
    
    /**
     * 
     * @param id
     * @throws ModelInException 
     */
    private void setID(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        this.id = id;
    }
    
    /**
     * 
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getName(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Name FROM " + this.getClassName() + "s WHERE Id = " + id;
        String name = Database.getStringElement(sql);
        return name;
    }
    
    /**
     * 
     * @return 
     */
    public Fabric getFabric()
    {
        return this.fabr;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private Fabric getFabric(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT FabrId FROM " + this.getClassName() + "s WHERE Id = " + id;
        long fabrId = Database.getLongElement(sql);
        return new Fabric(fabrId);
    }
    
    /**
     * 
     * @return 
     */
    public String getProgram()
    {
        return this.program;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getProgram(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Program FROM " + this.getClassName() + "s WHERE Id = " + id;
        String program = Database.getStringElement(sql);
        return program;
    }
    
    /**
     * 
     * @return 
     */
    public double getConsumption()
    {
        return this.consumption;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private double getConsumption(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Consumption FROM " + this.getClassName() + "s WHERE Id = " + id;
        double consumption = Database.getDoubleElement(sql);
        return consumption;
    }
    
    /**
     * 
     * @return 
     */
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getUnit(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Unit FROM " + this.getClassName() + "s WHERE Id = " + id;
        String unit = Database.getStringElement(sql);
        return unit;
    }
    
    /**
     * 
     * @return 
     */
    public String getObs()
    {
        return this.obs;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getObs(long id) throws ModelInException
    {
        if (id <= 0)
            throw new ModelInException("Invalid ID!!!");
        String sql = "SELECT Obs FROM " + this.getClassName() + "s WHERE Id = " + id;
        String obs = Database.getStringElement(sql);
        return obs;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getClassName()
    {
        return "ModelPart";
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(ModelComponent obj)
    {
        return obj instanceof ModelPart && this.equals((ModelPart) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(ModelPart obj)
    {
        String modelName = super.getModel().getName();
        return modelName.equals(obj.getModel().getName()) && 
                this.fabr.equals(obj.getFabric()) && this.id == obj.getID() && 
                this.name.equals(obj.getName()) && this.program.equals(obj.getProgram()) && 
                this.consumption == obj.getConsumption() && this.unit.equals(obj.getUnit()) && 
                this.obs.equals(obj.getObs());
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return this.name;
    }
    
    /**
     * 
     */
    @Override
    public void register()
    {
        if (super.isRegistered())
            throw new ModelUpException("This object had already been registered!!!");
        String sql = "INSERT INTO " + this.getClassName() + "s(Name, ModId, FabrId, Program, " +
                "Consumption, Unit, Obs) VALUES (\"" + this.getName() + "\", " + 
                super.getModel().getID() + ", " + this.fabr.getID() + ", \"" + this.program + 
                "\", " + this.consumption + ", \"" + this.unit + "\", \"" + this.obs + "\")";
        Database.update(sql);
        ModelPart obj = this.getNew(super.getModel(), this.name);
        this.setID(obj.getID());            
    }
    
    /**
     * 
     * @param mod
     * @param name
     * @return 
     */
    public ModelPart getNew(Model mod, String name)
    {
        return new ModelPart(mod, name);
    }
}
