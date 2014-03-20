 package components.collection; 

 import components.SimpleObject;
 
/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Collection extends SimpleObject
{    
    /**
     *
     * @param id
     * @throws CollectionInException
     */
    public Collection(long id) throws CollectionInException
    {
        super(id);
    }
    
    /**
     *
     * @param name
     * @throws CollectionInException
     */
    public Collection(String name) throws CollectionInException
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
        return "Collection";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws CollectionInException
     */
    @Override
    public SimpleObject getNew(String name) throws CollectionInException
    {
        return new Collection(name);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Collection && this.equals((Collection) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Collection obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName());
    }
}