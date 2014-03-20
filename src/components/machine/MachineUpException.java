package components.machine;

import components.UpException;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 05:50pm
 */
public class MachineUpException extends UpException
{
    /**
     * 
     * @param message 
     */
    public MachineUpException(String message)
    {
        super(message);
    }
}