package Structure;

import java.awt.*;

public class Empty extends AStructure
{

    public Empty()
    {
        this.isDestructible = true;
    }

    @Override
    public Color getColor()
    {
        return new Color(0, 0, 0);
    }

    @Override
    public String getClassName()
    {
        return "Empty";
    }
}
