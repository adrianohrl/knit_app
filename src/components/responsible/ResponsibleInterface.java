package components.responsible;

import java.util.Date;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/04/2014 08:23pm
 */
interface ResponsibleInterface 
{
    /**
     * 
     * @return 
     */
    public String getEmail();
    
    /**
     * 
     * @param newEmail
     * @throws ResponsibleInException 
     */
    public void updateEmail(String newEmail) throws ResponsibleInException, ResponsibleUpException;
    
    /**
     * 
     * @return 
     */
    public String getUsername();
    
    /**
     * 
     * @param newUsername
     * @throws ResponsibleInException 
     */
    public void updateUsername(String newUsername) throws ResponsibleInException;
    
    /**
     * 
     * @param password
     * @return 
     */
    public boolean comparePassword(String password);
    
    /**
     * 
     * @param newPassword
     * @throws ResponsibleInException 
     */
    public void updatePassword(String newPassword) throws ResponsibleInException, ResponsibleUpException;
    
    /**
     * 
     * @return 
     */
    public Date getPasswordUpdateDate();
    
    /**
     * 
     * @param newDate
     * @throws ResponsibleInException
     * @throws ResponsibleUpException 
     */
    public void updatePasswordUpdateDate(Date newDate) throws ResponsibleInException, ResponsibleUpException;
    
    /**
     * 
     * @return 
     */
    public long getAccessLevel();
    
    /**
     * 
     * @param newLevel
     * @throws ResponsibleInException
     * @throws ResponsibleUpException 
     */
    public void updateAccessLevel(long newLevel) throws ResponsibleInException, ResponsibleUpException;
}
