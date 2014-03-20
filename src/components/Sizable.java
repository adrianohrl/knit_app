package components;

import components.model.Percentages;

/**
 *
 * @author Adriano HRL
 */
public interface Sizable 
{      
    /**
     * 
     * @return 
     */
    public Percentages getPercentages();
    
    /**
     * 
     * @param newPercs
     * @throws UpException
     * @throws InException 
     */
    public void updatePercentages(Percentages newPercs) throws UpException, InException;
}
