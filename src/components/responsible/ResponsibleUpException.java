package components.responsible;

import components.UpException;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 05:50pm
 */
public class ResponsibleUpException extends UpException
{
    /**
     * 
     * @param message 
     */
    public ResponsibleUpException(String message)
    {
        super(message);
    }
}