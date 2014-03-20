package components.fabric;
import components.thread_type.*;
import utilities.Database;
import utilities.table.Table;
import java.util.ArrayList;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/311/2013 08:15 pm
 */
public class ConeResults extends FabricComponent
{
    private enum MeasureLabels {INITIAL, FIRST, SECOND, THIRD};
    public static final int NUMBER_OF_MEASURES = 4;
    private Table<ThreadType, MeasureLabels, Double> table;
    
    /**
     * 
     * @param fabric
     * @param thrds
     * @param coneWeights
     * @throws FabricInException 
     */
    public ConeResults(Fabric fabric, ThreadTypes thrds, Double[][] coneWeights) throws FabricInException
    {
        super(fabric);
        this.table = this.createTable(thrds, coneWeights);
    }
    
    /**
     * 
     * @param fabric
     * @throws FabricInException 
     */
    public ConeResults(Fabric fabric) throws FabricInException
    {
        super(fabric);
        if (!this.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        ThreadTypes thrds = this.getThreadTypes(fabric);
        Double[][] weights = this.getWeights(fabric);
        this.table = this.createTable(thrds, weights);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "ConeResults";
    }
    
    /**
     * 
     * @return 
     */
    private Table<ThreadType, MeasureLabels, Double> getTable()
    {
        return this.table;
    }
    
    private Table<ThreadType, MeasureLabels, Double> createTable(ThreadTypes thrds, Double[][] weights) throws FabricInException
    {
        if (thrds == null)
            throw new FabricInException("Thread Types must not be null!!!");
        if (weights.length != thrds.size())
            throw new FabricInException("Weights must be the same size as Thread Types!!!");
        for (Double[] typeWeights : weights)
            if (typeWeights.length != ConeResults.NUMBER_OF_MEASURES)
                throw new FabricInException("Each Thread Type Cone must have done four measurements!!!");
            else
                for (Double weight : typeWeights)
                    if (weight <= 0)
                        throw new FabricInException("Weight must be greater than zero!!!");
        ArrayList<ThreadType> objs = thrds.getObjs();
        ThreadType[] rows = new ThreadType[objs.size()];
        for (int i = 0; i < rows.length; i++)
            rows[i] = objs.get(i);
        MeasureLabels[] columns = MeasureLabels.values();
        Double[][] cells = weights;
        return new Table<ThreadType, MeasureLabels, Double>(rows, columns, cells);
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
        for (int i = 0; i < this.table.getRowCount(); i++) 
        {
            ThreadType thrd = this.table.getRowHeader(i);
            for (int j = 0; j < this.table.getColumnCount(); j++) 
            {
                MeasureLabels label = this.table.getColumnHeader(j);
                Double weight = this.table.getCellElement(i, j);
                String sql = "INSERT INTO " + this.getClassName() + "(fabrId, thrdId, measureNum, weight) VALUES ("
                        + fabric.getID() + ", " + thrd.getID() + ", " + label.ordinal() + ", " + weight + ")";
                Database.update(sql);
            }
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
        Fabric fabric = obj.getFabric();
        return fabric.equals(super.getFabric()) && obj instanceof ConeResults && this.equals((ConeResults) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(ConeResults obj)
    {
        Table<ThreadType, MeasureLabels, Double> table = obj.getTable();
        return table.equals(this.table);
    }
    
    public ThreadTypes getThreadTypes()
    {
        ArrayList<ThreadType> objs = this.table.getRowHeaders();
        ThreadTypes thrds = new ThreadTypes(objs);
        return thrds;
    }
    
    private ThreadTypes getThreadTypes(Fabric fabric) throws FabricInException
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        String sql = "SELECT thrdId FROM " + this.getClassName() + " WHERE fabrId = " + fabric.getID();
        long[] datas = Database.getLongArray(sql);
        ArrayList<ThreadType> objs = new ArrayList<ThreadType>();
        for (int i = 0; i < datas.length; i += ConeResults.NUMBER_OF_MEASURES)
            objs.add(new ThreadType(datas[i]));
        ThreadTypes thrds = new ThreadTypes(objs);
        return thrds;
    }
    
    public Double[][]  getWeights()
    {
        ArrayList<ArrayList<Double>> cells = this.table.getCellElements();
        Double[][] weights = new Double[cells.size()][];
        for (int i = 0; i < weights.length; i++)
            weights[i] = (Double[]) cells.get(i).toArray();
        return weights;
    }
    
    private Double[][] getWeights(Fabric fabric) throws FabricInException
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        String sql = "SELECT weight FROM " + this.getClassName() + " WHERE fabrId = " + fabric.getID();
        Double[] datas = Database.getDoubleArray(sql);
        int NUMBER_OF_THREAD_TYPES = (int) datas.length / ConeResults.NUMBER_OF_MEASURES;
        Double[][] weights = new Double[NUMBER_OF_THREAD_TYPES][];
        for (int i = 0; i < weights.length; i++)
        {
            weights[i] = new Double[ConeResults.NUMBER_OF_MEASURES];
            for (int j = 0; j < weights[i].length; j++)
                weights[i][j] = datas[i * ConeResults.NUMBER_OF_MEASURES + j];
        }
        return weights;
    }
}
