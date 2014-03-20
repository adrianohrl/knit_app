package components; 

import components.machine.Machine;

/**
 *
 * @author Adriano HRL
 */
public interface Manufacturable 
{
    /**
     * 
     * @return 
     */
    public Machine getMachine();
    
    /**
     * 
     * @param newMach
     * @throws UpException
     * @throws InException 
     */
    public void updateMachine(Machine newMach) throws UpException, InException;
}
