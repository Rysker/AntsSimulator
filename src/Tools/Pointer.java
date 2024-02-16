package Tools;

import java.awt.*;
import java.util.ArrayList;

import Drawable.IDrawable;
import DataTypes.Tuple;

public class Pointer implements ITool
{
    @Override
    public Tuple<ArrayList<Point>, IDrawable> handle(int x, int y)
    {
        return null;
    }

    public String identifyTool()
    {
        return "Pointer";
    }

}
