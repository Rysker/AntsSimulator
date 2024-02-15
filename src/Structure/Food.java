package Structure;

import java.awt.*;

public class Food extends AStructure
{

    public Food()
    {
        this.isDestructible = false;
    }

    @Override
    public String getClassName()
    {
        return "Food";
    }

    @Override
    public Color getColor() {
        return new Color(50, 255, 50);
    }
}
