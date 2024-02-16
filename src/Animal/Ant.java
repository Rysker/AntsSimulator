package Animal;


import Tuple.Tuple;

import java.awt.*;

public class Ant extends AAnimal
{
    private int posX;
    private int posY;

    private int lastPosX;
    private int lastPosY;

    public Ant(Tuple<Integer, Integer> pos)
    {
        this.lastPosition = null;
        this.posX = pos.getFirst();
        this.posY = pos.getSecond();
    }

    public Tuple<Integer, Integer> getPosition()
    {
        return new Tuple<>(posX, posY);
    }

    public void moveUp()
    {
        this.posY -= 1;
    }

    public void moveDown()
    {
        this.posY += 1;
    }

    public void moveLeft()
    {
        this.posX -= 1;
    }

    public void moveRight()
    {
        this.posX += 1;
    }

    public Tuple<Integer, Integer> getLastPosition()
    {
        return new Tuple<>(lastPosX, lastPosY);
    }

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
