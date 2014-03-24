package components.fabric;

import java.util.ArrayList;
import utilities.Database;
import utilities.table.Table;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/31/2013 08:15 pm
 */
public class FabricResults extends FabricComponent
{
    private enum MeasureLabels {FIRST, SECOND, THIRD};
    private enum DataLabels {ELAPSED_TIME, WEIGHT, HEIGHT, WIDTH};
    public static final int NUMBER_OF_MEASURES = 3;
    private Table<DataLabels, MeasureLabels, Double> table;
    
    /**
     * 
     * @param fabric
     * @param elapsedTimes
     * @param fabricWeights
     * @param fabricHeights
     * @param fabricWidths
     * @throws FabricInException 
     */
    public FabricResults(Fabric fabric, Double[] elapsedTimes, Double[] fabricWeights, 
            Double[] fabricHeights, Double[] fabricWidths) throws FabricInException
    {
        super(fabric);
        this.table = this.createTable(elapsedTimes, fabricWeights, fabricHeights, fabricWidths);
    }
    
    /**
     * 
     * @param fabric
     * @throws FabricInException 
     */
    public FabricResults(Fabric fabric) throws FabricInException
    {
        super(fabric);
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        Double[] elapsedTimes = this.getElapsedTimes(fabric);
        Double[] weights = this.getWeights(fabric);
        Double[] heights = this.getHeights(fabric);
        Double[] widths = this.getWidths(fabric);
        this.table = this.createTable(elapsedTimes, weights, heights, widths);
    }
    
    /**
     * 
     * @param elapsedTimes
     * @param weights
     * @param heights
     * @param widths
     * @return
     * @throws FabricInException 
     */
    private Table<DataLabels, MeasureLabels, Double> createTable(Double[] elapsedTimes, Double[] weights, 
            Double[] heights, Double[] widths) throws FabricInException
    {
        if (elapsedTimes == null)
            throw new FabricInException("Elapsed times must not be null!!!");
        if (elapsedTimes.length != FabricResults.NUMBER_OF_MEASURES)
            throw new FabricInException("Elapsed times must have done three measurements!!!");
        for (Double elapsedTime : elapsedTimes)
            if (elapsedTime <= 0)
                throw new FabricInException("Elapsed time must be greater than zero!!!");
        if (weights == null)
            throw new FabricInException("Weights must not be null!!!");
        if (weights.length != FabricResults.NUMBER_OF_MEASURES)
            throw new FabricInException("Weights must have done three measurements!!!");
        for (Double weight : weights)
            if (weight <= 0)
                throw new FabricInException("Weight must be greater than zero!!!");
        if (heights == null)
            throw new FabricInException("Heights must not be null!!!");
        if (heights.length != FabricResults.NUMBER_OF_MEASURES)
            throw new FabricInException("Heights must have done three measurements!!!");
        for (Double height : heights)
            if (height <= 0)
                throw new FabricInException("Height must be greater than zero!!!");
        if (widths == null)
            throw new FabricInException("Widths must not be null!!!");
        if (widths.length != FabricResults.NUMBER_OF_MEASURES)
            throw new FabricInException("Widths must have done three measurements!!!");
        for (Double width : widths)
            if (width <= 0)
                throw new FabricInException("Width must be greater than zero!!!");
        DataLabels[] rows = DataLabels.values();
        MeasureLabels[] columns = MeasureLabels.values();
        Double[][] cells = new Double[rows.length][];
        cells[0] = elapsedTimes;
        cells[1] = weights;
        cells[2] = heights;
        cells[3] = widths;
        return new Table<DataLabels, MeasureLabels, Double>(rows, columns, cells);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "FabricResults";
    }
    
    /**
     * 
     * @return 
     */
    private Table<DataLabels, MeasureLabels, Double> getTable()
    {
        return this.table;
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
        for (int i = 0; i < this.table.getColumnCount(); i++)
        {
            MeasureLabels label = this.table.getColumnHeader(i);
            Double elapsedTime = this.table.getCellElement(DataLabels.ELAPSED_TIME.ordinal(), i);
            Double weight = this.table.getCellElement(DataLabels.WEIGHT.ordinal(), i);
            Double height = this.table.getCellElement(DataLabels.HEIGHT.ordinal(), i);
            Double width = this.table.getCellElement(DataLabels.WIDTH.ordinal(), i);
            String sql = "INSERT INTO " + this.getClassName() + "(fabrId, measureNum, elapsedTime, weight, height, width) VALUES (" + 
                    fabric.getID() + ", " + label.ordinal() + ", " + elapsedTime + ", " + weight + ", " + height + ", " + width + ")";
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
        return obj instanceof FabricResults && this.equals((FabricResults) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(FabricResults obj)
    {
        Table<DataLabels, MeasureLabels, Double> table = obj.getTable();
        String fabricName = super.getFabric().getName();
        return fabricName.equals(obj.getFabric().getName()) && table.equals(this.table);
    }
    
    /**
     * 
     * @param label
     * @return 
     */
    private Double[] getDataValues(DataLabels label) throws FabricInException
    {
        if (label == null)
            throw new FabricInException("Label must not be null!!!");
        ArrayList<Double> values = this.table.getColumnCellElements(label.ordinal());
        return (Double[]) values.toArray();
    }
    
    /**
     * 
     * @param label
     * @return
     * @throws FabricInException 
     */
    private Double[] getDataValues(Fabric fabric, DataLabels label) throws FabricInException
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        if (label == null)
            throw new FabricInException("Label must not be null!!!");
        String sql = "SELECT ";
        switch (label)
        {
            case ELAPSED_TIME:
                sql += "ElapsedTime";
                break;
            case WEIGHT:
                sql += "Weight";
                break;
            case HEIGHT:
                sql += "Height";
                break;
            case WIDTH:
                sql += "Width";
                break;
        }
        sql += " FROM " + this.getClassName() + " WHERE fabrId = " + fabric.getID();
        Double[] values = Database.getDoubleArray(sql);
        return values;
    }
    
    /**
     * 
     * @return 
     */
    public Double[] getElapsedTimes()
    {
        return this.getDataValues(DataLabels.ELAPSED_TIME);
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private Double[] getElapsedTimes(Fabric fabric) throws FabricInException
    {
        return this.getDataValues(fabric, DataLabels.ELAPSED_TIME);
    }
    
    /**
     * 
     * @return 
     */
    public Double[] getWeights()
    {
        return this.getDataValues(DataLabels.WEIGHT);
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private Double[] getWeights(Fabric fabric) throws FabricInException
    {
        return this.getDataValues(fabric, DataLabels.WEIGHT);
    }
    
    /**
     * 
     * @return 
     */
    public Double[] getHeights()
    {
        return this.getDataValues(DataLabels.HEIGHT);
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private Double[] getHeights(Fabric fabric) throws FabricInException
    {
        return this.getDataValues(fabric, DataLabels.HEIGHT);
    }
    
    /**
     * 
     * @return 
     */
    public Double[] getWidths()
    {
        return this.getDataValues(DataLabels.WIDTH);
    }
    
    /**
     * 
     * @param fabric
     * @return
     * @throws FabricInException 
     */
    private Double[] getWidths(Fabric fabric) throws FabricInException
    {
        return this.getDataValues(fabric, DataLabels.WIDTH);
    }
}
