package Structure;

import java.awt.*;

public class Wall extends AStructure
{
    public Wall(boolean is)
    {
        this.isDestructible = is;
    }
    @Override
    public Color getColor()
    {
        return new Color(66, 66, 66);
    }

    @Override
    public String getClassName()
    {
        return "Wall";
    }
}
