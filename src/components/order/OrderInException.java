package components.order;

import components.InException;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 05:50pm
 */
public class OrderInException extends InException
{
    /**
     * 
     * @param message 
     */
    public OrderInException(String message)
    {
        super(message);
    }
}
