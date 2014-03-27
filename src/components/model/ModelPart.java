package components.model;

import components.fabric.Fabric;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/25/2014 10:12pm
 * @version 1.0.0000
 */
public class ModelPart extends ModelComponent
{
    private Fabric fabr;
    private String part;
    private String program;
    private double consumption;
    private String unit;
    private String obs;
    
    /**
     * 
     * @param model
     * @param fabr
     * @param part
     * @param program
     * @param consumption
     * @param unit
     * @param obs 
     */
    public ModelPart(Model model, Fabric fabr, String part, String program, double consumption, String unit, String obs)
    {
        super(model);
        this.fabr = fabr;
        this.part = part;
        this.program = program;
        this.consumption = consumption;
        this.unit = unit;
        this.obs = obs;
    }
}
