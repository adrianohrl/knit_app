package components.model;

import components.fabric.Fabric;
import components.fabric.Fabrics;
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
 * @version 03/22/2014 09:50 pm
 */
public class ModelTable extends ModelComponent implements TableInterface<Fabric, Variant, Variant>
{
    private TableInterface<Fabric, Variant, Variant> table;
    
    /**
     * 
     * @param model
     * @param fabrs
     * @param newVars
     * @param oldVars
     * @throws TableException 
     */
    public ModelTable(Model model, Fabrics fabrs, Variants newVars, Variants[] oldVars) throws TableException
    {
        super(model);
        this.table = this.createTable(fabrs, newVars, oldVars);
    }
    
    /**
     * 
     * @param model
     * @throws ModelInException 
     */
    public ModelTable(Model model) throws ModelInException
    {
        super(model);
        if (!model.isRegistered())
            throw new ModelInException("This model is not registered yet!!!");
        this.buildTable(model);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "ModelTable";
    }
    
    /**
     * 
     * @return 
     */
    private TableInterface<Fabric, Variant, Variant> getTable()
    {
        return this.table;
    }
    
    /**
     * 
     * @param model
     * @return 
     */
    private void buildTable(Model model)
    {
        if (model == null)
            throw new ModelInException("Model must not be null!!!");
        if (!model.isRegistered())
            throw new ModelInException("This model is not registered yet!!!");
        String sql = "SELECT fabrId, newVarId, oldVarId FROM " + this.getClassName() + "s WHERE modId = " + model.getID();
        long[][] datas = Database.getLongTable(sql);
        this.table = new Table<Fabric, Variant, Variant>();
        for (int i = 0, j = 1; i < datas.length; i++)
            try
            { 
                Variant newVar = new Variant(datas[i][j]);
                this.addColumn(newVar);
            }
            catch (ModelInException e)
            {
                break;
            }
        int columnLength = this.getColumnCount();
        for (int i = 0, j = 0; i < datas.length; i += columnLength)
        {
            Fabric fabr = new Fabric(datas[i][j]);
            this.addRow(fabr);
        }
        int rowLength = this.getRowCount();
        for (int i = 0; i < rowLength; i++)
            for (int j = 0; j < columnLength; j++)
            {
                Variant oldVar = new Variant(datas[i * columnLength + j][2]);
                this.setCell(i, j, oldVar);
            }
    }
    
    /**
     * 
     * @param fabrs
     * @param oldVars
     * @param newVars
     * @return
     * @throws TableException 
     */
    private TableInterface<Fabric, Variant, Variant> createTable(Fabrics fabrs, Variants newVars, Variants[] oldVars) throws TableException
    {
        if (fabrs == null)
            throw new ModelInException("Fabrics must not be null!!!");
        ArrayList<Fabric> rows = fabrs.getObjs();
        if (newVars == null)
            throw new ModelInException("New variants must not be null!!!");
        ArrayList<Variant> columns = newVars.getObjs();
        if (oldVars == null)
            throw new ModelInException("Old Variants must not be null!!!");
        if (oldVars.length != fabrs.size())
            throw new ModelInException("New Variants vector size must be equals to the number of elements in Fabrics!!!");
        int length = newVars.size();
        for (Variants oldVar : oldVars)
            if (oldVar.size() != length)
                throw new ModelInException("The number of elements in Old Variants vector must be equals to the number of elements in New Variants!!!");
        ArrayList<ArrayList<Variant>> cells = new ArrayList<ArrayList<Variant>>();
        for (int i = 0; i < oldVars.length; i++)
            cells.add(oldVars[i].getObjs());
        return new Table<Fabric, Variant, Variant>(rows, columns, cells);
    }
    
    /**
     * 
     * @throws ModelUpException 
     */
    @Override
    public void add() throws ModelUpException
    {
        if (!super.isRegistered())
            throw new ModelUpException("This model is not registered yet!!!");
        Model model = super.getModel();
        for (int i = 0; i < this.table.getRowCount(); i++)
        {
            Fabric fabr = this.table.getRowHeader(i);
            for (int j = 0; j < this.table.getColumnCount(); j++)
            {
                Variant newVar = this.table.getColumnHeader(j);
                Variant oldVar = this.table.getCellElement(i, j);
                String sql = "INSERT INTO " + this.getClassName() + "s(modId, fabrId, newVarId, oldVarId) VALUES (" + 
                        model.getID() + ", " + fabr.getID() + ", " + newVar.getID() + ", " + oldVar.getID() + ")";
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
        return obj instanceof ModelTable && this.equals((ModelTable) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(TableInterface obj)
    {
        return obj instanceof ModelTable && this.equals((ModelTable) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(ModelComponent obj)
    {
        return obj instanceof ModelTable && this.equals((ModelTable) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(ModelTable obj)
    {
        TableInterface<Fabric, Variant, Variant> table = obj.getTable();
        String modelName = super.getModel().getName();
        return modelName.equals(obj.getModel().getName()) && table.equals(this.table);
    }
    
    /**
     * 
     * @return 
     */
    public Fabrics getFabrics()
    {
        ArrayList<Fabric> objs = this.getRowHeaders();
        Fabrics fabrs = new Fabrics(objs);
        return fabrs;
    }
    
    /**
     * 
     * @return 
     */
    public Variants getNewVariants()
    {
        ArrayList<Variant> objs = this.getColumnHeaders();
        Variants newVars = new Variants(objs);
        return newVars;
    }
    
    /**
     * 
     * @return 
     */
    public Variants[] getOldVariants()
    {
        ArrayList<ArrayList<Variant>> cells = this.getCellElements();
        Variants[] oldVars = new Variants[cells.size()];
        for (int i = 0; i < oldVars.length; i++)
        {
            ArrayList<Variant> objs = cells.get(i);
            oldVars[i] = new Variants(objs);
        }
        return oldVars;
    }
    
    /**
     * 
     * @param newVar
     * @return 
     */
    public Variants getNewVariantOldVariants(Variant newVar) throws ModelInException
    {
        if (newVar == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!newVar.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (!this.hasNewVariant(newVar))
            throw new ModelInException("Invalid new variant!!!");
        int columnIndex = this.getNewVariantIndex(newVar);
        ArrayList<Variant> objs = this.getColumnCellElements(columnIndex);
        Variants oldVars = new Variants(objs);
        return oldVars;
    }
    
    /**
     * 
     * @param newVar
     * @return 
     */
    private int getNewVariantIndex(Variant newVar) throws ModelInException
    {
        if (newVar == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!newVar.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (!this.hasNewVariant(newVar))
            throw new ModelInException("Invalid new variant!!!");
        ArrayList<Variant> newVars = this.getColumnHeaders();
        int columnIndex = newVars.indexOf(newVar);
        return columnIndex;
    }
    
    /**
     * 
     * @param newVar
     * @return
     * @throws ModelInException 
     */
    private boolean hasNewVariant(Variant newVar) throws ModelInException
    {
        if (newVar == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!newVar.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        Variants newVars = this.getNewVariants();
        return newVars.contains(newVar);
    }
    
    /**
     * 
     * @param thrd
     * @return
     * @throws ModelInException 
     */
    private boolean hasFabric(Fabric fabr) throws ModelInException
    {
        if (fabr == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!fabr.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        Fabrics fabrs = this.getFabrics();
        return fabrs.contains(fabr);
    }
    
    /**
     * 
     * @param row
     * @throws TableException 
     */
    @Override
    public void addRow(Fabric row) throws TableException, ModelInException
    {
        if (row == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!row.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        this.table.addRow(row);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(Fabric row, ArrayList<Variant> cells) throws TableException, ModelInException
    {
        if (row == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!row.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        if (cells == null)
            throw new ModelInException("Old Variants must not be null!!!");
        this.table.addRow(row, cells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(Fabric row, Variant[] cells) throws TableException, ModelInException
    {
        if (row == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!row.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        if (cells == null)
            throw new ModelInException("Old Variants must not be null!!!");
        this.table.addRow(row, cells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    public void addRow(Fabric row, Variants cells) throws TableException, ModelInException
    {
        if (row == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!row.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        if (cells == null)
            throw new ModelInException("Old variants must not be null!!!");
        ArrayList<Variant> objs = cells.getObjs();
        this.addRow(row, objs);
    }
    
    /**
     * 
     * @param rowIndex
     * @return
     * @throws TableException 
     */
    @Override
    public Fabric getRowHeader(int rowIndex) throws TableException
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
    public ArrayList<Fabric> getRowHeaders()
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
    public void setRowHeader(int rowIndex, Fabric row) throws TableException, ModelInException
    {
        if (row == null)
            throw new ModelInException("Fabric must not be null!!!");
        if (!row.isRegistered())
            throw new ModelInException("This fabric has not been registered yet!!!");
        this.table.setRowHeader(rowIndex, row);
    }
    
    /**
     * 
     * @param column
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column) throws TableException, ModelInException
    {
        if (column == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!column.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (this.hasNewVariant(column))
            throw new ModelInException("This new variant has been already inputted!!!");
        this.table.addColumn(column);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column, ArrayList<Variant> cells) throws TableException, ModelInException
    {
        if (column == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!column.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (this.hasNewVariant(column))
            throw new ModelInException("This new variant has been already inputted!!!");
        if (cells == null)
            throw new ModelInException("Old variants must not be null!!!");
        this.table.addColumn(column, cells);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(Variant column, Variant[] cells) throws TableException, ModelInException
    {
        if (column == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!column.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (this.hasNewVariant(column))
            throw new ModelInException("This new variant has been already inputted!!!");
        if (cells == null)
            throw new ModelInException("Old variants must not be null!!!");
        this.table.addColumn(column, cells);
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    public void addColumn(Variant column, Variants cells) throws TableException, ModelInException
    {
        if (column == null)
            throw new ModelInException("New variant must not be null!!!");
        if (!column.isRegistered())
            throw new ModelInException("This new variant has not been registered yet!!!");
        if (this.hasNewVariant(column))
            throw new ModelInException("This new variant has been already inputted!!!");
        if (cells == null)
            throw new ModelInException("Old variants must not be null!!!");
        ArrayList<Variant> objs = cells.getObjs();
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
    public void setColumnHeader(int columnIndex, Variant column) throws TableException, ModelInException
    {
        if (column == null)
            throw new ModelInException("New variant must not be null!!!");
        if (this.hasNewVariant(column))
            throw new ModelInException("This new variant has been already inputted!!!");
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
    public Variant getCellElement(int rowIndex, int columnIndex) throws TableException
    {
        return this.table.getCellElement(rowIndex, columnIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ArrayList<Variant>> getCellElements()
    {
        return this.table.getCellElements();
    }
    
    /**
     * 
     * @param rowIndex
     * @return 
     */
    @Override
    public ArrayList<Variant> getRowCellElements(int rowIndex)
    {
        return this.table.getRowCellElements(rowIndex);
    }
    
    /**
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public ArrayList<Variant> getColumnCellElements(int columnIndex)
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
    public void setCell(int rowIndex, int columnIndex, Variant cell)
    {
        if (cell == null)
            throw new ModelInException("Old variant must not be null!!!");
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
            str += ModelTable.getIdentedFormat(column.toString(), length);
        ArrayList<Fabric> rows = this.getRowHeaders();
        ArrayList<ArrayList<Variant>> cells = this.getCellElements();
        for (int i = 0; i < rows.size(); i++)
        {
            Fabric row = rows.get(i);
            str += "\r\n" + ModelTable.getIdentedFormat(row.toString(), length);
            for (int j = 0; j < columns.size(); j++)
            {
                Variant cell = cells.get(i).get(j);
                str += ModelTable.getIdentedFormat(cell.toString(), length);
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
            throw new ModelInException("Data must not be null!!!");
        String str = data;
        for (int i = 0; i < length - data.length(); i++)
            str += " ";
        return str;
    }
}
