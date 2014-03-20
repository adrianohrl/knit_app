package components;  

/**
 * InException class
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 08:30 am
 */
public class InException extends RuntimeException
{
    public InException()
    {
        super("General Input Exception!!!");
    }
    
    public InException(String message)
    {
        super(message);
    }
}
