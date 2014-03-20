package utilities.list;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 03/03/2014 12:15am
 */
public class ListException extends RuntimeException
{
    public ListException(String message)
    {
        super(message);
    }
    
    public ListException()
    {
        super("General List Exception!!!");
    }
}
