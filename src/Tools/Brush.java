package Tools;

import java.awt.*;
import java.util.ArrayList;

import Drawable.IDrawable;
import Structure.*;
import Animal.*;
import Tuple.Tuple;
import World.*;

public class Brush implements ITool
{
    private int radius;
    private IDrawable thing;
    @Override
    public Tuple<ArrayList<Point>, IDrawable> handle(int x, int y)
    {
        ArrayList<Point> points = new ArrayList<>();

        int startX = Math.max(0, x - radius);
        int startY = Math.max(0, y - radius);
        int endX = Math.min(World.SIZE - 1, x + radius);
        int endY = Math.min(World.SIZE - 1, y + radius);

        for (int i = startX; i <= endX; i++)
            for (int j = startY; j <= endY; j++)
                points.add(new Point(i, j));

        return new Tuple<>(points, thing);
    }

    public Brush(String className)
    {
        this.thing = this.brushCreator(className);
        this.radius = 10;
    }

    private IDrawable brushCreator(String className)
    {
        switch(className)
        {
            case "Wall":
                return new Wall();
            case "Ant":
                return new Ant();
            case "Rubber":
                return new Empty();
            default:
                throw new IllegalArgumentException("Unknown brush type: " + className);
        }
    }

    public String identifyTool()
    {
        return "Brush:" + this.thing.getClassName();
    }
}
