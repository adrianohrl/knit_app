 package components; 

import java.util.Date;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/29/2013 11:35 am
 */
public interface Datable 
{
    /**
     *
     * @return
     */
    public Date getDate();
    
    /**
     * 
     * @param newDate
     * @throws UpException
     * @throws InException 
     */
    public void updateDate(Date newDate) throws UpException, InException;   
}
