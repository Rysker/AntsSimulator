package Animal;

import Drawable.IDrawable;
import DataTypes.Tuple;

public abstract class AAnimal implements IDrawable
{
    protected Tuple<Integer, Integer> position;
    protected Tuple<Integer, Integer> lastPosition;
}
