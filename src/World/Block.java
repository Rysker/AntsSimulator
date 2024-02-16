package World;

import Animal.*;
import Drawable.IDrawable;
import Structure.*;

import java.awt.*;
import java.util.ArrayList;

public class Block
{
    private int x;
    private int y;
    private AStructure structure;
    private int pheromone;
    private ArrayList<AAnimal> animals;

    public Block(int x, int y)
    {
        this.animals = new ArrayList<>();
        this.pheromone = 1;
        this.structure = new Empty();
        this.x = x;
        this.y = y;
    }

    public double getPoints()
    {
        if(this.structure instanceof Wall)
            return 0;
        else if(this.structure instanceof Food)
            return 100;
        else
            return pheromone;
    }

    public int getX()
    {
        return x;
    }

    public void place(IDrawable obj)
    {
        if(obj instanceof AAnimal)
        {
            if(structure != null)
                structure = new Empty();
            this.animals.add((AAnimal) obj);
        }
        else if (obj instanceof AStructure)
        {
            this.structure = (AStructure) obj;
            this.animals.clear();
        }

    }
    public int getY()
    {
        return y;
    }

    public AStructure getStructure()
    {
        return structure;
    }

    public void setStructure(AStructure struct)
    {
        this.structure = struct;
    }

    public Color getColor()
    {
        if(this.structure != null && this.structure instanceof Empty && this.animals.size() != 0)
            return this.animals.get(0).getColor();
        else
            return this.structure.getColor();
    }

}
