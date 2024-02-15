package Structure;

import java.awt.*;

public class Nest extends AStructure
{
    private int collectedFood = 0;
    private static Nest instance = null;

    private Nest()
    {
        this.isDestructible = false;
    }

    public static Nest getInstance()
    {
        if(instance == null)
            instance = new Nest();
        return instance;
    }

    @Override
    public String getClassName()
    {
        return "Nest";
    }

    @Override
    public Color getColor()
    {
        return new Color(125, 9, 143);
    }
}
