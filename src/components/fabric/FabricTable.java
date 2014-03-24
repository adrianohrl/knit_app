package components.fabric;

import components.color.Color;
import components.color.Colors;
import components.thread_type.ThreadType;
import components.thread_type.ThreadTypes;
import components.variant.Variant;
import components.variant.Variants;
import utilities.Database;
import utilities.table.Table;
import utilities.table.TableException;
import utilities.table.TableInterface;
import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 01/01/2014 09:50 pm
 */
public class FabricTable extends FabricComponent implements TableInterface<ThreadType, Variant, Color>
{
    private TableInterface<ThreadType, Variant, Color> table;
    
    /**
     * 
     * @param fabric
     * @param thrds
     * @param vars
     * @param colors
     * @throws TableException 
     */
    public FabricTable(Fabric fabric, ThreadTypes thrds, Variants vars, Colors[] colors) throws TableException
    {
        super(fabric);
        this.table = this.createTable(thrds, vars, colors);
    }
    
    /**
     * 
     * @param fabric
     * @throws FabricInException 
     */
    public FabricTable(Fabric fabric) throws FabricInException
    {
        super(fabric);
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        this.buildTable(fabric);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "FabricTable";
    }
    
    /**
     * 
     * @return 
     */
    private TableInterface<ThreadType, Variant, Color> getTable()
    {
        return this.table;
    }
    
    /**
     * 
     * @param fabric
     * @return 
     */
    private void buildTable(Fabric fabric)
    {
        if (fabric == null)
            throw new FabricInException("Fabric must not be null!!!");
        if (!fabric.isRegistered())
            throw new FabricInException("This fabric is not registered yet!!!");
        String sql = "SELECT thrdId, varId, colorId FROM " + this.getClassName() + "s WHERE fabrId = " + fabric.getID();
        long[][] datas = Database.getLongTable(sql);
        this.table = new Table<ThreadType, Variant, Color>();
        for (int i = 0, j = 1; i < datas.length; i++)
            try
            { 
                Variant var = new Variant(datas[i][j]);
                this.addColumn(var);
            }
            catch (FabricInException e)
            {
                break;
            }
        int columnLength = this.getColumnCount();
        for (int i = 0, j = 0; i < datas.length; i += columnLength)
        {
            ThreadType thrd = new ThreadType(datas[i][j]);
            this.addRow(thrd);
        }
        int rowLength = this.getRowCount();
        for (int i = 0; i < rowLength; i++)
            for (int j = 0; j < columnLength; j++)
            {
                Color color = new Color(datas[i * columnLength + j][2]);
                this.setCell(i, j, color);
            }
    }
    
    /**
     * 
     * @param thrds
     * @param vars
     * @param colors
     * @return
     * @throws TableException 
     */
    private TableInterface<ThreadType, Variant, Color> createTable(ThreadTypes thrds, Variants vars, Colors[] colors) throws TableException
    {
        if (thrds == null)
            throw new FabricInException("Thread Types must not be null!!!");
        ArrayList<ThreadType> rows = thrds.getObjs();
        if (vars == null)
            throw new FabricInException("Variants must not be null!!!");
        ArrayList<Variant> columns = vars.getObjs();
        if (colors == null)
            throw new FabricInException("Colors must not be null!!!");
        if (colors.length != thrds.size())
            throw new FabricInException("Colors vector size must be equals to the number of elements in Thread Types!!!");
        int length = vars.size();
        for (Colors color : colors)
            if (color.size() != length)
                throw new FabricInException("The number of elements in Colors vector must be equals to the number of elements in Variants!!!");
        ArrayList<ArrayList<Color>> cells = new ArrayList<ArrayList<Color>>();
        for (int i = 0; i < colors.length; i++)
            cells.add(colors[i].getObjs());
        return new Table<ThreadType, Variant, Color>(rows, columns, cells);
    }
    
    /**
     * 
     * @throws FabricUpException 
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
                Variant var = this.table.getColumnHeader(j);
                Color color = this.table.getCellElement(i, j);
                String sql = "INSERT INTO " + this.getClassName() + "s(fabrId, thrdId, varId, colorId) VALUES (" + 
                        fabric.getID() + ", " + thrd.getID() + ", " + var.getID() + ", " + color.getID() + ")";
                Database.update(sql);
            }
        }
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof FabricTable && this.equals((FabricTable) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(TableInterface obj)
    {
        return obj instanceof FabricTable && this.equals((FabricTable) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(FabricComponent obj)
    {
        return obj instanceof FabricTable && this.equals((FabricTable) obj);
    }
    
    
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(FabricTable obj)
    {
        TableInterface<ThreadType, Variant, Color> table = obj.getTable();
        String fabricName = super.getFabric().getName();
        return fabricName.equals(obj.getFabric().getName()) && table.equals(this.table);
    }
    
    /**
     * 
     * @return 
     */
    public ThreadTypes getThreadTypes()
    {
        ArrayList<ThreadType> objs = this.getRowHeaders();
        ThreadTypes thrds = new ThreadTypes(objs);
        return thrds;
    }
    
    /**
     * 
     * @return 
     */
    public Variants getVariants()
    {
        ArrayList<Variant> objs = this.getColumnHeaders();
        Variants vars = new Variants(objs);
        return vars;
    }
    
    /**
     * 
     * @return 
     */
    public Colors[] getColors()
    {
        ArrayList<ArrayList<Color>> cells = this.getCellElements();
        Colors[] colors = new Colors[cells.size()];
        for (int i = 0; i < colors.length; i++)
        {
            ArrayList<Color> objs = cells.get(i);
            colors[i] = new Colors(objs);
        }
        return colors;
    }
    
    /**
     * 
     * @param var
     * @return 
     */
    public Colors getVariantColors(Variant var) throws FabricInException
    {
        if (var == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!var.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (!this.hasVariant(var))
            throw new FabricInException("Invalid variant!!!");
        int columnIndex = this.getVariantIndex(var);
        ArrayList<Color> objs = this.getColumnCellElements(columnIndex);
        Colors colors = new Colors(objs);
        return colors;
    }
    
    /**
     * 
     * @param var
     * @return 
     */
    private int getVariantIndex(Variant var) throws FabricInException
    {
        if (var == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!var.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (!this.hasVariant(var))
            throw new FabricInException("Invalid variant!!!");
        ArrayList<Variant> vars = this.getColumnHeaders();
        int columnIndex = vars.indexOf(var);
        return columnIndex;
    }
    
    /**
     * 
     * @param var
     * @return
     * @throws FabricInException 
     */
    private boolean hasVariant(Variant var) throws FabricInException
    {
        if (var == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!var.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        Variants vars = this.getVariants();
        return vars.contains(var);
    }
    
    /**
     * 
     * @param thrd
     * @return
     * @throws FabricInException 
     */
    private boolean hasThreadType(ThreadType thrd) throws FabricInException
    {
        if (thrd == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!thrd.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        ThreadTypes thrds = this.getThreadTypes();
        return thrds.contains(thrd);
    }
    
    /**
     * 
     * @param row
     * @throws TableException 
     */
    @Override
    public void addRow(ThreadType row) throws TableException, FabricInException
    {
        if (row == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!row.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        this.table.addRow(row);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(ThreadType row, ArrayList<Color> cells) throws TableException, FabricInException
    {
        if (row == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!row.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        this.table.addRow(row, cells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(ThreadType row, Color[] cells) throws TableException, FabricInException
    {
        if (row == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!row.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        this.table.addRow(row, cells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    public void addRow(ThreadType row, Colors cells) throws TableException, FabricInException
    {
        if (row == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!row.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        ArrayList<Color> objs = cells.getObjs();
        this.addRow(row, objs);
    }
    
    /**
     * 
     * @param rowIndex
     * @return
     * @throws TableException 
     */
    @Override
    public ThreadType getRowHeader(int rowIndex) throws TableException
    {
        return this.table.getRowHeader(rowIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getRowCount()
    {
        return this.table.getRowCount();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ThreadType> getRowHeaders()
    {
        return this.table.getRowHeaders();
    }
    
    /**
     * 
     * @param rowIndex
     * @param row
     * @throws TableException 
     */
    @Override
    public void setRowHeader(int rowIndex, ThreadType row) throws TableException, FabricInException
    {
        if (row == null)
            throw new FabricInException("Thread Type must not be null!!!");
        if (!row.isRegistered())
            throw new FabricInException("This thread type has not been registered yet!!!");
        this.table.setRowHeader(rowIndex, row);
    }
    
    /**
     * 
     * @param column
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column) throws TableException, FabricInException
    {
        if (column == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!column.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (this.hasVariant(column))
            throw new FabricInException("This Variant has been already inputted!!!");
        this.table.addColumn(column);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column, ArrayList<Color> cells) throws TableException, FabricInException
    {
        if (column == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!column.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (this.hasVariant(column))
            throw new FabricInException("This Variant has been already inputted!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        this.table.addColumn(column, cells);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column, Color[] cells) throws TableException, FabricInException
    {
        if (column == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!column.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (this.hasVariant(column))
            throw new FabricInException("This Variant has been already inputted!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        this.table.addColumn(column, cells);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    public void addColumn(Variant column, Colors cells) throws TableException, FabricInException
    {
        if (column == null)
            throw new FabricInException("Variant must not be null!!!");
        if (!column.isRegistered())
            throw new FabricInException("This variant has not been registered yet!!!");
        if (this.hasVariant(column))
            throw new FabricInException("This Variant has been already inputted!!!");
        if (cells == null)
            throw new FabricInException("Colors must not be null!!!");
        ArrayList<Color> objs = cells.getObjs();
        this.addColumn(column, objs);
    }
    
    /**
     * 
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    @Override
    public Variant getColumnHeader(int columnIndex) throws TableException
    {
        return this.table.getColumnHeader(columnIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getColumnCount()
    {
        return this.table.getColumnCount();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Variant> getColumnHeaders()
    {
        return this.table.getColumnHeaders();
    }
    
    /**
     * 
     * @param columnIndex
     * @param column
     * @throws TableException 
     */
    @Override
    public void setColumnHeader(int columnIndex, Variant column) throws TableException, FabricInException
    {
        if (column == null)
            throw new FabricInException("Variant must not be null!!!");
        if (this.hasVariant(column))
            throw new FabricInException("This Variant has been already inputted!!!");
        this.table.setColumnHeader(columnIndex, column);
    }
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    @Override
    public Color getCellElement(int rowIndex, int columnIndex) throws TableException
    {
        return this.table.getCellElement(rowIndex, columnIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ArrayList<Color>> getCellElements()
    {
        return this.table.getCellElements();
    }
    
    /**
     * 
     * @param rowIndex
     * @return 
     */
    @Override
    public ArrayList<Color> getRowCellElements(int rowIndex)
    {
        return this.table.getRowCellElements(rowIndex);
    }
    
    /**
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public ArrayList<Color> getColumnCellElements(int columnIndex)
    {
        return this.table.getColumnCellElements(columnIndex);
    }
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @param cell 
     */
    @Override
    public void setCell(int rowIndex, int columnIndex, Color cell)
    {
        if (cell == null)
            throw new FabricInException("Color must not be null!!!");
        this.table.setCell(rowIndex, columnIndex, cell);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        int length = 25;
        String str = "";
        ArrayList<Variant> columns = this.getColumnHeaders();
        for (Variant column : columns)
            str += FabricTable.getIdentedFormat(column.toString(), length);
        ArrayList<ThreadType> rows = this.getRowHeaders();
        ArrayList<ArrayList<Color>> cells = this.getCellElements();
        for (int i = 0; i < rows.size(); i++)
        {
            ThreadType row = rows.get(i);
            str += "\r\n" + FabricTable.getIdentedFormat(row.toString(), length);
            for (int j = 0; j < columns.size(); j++)
            {
                Color cell = cells.get(i).get(j);
                str += FabricTable.getIdentedFormat(cell.toString(), length);
            }
        }
        return str;
    }
     
    /**
     * 
     * @param data
     * @param length
     * @return 
     */
    private static String getIdentedFormat(String data, int length)
    {
        if (data == null)
            throw new FabricInException("Data must not be null!!!");
        String str = data;
        for (int i = 0; i < length - data.length(); i++)
            str += " ";
        return str;
    }
}
