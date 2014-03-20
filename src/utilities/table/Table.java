package utilities.table;

import java.util.ArrayList;

/**
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/23/2013 1:00 pm
 * @param <ROW>
 * @param <COL>
 * @param <CELL> 
 */
public class Table<ROW, COL, CELL> implements TableInterface<ROW, COL, CELL>
{
    /**
     * 
     */
    private ArrayList<ROW> rows;
    
    /**
     * 
     */
    private ArrayList<COL> columns;
    
    /**
     * 
     */
    private ArrayList<ArrayList<CELL>> cells;
    
    /**
     * 
     */
    public Table()
    {
        this.rows = new ArrayList<ROW>();
        this.columns = new ArrayList<COL>();
        this.cells = new ArrayList<ArrayList<CELL>>();
    }
    
    /**
     * 
     * @param rows
     * @param columns
     * @param cells
     * @throws TableException 
     */
    public Table(ArrayList<ROW> rows, ArrayList<COL> columns, ArrayList<ArrayList<CELL>> cells) throws TableException
    {
        this();
        if (rows == null)
            throw new TableException("Rows must not be null!!!");
        this.rows = rows;
        if (rows == null)
            throw new TableException("Columns must not be null!!!");
        this.columns = columns;
        if (rows == null)
            throw new TableException("Cells must not be null!!!");
        this.cells = cells;
    }
    
    /**
     * 
     * @param rows
     * @param columns
     * @param cells
     * @throws TableException 
     */
    public Table(ROW[] rows, COL[] columns, CELL[][] cells) throws TableException
    {
        this();
        for (ROW row : rows)
            this.rows.add(row);
        for (COL column : columns)
            this.columns.add(column);
        for (CELL[] array : cells)
        {
            ArrayList<CELL> columnCells = new ArrayList<CELL>();
            for (CELL cell : array)
                columnCells.add(cell);
            this.cells.add(columnCells);
        }
    }
    
    /**
     * 
     * @param row
     * @throws TableException 
     */
    @Override
    public void addRow(ROW row) throws TableException
    {
        if (row == null)
            throw new TableException("Row must not be null!!!");
        this.rows.add(row);
        ArrayList<CELL> columnCells = new ArrayList<CELL>();
        for (int i = 0; i < this.getColumnCount(); i++)
            columnCells.add(null);
        this.cells.add(columnCells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(ROW row, ArrayList<CELL> cells) throws TableException
    {
        if (row == null)
            throw new TableException("Row must not be null!!!");
        if (cells == null)
            throw new TableException("Cells must not be null!!!");
        if (cells.size() != this.getColumnCount())
            throw new TableException("Insuficient number of cells!!!");
        this.rows.add(row);
        this.cells.add(cells);
    }
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addRow(ROW row, CELL[] cells) throws TableException
    {
        if (row == null)
            throw new TableException("Row must not be null!!!");
        if (cells == null)
            throw new TableException("Cells must not be null!!!");
        if (cells.length != this.getColumnCount())
            throw new TableException("Insuficient number of cells!!!");
        this.rows.add(row);
        ArrayList<CELL> newRow = new ArrayList<CELL>();
        for (int i = 0; i < this.getColumnCount(); i++)
            newRow.add(cells[i]);
        this.cells.add(newRow);
    }
    
    /**
     * 
     * @param rowIndex
     * @return
     * @throws TableException 
     */
    @Override
    public ROW getRowHeader(int rowIndex) throws TableException
    {
        if (rowIndex < 0 || rowIndex >= this.getRowCount())
            throw new TableException("Invalid row index!!!");
        return this.rows.get(rowIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getRowCount()
    {
        return this.rows.size();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ROW> getRowHeaders()
    {
        return this.rows;
    }
    
    /**
     * 
     * @param rowIndex
     * @param row
     * @throws TableException 
     */
    @Override
    public void setRowHeader(int rowIndex, ROW row) throws TableException
    {
        if (rowIndex < 0 || rowIndex >= this.getRowCount())
            throw new TableException("Invalid row index!!!");
        if (row == null)
            throw new TableException("Row must not be null!!!");
        this.rows.set(rowIndex, row);
    }
    
    /**
     * 
     * @param column
     * @throws TableException 
     */
    @Override
    public void addColumn(COL column) throws TableException
    {
        if (column == null)
            throw new TableException("Row must not be null!!!");
        this.columns.add(column);
        for (int i = 0; i < this.getRowCount(); i++)
        {
            ArrayList<CELL> rowCells = this.cells.get(i);
            rowCells.add(null);
            this.cells.set(i, rowCells);
        }
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(COL column, ArrayList<CELL> cells) throws TableException
    {
        if (column == null)
            throw new TableException("Row must not be null!!!");
        if (cells == null)
            throw new TableException("Cells must not be null!!!");
        if (cells.size() != this.getRowCount())
            throw new TableException("Insuficient number of cells!!!");
        this.columns.add(column);
        for (int i = 0; i < this.getRowCount(); i++)
        {
            ArrayList<CELL> rowCells = this.cells.get(i);
            rowCells.add(cells.get(i));
            this.cells.set(i, rowCells);
        }
    }
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    @Override
    public void addColumn(COL column, CELL[] cells) throws TableException
    {
        if (column == null)
            throw new TableException("Row must not be null!!!");
        if (cells == null)
            throw new TableException("Cells must not be null!!!");
        if (cells.length != this.getRowCount())
            throw new TableException("Insuficient number of cells!!!");
        this.columns.add(column);
        for (int i = 0; i < this.getRowCount(); i++)
        {
            ArrayList<CELL> rowCells = this.cells.get(i);
            rowCells.add(cells[i]);
            this.cells.set(i, rowCells);
        }
    }
    
    /**
     * 
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    @Override
    public COL getColumnHeader(int columnIndex) throws TableException
    {
        if (columnIndex < 0 || columnIndex >= this.getColumnCount())
            throw new TableException("Invalid column index!!!");
        return this.columns.get(columnIndex);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getColumnCount()
    {
        return this.columns.size();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<COL> getColumnHeaders()
    {
        return this.columns;
    }
    
    /**
     * 
     * @param columnIndex
     * @param column
     * @throws TableException 
     */
    @Override
    public void setColumnHeader(int columnIndex, COL column) throws TableException
    {
        if (columnIndex < 0 || columnIndex >= this.getColumnCount())
            throw new TableException("Invalid column index!!!");
        if (column == null)
            throw new TableException("Column must not be null!!!");
        this.columns.set(columnIndex, column);
    }
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    @Override
    public CELL getCellElement(int rowIndex, int columnIndex) throws TableException
    {
        if (rowIndex < 0 || rowIndex >= this.getRowCount())
            throw new TableException("Invalid row index!!!");
        if (columnIndex < 0 || columnIndex >= this.getColumnCount())
            throw new TableException("Invalid column index!!!");
        return this.cells.get(rowIndex).get(columnIndex);
    }
    
    /**
     * 
     * @param rowIndex
     * @return 
     */
    @Override
    public ArrayList<CELL> getRowCellElements(int rowIndex)
    {
        ArrayList<CELL> rowCells = new ArrayList<CELL>();
        for (ArrayList<CELL> columnCells : this.cells)
        {
            CELL rowCell = columnCells.get(rowIndex);
            rowCells.add(rowCell);
        }
        return rowCells;
    }
    
    /**
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public ArrayList<CELL> getColumnCellElements(int columnIndex)
    {
        ArrayList<CELL> columnCells = this.cells.get(columnIndex);
        return columnCells;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ArrayList<CELL>> getCellElements()
    {
        return this.cells;
    }
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @param cell 
     */
    @Override
    public void setCell(int rowIndex, int columnIndex, CELL cell)
    {
        if (rowIndex < 0 || rowIndex >= this.getRowCount())
            throw new TableException("Invalid row index!!!");
        if (columnIndex < 0 || columnIndex >= this.getColumnCount())
            throw new TableException("Invalid column index!!!");
        ArrayList<CELL> columnCells = this.cells.get(rowIndex);
        columnCells.set(columnIndex, cell);
        this.cells.set(rowIndex, columnCells);
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
        for (COL column : this.columns)
            str += Table.getIdentedFormat(column.toString(), length);
        for (int i = 0; i < this.rows.size(); i++)
        {
            ROW row = this.rows.get(i);
            str += "\r\n" + Table.getIdentedFormat(row.toString(), length);
            for (int j = 0; j < this.columns.size(); j++)
            {
                CELL cell = this.cells.get(i).get(j);
                str += Table.getIdentedFormat(cell.toString(), length);
            }
        }
        return str;
    }
    
    /**
     * 
     * @return 
     */
    public String[][] stringTable()
    {
        int numOfRows = this.getRowCount() + 1;
        int numOfColumns = this.getColumnCount() + 1;
        String[][] table = new String[numOfRows][];
        for (int i = 1; i < numOfRows + 1; i++)
        {
            table[i] = new String[numOfColumns];
            for (int j = 1; j < numOfColumns + 1; j++)
            {
                CELL cell = this.getCellElement(i, j);
                table[i][j] = cell.toString();
            }
        }
        for (int i = 0; i < numOfRows; i++)
        {
            ROW row = this.getRowHeader(i);
            table[i + 1][0] = row.toString();
        }
        for (int j = 0; j < numOfColumns; j++)
        {
            COL column = this.getColumnHeader(j);
            table[0][j + 1] = column.toString();
        }
        return table;
    }
     
    /**
     * 
     * @param data
     * @param length
     * @return 
     */
    private static String getIdentedFormat(String data, int length)
    {
        String str = data;
        for (int i = 0; i < length - data.length(); i++)
            str += " ";
        return str;
    }
}
