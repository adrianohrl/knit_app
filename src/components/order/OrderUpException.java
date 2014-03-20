package components.order;

import components.UpException;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 05:50pm
 */
public class OrderUpException extends UpException
{
    /**
     * 
     * @param message 
     */
    public OrderUpException(String message)
    {
        super(message);
    }
}