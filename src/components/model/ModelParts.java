package components.model;

import components.fabric.Fabrics;
import java.util.ArrayList;
import java.util.Iterator;
import utilities.Database;
import utilities.list.*;
        
/**
 *
 * @author Adriano Henrique Rossette Leite
 * @since 03/26/2014 11:10pm
 * @version 1.0.0003
 */
public class ModelParts extends ModelComponent implements Iterable<ModelPart>, List<ModelPart>
{
    private ArrayList<ModelPart> parts;
    
    /**
     * 
     * @param mod
     * @param fabrs
     * @param names
     * @param programs
     * @param consumptions
     * @param units
     * @param obss
     * @throws ModelInException 
     */
    public ModelParts(Model mod, Fabrics fabrs, String[] names, String[] programs, 
            double[] consumptions, String[] units, String[] obss) throws ModelInException
    {
        super(mod);
        this.parts = this.getParts(mod, fabrs, names, programs, consumptions, units, obss);
    }
    
    /**
     * 
     * @param mod
     * @param parts
     * @throws ModelInException 
     */
    public ModelParts(Model mod, ArrayList<ModelPart> parts) throws ModelInException
    {
        super(mod);
        if (parts == null)
            throw new ModelInException("Model Parts must not be null!!!");
        if (parts.size() < 1)
            throw new ModelInException("Model must have at least one Model Part!!!");
        if (mod.getTable().getRowCount() == parts.size())
            throw new ModelInException("The number of model parts must be equals to the number of its fabrics!!!");
        this.parts = parts;
    }
    
    /**
     * 
     * @param mod
     * @param parts 
     */
    public ModelParts(Model mod, ModelPart[] parts) throws ModelInException
    {
        super(mod);
        if (parts == null)
            throw new ModelInException("Model Parts must not be null!!!");
        if (mod.getTable().getRowCount() == parts.length)
            throw new ModelInException("The number of model parts must be equals to the number of its fabrics!!!");
        this.parts = new ArrayList<ModelPart>();
        for (ModelPart part : parts)
            this.parts.add(part);
    }
    
    /**
     * 
     * @param mod 
     */
    public ModelParts(Model mod)
    {
        super(mod);
        this.parts = this.getParts(mod);
    }
    
    /**
     * 
     * @param mod
     * @return
     * @throws ModelInException 
     */
    private ArrayList<ModelPart> getParts(Model mod) throws ModelInException
    {
        if (mod == null)
            throw new ModelInException("Model must not be null!!!");
        if (!mod.isRegistered())
            throw new ModelInException("Model has not been registered yet!!!");
        String sql = "SELECT Id FROM " + this.getClassName() + "s WHERE ModId = " + mod.getID() + " ORDER BY Id";
        long[] ids = Database.getLongArray(sql);
        int numOfParts = ids.length;
        ArrayList<ModelPart> parts = new ArrayList<ModelPart>();
        for (int i = 0; i < numOfParts; i++)
        {
            ModelPart part = new ModelPart(mod, ids[i]);
            parts.add(part);
        }
        return parts;
    }
    
    /**
     * 
     * @param mod
     * @param fabrs
     * @param names
     * @param programs
     * @param consumptions
     * @param units
     * @param obss
     * @return
     * @throws ModelInException 
     */
    private ArrayList<ModelPart> getParts(Model mod, Fabrics fabrs, String[] names, String[] programs, 
            double[] consumptions, String[] units, String[] obss) throws ModelInException
    {
        if (fabrs == null)
            throw new ModelInException("Fabrics must not be null!!!");
        if (fabrs.size() < 1)
            throw new ModelInException("Model must have at least one Model Part!!!");
        int numOfParts = fabrs.size();
        if (mod.getTable().getRowCount() == numOfParts)
            throw new ModelInException("The number of model parts must be equals to the number of its fabrics!!!");
        if (names == null)
            throw new ModelInException("Names must not be null!!!");
        if (names.length != numOfParts) 
            throw new ModelInException("Names must have the same number of Fabrics!!!");
        if (programs == null)
            throw new ModelInException("Programs must not be null!!!");        
        if (programs.length != numOfParts)
            throw new ModelInException("Programs must have the same number of Fabrics!!!");
        if (consumptions == null)
            throw new ModelInException("Consumptions must not be null!!!");
        if (consumptions.length != numOfParts)
            throw new ModelInException("Consumptions must have the same number of Fabrics!!!");
        if (units == null)
            throw new ModelInException("Units must not be null!!!");
        if (units.length != numOfParts)
            throw new ModelInException("Units must have the same number of Fabrics!!!");
        if (obss == null)
            throw new ModelInException("Observations must not be null!!!");
        if (obss.length != numOfParts)
            throw new ModelInException("Observations must have the same number of Fabrics!!!");
        ArrayList<ModelPart> parts = new ArrayList<ModelPart>();
        for (int i = 0; i < numOfParts; i++)
        {
            ModelPart part = new ModelPart(mod, fabrs.get(i), names[i], programs[i], consumptions[i], units[i], obss[i]);
            parts.add(part);
        }
        return parts;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<ModelPart> getParts()
    {
        return this.parts;
    }
    
    /**
     * 
     */
    @Override
    public void register() throws ModelUpException
    {
        if (super.isRegistered())
            throw new ModelUpException("These model parts have already been registered!!!");
        for (ModelPart part : this)
            part.register();
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(ModelComponent obj)
    {
        return obj instanceof ModelParts && this.equals((ModelParts) obj);
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(ModelParts obj)
    {
        return this.parts.equals(obj.getParts());
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        String str = "";
        Iterator it = this.iterator();
        for (ModelPart part : this)
            str += part + "\r\n";
        return str;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getClassName()
    {
        return "ModelParts";
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Iterator<ModelPart> iterator()
    {
        return new ModelPartsIterator(this);
    }
    
    /**
     * 
     */
    @Override
    public void clear()
    {
        this.parts.clear();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int size()
    {
        return this.parts.size();
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return this.parts.isEmpty();
    }
    
    /**
     * 
     * @param index
     * @return
     */
    @Override
    public ModelPart get(int index) throws ListException
    {
        if (index < 0 || index >= this.size())
            throw new ListException("Invalid index!!!");
        return this.parts.get(index);
    }
    
    /**
     * 
     * @param part
     * @return
     * @throws ListException 
     */
    @Override
    public boolean contains(ModelPart part) throws ListException
    {
        if (part == null)
            throw new ListException("Part must not be null!!!");
        return this.parts.contains(part);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public ModelPart[] toArray()
    {
        ModelPart[] array = new ModelPart[this.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = this.get(i);
        return array;
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public ModelPart remove(int index) throws ListException
    {
        if (index < 0 || index >= this.size())
            throw new ListException("Invalid index!!!");
        return this.parts.remove(index);
    }
}
