package components;  

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public interface Observable
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @return   the result produced by sampleMethod 
     */
    public String getObs();
    
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param   newObs   a sample parameter for a method
     * @throws InException
     */
    public void updateObs(String newObs) throws InException;
}
