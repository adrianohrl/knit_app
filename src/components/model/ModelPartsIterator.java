package components.model;

import java.util.Iterator;
import utilities.list.ListException;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/29/2014 10:21pm
 * @version 1.0.0000
 */
public class ModelPartsIterator implements Iterator<ModelPart>
{
    private ModelParts parts;
    
    /**
     * 
     * @param parts
     * @throws ModelInException 
     */
    public ModelPartsIterator(ModelParts parts) throws ModelInException
    {
        if (parts == null)
            throw new ModelInException("Model parts must not be null!!!");
        this.parts = parts;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public boolean hasNext()
    {
        return !this.parts.isEmpty();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ModelPart next()
    {
        if (!hasNext())
            throw new ListException("End of list!!!");
        return this.parts.remove(0);
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
