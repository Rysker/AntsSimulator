package Tools;

import Drawable.IDrawable;
import Tuple.Tuple;

import java.awt.*;
import java.util.ArrayList;

public interface ITool
{
   Tuple<ArrayList<Point>, IDrawable> handle(int x, int y);
   String identifyTool();
}
