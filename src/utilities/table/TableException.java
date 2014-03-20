package utilities.table;

/**
 *
 * @author Adriano HRL
 */
public class TableException extends RuntimeException
{
    /**
     * 
     */
    public TableException()
    {
        super("General Table Exception!!!");
    }
    
    /**
     * 
     * @param message 
     */
    public TableException(String message)
    {
        super(message);
    }
}
