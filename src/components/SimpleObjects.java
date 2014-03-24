package components;  

import utilities.list.List;
import utilities.list.ListException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class SimpleObjects - This class contain basic methods that all Simple Objetcs must have
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 09:00 am
 * @param <T>
 */
public abstract class SimpleObjects<T extends SimpleObject> implements List<T>
{
    /**
     * 
     */
    private ArrayList<T> objs;
    
    /**
     *
     */
    public SimpleObjects()
    {
        this.objs = new ArrayList<T>();
    }
    
    /**
     *
     * @param objs
     * @throws InException
     */
    public SimpleObjects(ArrayList<T> objs) throws InException
    {
        this();
        this.setObjs(objs);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<T> getObjs()
    {
        return objs;
    }
    
    /**
     *
     * @param objs
     * @throws InException
     */
    public void setObjs(ArrayList<T> objs) throws InException
    {
        if (objs == null)
            throw new InException("Objects must not be null!!!");
        for(T obj : objs)
            this.add(obj);
    }
    
    /**
     * 
     * @param obj
     * @throws InException 
     */
    public void add(T obj) throws InException
    {
        if (obj == null)
            throw new InException("Object must not be null!!!");
        this.objs.add(obj);
    }
    
    /**
     * 
     */
    @Override
    public void clear()
    {
        this.objs = new ArrayList<T>();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int size()
    {
        return this.objs.size();
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return this.objs.isEmpty();
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public T get(int index) throws ListException
    {
        if (index < 0 || index >= this.size())
            throw new ListException("Invalid index!!!");
        return this.objs.get(index);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean contains(T obj) throws ListException
    {
        if (objs == null)
            throw new ListException("Input object must not be null!!!");
        return objs.contains(obj);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public T[] toArray()
    {
        T[] array = (T[]) new SimpleObject[this.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = this.get(i);
        return array;
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public T remove(int index) throws ListException
    {
        if (index < 0 || index >= this.size())
            throw new ListException("Invalid index!!!");
        return this.objs.remove(index);
    }

    /**
     *
     * @return
     */
    @Override
    public abstract String toString();
    
    /**
     * 
     * @return 
     */
    @Override
    public Iterator<T> iterator()
    {
        return new SimpleObjectsIterator(this);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof SimpleObjects && this.equals((SimpleObjects) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(SimpleObjects obj)
    {
        return this.objs.equals(obj.getObjs());
    }
}
