package Animal;


import Tuple.Tuple;

import java.awt.*;

public class Ant extends AAnimal
{

    public Ant()
    {
        this.lastPosition = null;
        this.position = null;
    }

    public Ant(Tuple<Integer, Integer> pos)
    {
        this.lastPosition = null;
        this.position = pos;
    }

    public Color getColor()
    {
        return new Color(157, 6, 6);
    }
    @Override
    public String getClassName()
    {
        return "Ant";
    }

}
