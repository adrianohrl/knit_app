 package components; 

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public interface Costable
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @return        the result produced by sampleMethod 
     */
    public double getCost();
    
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param newCost
     * @throws InException
     */
    public void updateCost(double newCost) throws InException;
}
