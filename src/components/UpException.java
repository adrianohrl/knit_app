package components;  

/**
 * InException class
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/26/2013 12:30 pm
 */
public class UpException extends RuntimeException
{
    public UpException()
    {
        super("General Input Exception!!!");
    }
    
    public UpException(String message)
    {
        super(message);
    }
}
