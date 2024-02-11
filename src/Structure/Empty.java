package Structure;

import java.awt.*;

public class Empty extends AStructure
{
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
