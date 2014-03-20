/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package knit.app;

import components.fabric.Fabric;
import components.fabric.FabricTable;
import components.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adriano HRL
 * @version 12/20/2013 09:30 am
 */
public class KnitApp 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Fabric fabric = new Fabric("Jacar");
        FabricTable table = new FabricTable(fabric);
        System.out.println(table);
        /*
        String name = "Jacar1";
        String obs = "Tem desenho na frente e atrás!!!";
        Collection coll = new Collection("Winter 2014");
        if (!coll.isRegistered())
            coll.add();
        Responsible resp = new Responsible("AdrianoHRL");
        if (!resp.isRegistered())
            resp.add();
        Machine mach = new Machine("Coppo 7", 87.4);
        if (!mach.isRegistered())
            mach.add();
        Date date = new Date(92, 8, 4, 12, 3, 57);
        
        ThreadType thrd1 = new ThreadType("Lurex");
        if (!thrd1.isRegistered())
            thrd1.add();
        ThreadType thrd2 = new ThreadType("Cotton");
        if (!thrd2.isRegistered())
            thrd2.add();
        ThreadType thrd3 = new ThreadType("Mousse");
        if (!thrd3.isRegistered())
            thrd3.add();
        ArrayList<ThreadType> thrdObjs = new ArrayList<ThreadType>();
        thrdObjs.add(thrd1);
        thrdObjs.add(thrd2);
        thrdObjs.add(thrd3);
        ThreadTypes thrds = new ThreadTypes(thrdObjs);
        
        Variant var1 = new Variant("Colorido");
        if (!var1.isRegistered())
            var1.add();
        Variant var2 = new Variant("Escuro");
        if (!var2.isRegistered())
            var2.add();
        ArrayList<Variant> varObjs = new ArrayList<Variant>();
        varObjs.add(var1);
        varObjs.add(var2);
        Variants vars = new Variants(varObjs);
        
        Colors[] colors = new Colors[thrds.size()];
        ArrayList<Color> colorObjs = Colors.getAllRegistereds();
        ArrayList<Color> color1Objs = new ArrayList<Color>();
        color1Objs.add(colorObjs.get(0));
        color1Objs.add(colorObjs.get(1));
        colors[0] = new Colors(color1Objs);
        ArrayList<Color> color2Objs = new ArrayList<Color>();
        color2Objs.add(colorObjs.get(2));
        color2Objs.add(colorObjs.get(3));
        colors[1] = new Colors(color2Objs);
        ArrayList<Color> color3Objs = new ArrayList<Color>();
        color3Objs.add(colorObjs.get(4));
        color3Objs.add(colorObjs.get(5));
        colors[2] = new Colors(color3Objs);
        
        ArrayList<Machine> machObjs = Machines.getAllRegistereds();
        Machines machs = new Machines(machObjs);
        
        Double[][] coneWeights = new Double[thrds.size()][];
        for (int i = 0; i < coneWeights.length; i++)
        {
            coneWeights[i] = new Double[ConeResults.NUMBER_OF_MEASURES];
            for (int j = 0; j < coneWeights[i].length; j++)
                coneWeights[i][j] = 1000.0 - 30 * i - 50 * j;
        }
        
        Double[] elapsedTimes = {10.0, 10.4, 9.8};
        
        Double[] fabricWeights = {100.0, 104.3, 107.89};
                
        Double[] fabricHeights = {.7, .49, .67};
        
        Double[] fabricWidths = {.475, .538, .4278};
                    
        Fabric f = new Fabric(name, obs, coll, resp, mach, date, 
            thrds, vars, colors, machs, coneWeights, 
            elapsedTimes, fabricWeights, fabricHeights, fabricWidths);
        if (!f.isRegistered())
            f.add();
        /**
        // TODO code application logic here
        String str = "Total number of Registered Colors: " + Colors.getTotalNumberOfRegistereds() + "!";
        System.out.println(str);
        ArrayList<Collection> objs = Collections.getAllRegistereds();
        Collections colls = new Collections(objs);
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");
        Collection obj = new Collection("Spring 2014");
        try
        {
            obj.add();
        }
        catch (UpException e)
        {
            System.out.println(e);
        }
        objs = Collections.getAllRegistereds();
        colls = new Collections(objs);
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");
        obj = new Collection("Winter 2013");
        try
        {
            obj.delete();
        }
        catch (UpException e)
        {
            System.out.println(e);
        }
        try
        {
            obj.add();
        }
        catch (UpException e)
        {
            System.out.println(e);
        }
        objs = Collections.getAllRegistereds();
        colls = new Collections(objs);
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");
        try
        {
            obj.updateName("2015 Summer");
        }
        catch (UpException e)
        {
            System.out.println(e);
        }
        objs = Collections.getAllRegistereds();
        colls = new Collections(objs);  
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");      
        try
        {
            obj.delete();
        }
        catch (UpException e)
        {
            System.out.println(e);
        }
        objs = Collections.getAllRegistereds();
        colls = new Collections(objs);  
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");   
        objs = Collections.getAllDeleteds();
        colls = new Collections(objs);  
        System.out.println("\r\n" + colls);
        System.out.println("\r\n********************************************\r\n");   
        * */
    }
}
