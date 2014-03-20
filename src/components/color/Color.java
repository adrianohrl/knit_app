 package components.color; 

import components.SimpleObject;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Color extends SimpleObject
{

    /**
     *
     * @param id
     * @throws ColorInException
     */
    public Color(long id) throws ColorInException
    {
        super(id);
    }
    
    /**
     *
     * @param name
     * @throws ColorInException
     */
    public Color(String name) throws ColorInException
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
        return "Color";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws ColorInException
     */
    @Override
    public SimpleObject getNew(String name) throws ColorInException
    {
        return new Color(name);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Color && this.equals((Color) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Color obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName());
    }
}
 