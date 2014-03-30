package utilities.list;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @param <T>
 * @since 03/03/2014 12:00pm
 * @version 1.0.0000
 */
public interface List<T> extends Iterable<T>
{
    /**
     * 
     */
    public void clear();
    
    /**
     *
     * @return
     */
    public int size();
    
    /**
     *
     * @return
     */
    public boolean isEmpty();
    
    /**
     * 
     * @param index
     * @return
     */
    public T get(int index) throws ListException;
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean contains(T obj) throws ListException;
    
    /**
     * 
     * @return 
     */
    public T[] toArray();
    
    /**
     * 
     * @param index
     * @return 
     */
    public T remove(int index) throws ListException;
}
