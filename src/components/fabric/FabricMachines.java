package components.fabric;

import components.machine.Machine;
import components.machine.Machines;
import java.util.ArrayList;
import utilities.Database;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 01/01/2014 11:00 am
 */
public class FabricMachines extends FabricComponent 
{
    private Machines machs;

    /**
     *
     * @param fabric
     * @param machs
     * @throws FabricInException
     */
    public FabricMachines(Fabric fabric, Machines machs) throws FabricInException 
    {
        super(fabric);
        if (machs == null) 
            throw new FabricInException("Machines must not be null!!!");
        this.machs = machs;
    }

    /**
     *
     * @param fabric
     * @throws FabricInException
     */
    public FabricMachines(Fabric fabric) throws FabricInException 
    {
        super(fabric);
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        this.machs = this.getMachines(fabric);
    }

    /**
     *
     * @return
     */
    @Override
    protected String getClassName() 
    {
        return "FabricMachines";
    }

    /**
     *
     */
    @Override
    public void add() throws FabricUpException
    {
        if (!super.isRegistered())
            throw new FabricUpException("This fabric is not registered yet!!!");
        Fabric fabric = super.getFabric();
        for (int i = 0; i < this.machs.size(); i++) 
        {
            Machine mach = this.machs.get(i);
            String sql = "INSERT INTO " + this.getClassName() + "(fabrId, machId) VALUES (" + fabric.getID() + ", " + mach.getID() + ")";
            Database.update(sql);
        }
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
        return obj instanceof FabricTable && this.equals((FabricMachines) obj);
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(FabricMachines obj) 
    {
        Machines machs = obj.getMachines();
        String fabricName = super.getFabric().getName();
        return fabricName.equals(obj.getFabric().getName()) && machs.equals(this.machs);
    }

    /**
     *
     * @return
     */
    public Machines getMachines() 
    {
        return this.machs;
    }

    /**
     *
     * @param fabric
     * @return
     */
    private Machines getMachines(Fabric fabric) throws FabricInException 
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        String sql = "SELECT machId FROM " + this.getClassName() + " WHERE fabrId = " + fabric.getID();
        long[] datas = Database.getLongArray(sql);
        ArrayList<Machine> objs = new ArrayList<Machine>();
        for (int i = 0; i < datas.length; i++)
            objs.add(new Machine(datas[i]));
        Machines machs = new Machines(objs);
        return machs;
    }
}
