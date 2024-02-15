package Structure;

import java.awt.*;

public class Nest extends AStructure
{
    private int collectedFood=0;

    public Nest()
    {
        this.isDestructible = false;
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
