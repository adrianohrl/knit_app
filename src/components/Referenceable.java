/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package components;

/**
 *
 * @author Adriano HRL
 */
public interface Referenceable 
{
    /**
     * 
     * @return 
     */
    public long getRef();
    
    /**
     * 
     * @param newRef
     * @throws InException 
     */
    public void updateRef(long newRef) throws InException;
}
