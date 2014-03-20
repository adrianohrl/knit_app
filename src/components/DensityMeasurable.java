package components;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 1:30pm
 */
public interface DensityMeasurable 
{
    /**
     * 
     * @return 
     */
    public double getDensity();
    
    /**
     * 
     * @param newDensity
     * @throws InException
     * @throws UpException 
     */
    public void updateDensity(double newDensity) throws InException, UpException;
}
