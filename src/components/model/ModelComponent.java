package components.model;

import components.Status;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/22/2014 09:00 am
 */
public abstract class ModelComponent 
{
    private Model mod;
    
    /**
     * 
     * @param mod
     * @throws ModelInException 
     */
    public ModelComponent(Model mod) throws ModelInException
    {
        if (mod == null)
            throw new ModelInException("Model must not be null!!!");
        this.mod = mod;
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
    public Model getModel()
    {
        return this.mod;
    }
    
    /**
     * 
     */
    public abstract void register();
    
    /**
     * 
     * @return 
     */
    public Status getStatus()
    {
        return this.mod.getStatus();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isRegistered()
    {
        return this.mod.isRegistered();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDeleted()
    {
        return this.mod.isDeleted();
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
        return obj instanceof ModelComponent && this.equals((ModelComponent) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public abstract boolean equals(ModelComponent obj);
}
