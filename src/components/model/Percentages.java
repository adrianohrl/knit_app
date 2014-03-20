package components.model;

import components.size.*;
import java.util.ArrayList;

/**
 *
 * @author Adriano Henrique Rossette Leite
 * @version 12/29/2013 01:50 pm
 */
public class Percentages 
{
    private Sizes sizes;
    double[] values;
    
    /**
     *
     * @param sizes
     * @param values
     * @throws ModelInException
     */
    public Percentages(Sizes sizes, double[] values) throws ModelInException
    {
        if (sizes == null)
            throw new ModelInException("Sizes must not be null!!!");
        if (values.length != sizes.size())
            throw new ModelInException("Percentages length and Sizes size must be equal!!!");
        this.sizes = Sizes.getPatternSizes();
        this.values = new double[this.sizes.size()];
        ArrayList<Size> objs = sizes.getObjs();
        for (int i = 0; i < sizes.size(); i++)
            for (int j = 0; j < objs.size(); j++)
            {
                Size obj = objs.get(j);
                if (obj.equals(this.sizes.get(i)))
                {
                    this.values[i] = values[i];
                    objs.remove(j);
                    break;
                }
            }
    }
    
    /**
     *
     * @param values
     * @throws ModelInException
     */
    public Percentages(double[] values) throws ModelInException
    {
        this.sizes = Sizes.getPatternSizes();
        if (values.length != this.sizes.size())
            throw new ModelInException("Percentages length and Sizes size must be equal!!!");
        this.values = values;
    }
    
    /**
     *
     * @param model
     */
    public Percentages(Model model) throws ModelInException
    {
        if (model == null)
            throw new ModelInException("Model must not be null!!!");
        Percentages values = model.getPercentages();
        this.sizes = values.getSizes();
        this.values = values.getValues();
    }
    
    /**
     *
     * @return
     */
    public Sizes getSizes()
    {
        return this.sizes;
    }
    
    /**
     *
     * @return
     */
    public double[] getValues()
    {
        return this.values;
    }
    
    public double getPerc(SizeLabels label)
    {
        return values[label.ordinal()];
    }
    
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
    public boolean equals(Object obj)
    {
        if (obj instanceof Percentages)
            return this.equals((Percentages) obj);
        return false;
    }
    
    public boolean equals(Percentages obj)
    {
        Sizes sizes = obj.getSizes();
        double[] values = obj.getValues();
        if (values.length != this.values.length)
            return false;
        for (int i = 0; i < values.length; i++)
            if (values[i] != this.values[i])
                return false;
        return sizes.equals(this.sizes);
    }
}
