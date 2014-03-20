 package components; 

 import components.collection.*;
 
/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/29/2013 12:50
 */
public interface Collectionable 
{
    /**
     *
     * @return
     */
    public Collection getCollection();
    
    /**
     *
     * @param newColl
     */
    public void updateCollection(Collection newColl) throws UpException, InException;
}
