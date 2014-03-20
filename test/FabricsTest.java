import components.fabric.*;
import components.collection.Collection;
import components.responsible.Responsible;
import components.thread_type.ThreadTypes;
import components.variant.Variant;
import components.variant.Variants;
import components.color.Color;
import components.color.Colors;
import components.machine.Machine;
import components.machine.Machines;
import java.util.Date;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 03/14/2014 3:06 pm
 */
public class FabricsTest 
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        String f0Name = "Jacar Xadrez";
        String f0Obs = "Esse tecido parece um tabuleiro de xadrez.";
        Collection c0 = new Collection(163);
        Responsible r0 = new Responsible(6);
        Machine m0 = new Machine(7);
        Date date0 = new Date();
        ThreadTypes ts0 = ThreadTypes.getAllRegistereds();
        int numOfThreadTypes = ts0.size();
        Variants vs0 = new Variants();
        for (int v = 5; v < 9; v++)
            vs0.add(new Variant(v));
        int numOfVariants = vs0.size();
        Colors[] cs0 = new Colors[ts0.size()];
        for (int i = 0; i < numOfThreadTypes; i++)
        {
            cs0[i] = new Colors();
            for (int j = 0; j < numOfVariants; j++)
                cs0[i].add(new Color(i * numOfThreadTypes + j + 24));
        }       
        Machines ms0 = Machines.getAllRegistereds();
        Double[][] cWeights = new Double[numOfThreadTypes][];
        for (int i = 0; i < cWeights.length; i++)
        {
            cWeights[i] = new Double[4];
            for (int j = 0; j < cWeights[i].length; j++)
                cWeights[i][j] = 2500 * (1 - .1 * i - .05 * j);
        }
        Double[] times = {10.4, 10.5, 10.3};
        Double[] fWeights = {.350, .345, .352};
        Double[] heights = {.6, .55, .58};
        Double[] widths = {.3, .28, .27};
        Fabric f0 = new Fabric(f0Name, f0Obs, c0, r0, m0, date0, ts0, vs0, cs0, ms0, cWeights, times, fWeights, heights, widths);
        
        /*******************************************************************/
        if (!f0.isRegistered())
            f0.add();
        else
            System.out.println("This fabric has already been registered!!!");
        
        /*********************************************************************/
        if (!f0.isDeleted())
            f0.delete();
        else
            System.out.println("This fabric has already been deleted!!!");
        
        /********************************************************************/
        Fabric f1 = null;
        try
        {
            f1 = new Fabric("Novo Jacar Xadrez");
        }
        catch (FabricInException fie)
        {
            System.out.println(fie);
            if (f0.isRegistered())
                 f0.updateName("Novo Jacar Xadrez");
        }
        
        /*********************************************************************/
        System.out.println("F0: \r\n" + f0);
    }
}
