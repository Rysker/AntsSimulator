package Animal;

import java.awt.*;

public class Ant extends AAnimal
{
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
