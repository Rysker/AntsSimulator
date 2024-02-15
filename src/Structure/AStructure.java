package Structure;

import java.awt.*;

import Drawable.IDrawable;

public abstract class AStructure implements IDrawable
{
    protected boolean isDestructible;
    abstract public Color getColor();

    public boolean isDestructible()
    {
        return isDestructible;
    }
}
