package utilities.table;

import java.util.ArrayList;

/**
 *
 * @author Adriano HRL
 * @param <ROW>
 * @param <COL>
 * @param <CELL>
 */
public interface TableInterface <ROW, COL, CELL>
{
    /**
     * 
     * @param row
     * @throws TableException 
     */
    public void addRow(ROW row) throws TableException;
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    public void addRow(ROW row, ArrayList<CELL> cells) throws TableException;
    
    /**
     * 
     * @param row
     * @param cells
     * @throws TableException 
     */
    public void addRow(ROW row, CELL[] cells) throws TableException;
    
    /**
     * 
     * @param rowIndex
     * @return
     * @throws TableException 
     */
    public ROW getRowHeader(int rowIndex) throws TableException;
    
    /**
     * 
     * @return 
     */
    public int getRowCount();
    
    /**
     * 
     * @return 
     */
    public ArrayList<ROW> getRowHeaders();
    
    /**
     * 
     * @param rowIndex
     * @param row
     * @throws TableException 
     */
    public void setRowHeader(int rowIndex, ROW row) throws TableException;
    
    /**
     * 
     * @param column
     * @throws TableException 
     */
    public void addColumn(COL column) throws TableException;
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    public void addColumn(COL column, ArrayList<CELL> cells) throws TableException;
    
    /**
     * 
     * @param column
     * @param cells
     * @throws TableException 
     */
    public void addColumn(COL column, CELL[] cells) throws TableException;
    
    /**
     * 
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    public COL getColumnHeader(int columnIndex) throws TableException;
    
    /**
     * 
     * @return 
     */
    public int getColumnCount();
    
    /**
     * 
     * @return 
     */
    public ArrayList<COL> getColumnHeaders();
    
    /**
     * 
     * @param columnIndex
     * @param column
     * @throws TableException 
     */
    public void setColumnHeader(int columnIndex, COL column) throws TableException;
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @return
     * @throws TableException 
     */
    public CELL getCellElement(int rowIndex, int columnIndex) throws TableException;
    
    /**
     * 
     * @return 
     */
    public ArrayList<ArrayList<CELL>> getCellElements();
    
    /**
     * 
     * @param rowIndex
     * @return 
     */
    public ArrayList<CELL> getRowCellElements(int rowIndex);
    
    /**
     * 
     * @param columnIndex
     * @return 
     */
    public ArrayList<CELL> getColumnCellElements(int columnIndex);
    
    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @param cell 
     */
    public void setCell(int rowIndex, int columnIndex, CELL cell);
}
