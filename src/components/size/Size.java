package components.size;  

import components.SimpleObject;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Size extends SimpleObject
{
    /**
     * 
     * @param id
     * @throws SizeInException 
     */
    public Size(long id) throws SizeInException
    {
        super(id);
    }
    
    /**
     * 
     * @param name
     * @throws SizeInException 
     */
    public Size(String name) throws SizeInException
    {
        super(name);
    }
    
    public Size(SizeLabels label) throws SizeInException
    {
        super(label.name());       
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Size";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws SizeInException
     */
    @Override
    public SimpleObject getNew(String name) throws SizeInException
    {
        return new Size(name);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Size && this.equals((Size) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Size obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName());
    }
}
 