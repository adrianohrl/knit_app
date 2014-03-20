package components;

import utilities.list.ListException;
import java.util.Iterator;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 11:00am
 * @param <T>
 */
public class SimpleObjectsIterator<T extends SimpleObject> implements Iterator<T>
{
    /**
     * 
     */
    SimpleObjects<T> objs;
    
    /**
     * 
     * @param objs
     * @throws InException 
     */
    public SimpleObjectsIterator(SimpleObjects<T> objs) throws InException
    {
        if (objs == null)
            throw new InException("Simple Objects must not be null!!!");
        this.objs = objs;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public boolean hasNext()
    {
        return !this.objs.isEmpty();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public T next()
    {
        if (!hasNext())
            throw new ListException("End of list!!!");
        return this.objs.remove(0);
    }
    
    /**
     * 
     */
    @Override
    public void remove()
    {   
        throw new UnsupportedOperationException("Remove not implemented.");
    }
}
