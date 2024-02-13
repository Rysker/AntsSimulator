package Animal;

import Drawable.IDrawable;
import Tuple.Tuple;

public abstract class AAnimal implements IDrawable
{
    protected Tuple<Integer, Integer> position;
    protected Tuple<Integer, Integer> lastPosition;
}
