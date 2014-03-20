package components.fabric;

import components.Status;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 01/03/2014 09:00 am
 */
public abstract class FabricComponent 
{
    private Fabric fabric;
    
    /**
     * 
     * @param fabric
     * @throws FabricInException 
     */
    public FabricComponent(Fabric fabric) throws FabricInException
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        this.fabric = fabric;
    }
    
    /**
     * 
     * @return 
     */
    protected abstract String getClassName();
    
    /**
     * 
     * @return 
     */
    public Fabric getFabric()
    {
        return this.fabric;
    }
    
    /**
     * 
     */
    public abstract void add();
    
    /**
     * 
     * @return 
     */
    public Status getStatus()
    {
        return this.fabric.getStatus();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isRegistered()
    {
        return this.fabric.isRegistered();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDeleted()
    {
        return this.fabric.isDeleted();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public abstract String toString();
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof FabricComponent && this.equals((FabricComponent) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public abstract boolean equals(FabricComponent obj);
}
