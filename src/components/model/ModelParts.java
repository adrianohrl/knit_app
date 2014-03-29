package components.model;

import components.fabric.Fabric;
import components.fabric.Fabrics;
        
/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/26/2014 11:10pm
 * @version 1.0.0001
 */
public class ModelParts extends ModelComponent
{
    private ModelPart[] parts;
    
    /**
     * 
     * @param mod
     * @param parts 
     */
    public ModelParts(Model mod, ModelPart[] parts) throws ModelInException
    {
        super(mod);
        if (parts == null)
            throw new ModelInException("");
        if (mod.getTable().getRowCount() == parts.length)
            throw new ModelInException("");
        this.parts = parts;
    }
    
    public ModelParts(Model mod, Fabrics fabrs, String[] names, String[] programs, 
            double[] consumptions, String[] units, String[] obss) throws ModelInException
    {
        super(mod);
        if (fabrs == null)
            throw new ModelInException("Fabrics must not be null!!!");
        int numOfParts = fabrs.size();
        if (names == null)
            throw new ModelInException("Names must not be null!!!");
        if (names.length != numOfParts) 
            throw new ModelInException("Names must have the same number of Fabrics!!!");
        if (programs == null)
            throw new ModelInException("Programs must not be null!!!");        
        if (programs.length != numOfParts)
            throw new ModelInException("Programs must have the same number of Fabrics!!!");
        if (consumptions == null)
            throw new ModelInException("Consumptions must not be null!!!");
        if (consumptions.length != numOfParts)
            throw new ModelInException("Consumptions must have the same number of Fabrics!!!");
        if (units == null)
            throw new ModelInException("Units must not be null!!!");
        if (units.length != numOfParts)
            throw new ModelInException("Units must have the same number of Fabrics!!!");
        if (obss == null)
            throw new ModelInException("Observations must not be null!!!");
        if (obss.length != numOfParts)
            throw new ModelInException("Observations must have the same number of Fabrics!!!");
    }
            
}
