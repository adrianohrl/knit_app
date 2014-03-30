package utilities.list;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @since 03/03/2014 12:15am
 * @version 1.0.0000
 */
public class ListException extends RuntimeException
{
    /**
     * 
     * @param message 
     */
    public ListException(String message)
    {
        super(message);
    }
    
    /**
     * 
     */
    public ListException()
    {
        super("General List Exception!!!");
    }
}
