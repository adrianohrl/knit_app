package components.fabric;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/311/2013 08:15 pm
 */
public class MeasurementResults extends FabricComponent
{
    private FabricResults fRes;
    private ConeResults cRes;
            
    /**
     * 
     * @param fabric
     * @param cRes
     * @param fRes
     * @throws FabricInException 
     */
    public MeasurementResults(Fabric fabric, ConeResults cRes, FabricResults fRes) throws FabricInException
    {
        super(fabric);
        if (cRes == null)
            throw new FabricInException("Cone Results must not be null!!!");
        if (fabric.getID() != cRes.getFabric().getID())
            throw new FabricInException("Cone Results Fabric must be equals to this Fabric!!!");
        this.cRes = cRes;
        if (fRes == null)
            throw new FabricInException("Fabric Results must not be null!!!");
        if (fabric.getID() != fRes.getFabric().getID())
            throw new FabricInException("Fabric Results Fabric must be equals to this Fabric!!!");
        this.fRes = fRes;
    }
    
    /**
     * 
     * @param fabric
     * @throws FabricInException 
     */
    public MeasurementResults(Fabric fabric) throws FabricInException
    {
        super(fabric);
        MeasurementResults results = fabric.getMeasurementResults();
        this.fRes = results.getFabricResults(fabric);
        this.cRes = results.getConeResults(fabric);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "MeasurementResults";
    }
    
    /**
     * 
     * @return 
     */
    public FabricResults getFabricResults()
    {
        return this.fRes;
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private FabricResults getFabricResults(Fabric fabric) throws FabricInException
    {
        return new FabricResults(fabric);
    }
    
    /**
     * 
     * @return 
     */
    public ConeResults getConeResults()
    {
        return this.cRes;
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private ConeResults getConeResults(Fabric fabric) throws FabricInException
    {
        return new ConeResults(fabric);
    }
    
    /**
     * 
     */
    @Override
    public void add() throws FabricUpException
    {
        this.cRes.add();
        this.fRes.add();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return "";
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(FabricComponent obj)
    {
        Fabric fabric = obj.getFabric();
        return fabric.equals(super.getFabric()) && obj instanceof MeasurementResults && this.equals((MeasurementResults) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(MeasurementResults obj)
    {
        ConeResults cRes = obj.getConeResults();
        FabricResults fRes = obj.getFabricResults();
        return cRes.equals(this.cRes) && fRes.equals(this.fRes);
    }
}
