package components.model;

import components.Status;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/22/2014 09:00 am
 */
public abstract class ModelComponent 
{
    private Model model;
    
    /**
     * 
     * @param model
     * @throws ModelInException 
     */
    public ModelComponent(Model model) throws ModelInException
    {
        if (model == null)
            throw new ModelInException("Model must not be null!!!");
        this.model = model;
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
        return this.model;
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
        return this.model.getStatus();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isRegistered()
    {
        return this.model.isRegistered();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDeleted()
    {
        return this.model.isDeleted();
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
