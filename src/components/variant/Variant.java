package components.variant;  

import components.SimpleObject;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Variant extends SimpleObject
{
    /**
     * 
     * @param id
     * @throws VariantInException 
     */
    public Variant(long id) throws VariantInException
    {
        super(id);
    }
    
    /**
     * 
     * @param name
     * @throws VariantInException 
     */
    public Variant(String name) throws VariantInException
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
        return "Variant";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws VariantInException
     */
    @Override
    public SimpleObject getNew(String name) throws VariantInException
    {
        return new Variant(name);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Variant && this.equals((Variant) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Variant obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName());
    }
}
