package components; 

import components.responsible.Responsible;

/**
 *
 * @author Adriano HRL
 */
public interface Supervisorable 
{    
    /**
     *
     * @return
     */
    public Responsible getResponsible();
    
    /**
     *
     * @param newResp
     * @throws InException
     */
    public void updateResponsible(Responsible newResp) throws UpException, InException;
}
