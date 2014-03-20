/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adriano HRL
 * 
 */
public class Database
{
    private static Connection conn;
    private static Statement stat;
    private static ResultSet res;
    private static ResultSetMetaData metaData;
    
    private static void open()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Database.conn = DriverManager.getConnection("jdbc:mysql://localhost/knitapp", "adrianohrl", "ahrl");
            Database.stat = Database.conn.createStatement();
            //System.out.println("KnitApp Database is connected!!!");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("KnitApp Database is not connected!!!\r\n...\r\n" + e + "\r\n\r\n");
        }
    }
    
    private static void close()
    {
        try
        {
            if (Database.res != null)
                Database.res.close();
            if (Database.stat != null)
                Database.stat.close();
            if (Database.conn != null)
                Database.conn.close();
            Database.res = null;
            Database.stat = null;
            Database.conn = null;
            //System.out.println("KnitApp Database is disconnected!!!");
        }
        catch (SQLException e)
        {
            System.out.println("KnitApp Database is not disconnected!!!\r\n" + e);
        }
    }

    /**
     * Insertion, Update and Deletion
     * 
     * @param sql
     */
    public static void update(String sql)
    {
        try 
        {
            Database.open();
            Database.stat.executeUpdate(sql);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Failed Execution!!!\r\n" + e);
        }
    }
    
    public static Object getObjectElement(String sql)
    {
        Object element = 0;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getObject(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static boolean getBooleanElement(String sql)
    {
        boolean element = false;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getBoolean(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static long getLongElement(String sql)
    {
        long element = 0;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getLong(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static Double getDoubleElement(String sql)
    {
        Double element = 0.0;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getDouble(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static String getStringElement(String sql)
    {
        String element = null;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getString(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static Date getDateElement(String sql)
    {
        Date element = null;
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            if (Database.res.next())
                element = Database.res.getDate(1);
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        return element;
    }
    
    public static Object[] getObjectArray(String sql)
    {
        ArrayList list = new ArrayList();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            while (Database.res.next())
                list.add(Database.res.getObject(1));
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
       Object[] array = new Object[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public static long[] getLongArray(String sql)
    {
        ArrayList<Long> list = new ArrayList<Long>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            while (Database.res.next())
                list.add(Database.res.getLong(1));
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        long[] array = new long[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public static Double[] getDoubleArray(String sql)
    {
        ArrayList<Double> list = new ArrayList<Double>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            while (Database.res.next())
                list.add(Database.res.getDouble(1));
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        Double[] array = new Double[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public static String[] getStringArray(String sql)
    {
        ArrayList<String> list = new ArrayList<String>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            while (Database.res.next())
                list.add(Database.res.getString(1));
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public static Date[] getDateArray(String sql)
    {
        ArrayList<Date> list = new ArrayList<Date>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            while (Database.res.next())
                list.add(Database.res.getDate(1));
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        Date[] array = new Date[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public static Object[][] getObjectTable(String sql)
    {
        ArrayList<Object []> arrayList = new ArrayList<Object[]>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            Database.metaData = Database.res.getMetaData();
            while (Database.res.next())
            {
                Object[] col = new Object[Database.metaData.getColumnCount()];
                for (int c = 0; c < col.length; c++)
                    col[c] = Database.res.getObject(c + 1);
                arrayList.add(col);
            }
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        Object[][] table = new Object[arrayList.size()][];
        for (int i = 0; i < table.length; i++)
        {
            Object[] col = arrayList.get(i);
            table[i] = new Object[col.length];
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = col[j];
        }
        return table;
    }
    
    public static long[][] getLongTable(String sql)
    {
        ArrayList<Long[]> arrayList = new ArrayList<Long[]>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            Database.metaData = Database.res.getMetaData();
            while (Database.res.next())
            {
                Long[] col = new Long[Database.metaData.getColumnCount()];
                for (int c = 0; c < col.length; c++)
                    col[c] = Database.res.getLong(c + 1);
                arrayList.add(col);
            }
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        long[][] table = new long[arrayList.size()][];
        for (int i = 0; i < table.length; i++)
        {
            Long[] col = arrayList.get(i);
            table[i] = new long[col.length];
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = col[j];
        }
        return table;
    }
    
    public static Double[][] getDoubleTable(String sql)
    {
        ArrayList<Double[]> arrayList = new ArrayList<Double[]>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            Database.metaData = Database.res.getMetaData();
            while (Database.res.next())
            {
                Double[] col = new Double[Database.metaData.getColumnCount()];
                for (int c = 0; c < col.length; c++)
                    col[c] = Database.res.getDouble(c + 1);
                arrayList.add(col);
            }
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        Double[][] table = new Double[arrayList.size()][];
        for (int i = 0; i < table.length; i++)
        {
            Double[] col = arrayList.get(i);
            table[i] = new Double[col.length];
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = col[j];
        }
        return table;
    }
    
    public static String[][] getStringTable(String sql)
    {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            Database.metaData = Database.res.getMetaData();
            while (Database.res.next())
            {
                String[] col = new String[Database.metaData.getColumnCount()];
                for (int c = 0; c < col.length; c++)
                    col[c] = Database.res.getString(c + 1);
                arrayList.add(col);
            }
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        String[][] table = new String[arrayList.size()][];
        for (int i = 0; i < table.length; i++)
        {
            String[] col = arrayList.get(i);
            table[i] = new String[col.length];
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = col[j];
        }
        return table;
    }
    
    public static Date[][] getDateTable(String sql)
    {
        ArrayList<Date[]> arrayList = new ArrayList<Date[]>();
        try 
        {
            Database.open();
            Database.res = Database.stat.executeQuery(sql);
            Database.metaData = Database.res.getMetaData();
            while (Database.res.next())
            {
                Date[] col = new Date[Database.metaData.getColumnCount()];
                for (int c = 0; c < col.length; c++)
                    col[c] = Database.res.getDate(c + 1);
                arrayList.add(col);
            }
            Database.close();
        }
        catch (SQLException e)
        {
            System.out.println("Selection Failed!!!\r\n" + e);
        }
        Date[][] table = new Date[arrayList.size()][];
        for (int i = 0; i < table.length; i++)
        {
            Date[] col = arrayList.get(i);
            table[i] = new Date[col.length];
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = col[j];
        }
        return table;
    }
}
