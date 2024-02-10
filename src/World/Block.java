package World;

import Animal.*;
import Structure.*;

import java.awt.*;
import java.util.ArrayList;

public class Block
{
    private int x;
    private int y;
    private AStructure structure;
    private Pheromone pheromone;
    private ArrayList<AAnimal> animals;

    public Block(int x, int y)
    {
        this.pheromone = null;
        if(x > 100 &&  x < 200 && y > 100 && y < 200)
            this.structure = new Wall();
        else
            this.structure = new Empty();
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Color getColor()
    {
        return this.structure.getColor();
    }

}
