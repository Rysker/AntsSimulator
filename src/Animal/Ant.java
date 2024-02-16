package Animal;

import java.awt.*;

import DataTypes.*;


public class Ant extends AAnimal
{
    private int posX;
    private int posY;
    private Direction direction;

    public Ant(Tuple<Integer, Integer> pos)
    {
        this.lastPosition = null;
        this.posX = pos.getFirst();
        this.posY = pos.getSecond();
        this.direction = randomDirection();
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

    public Direction getDirection()
    {
        return this.direction;
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

    public void changePosition(int x, int y)
    {
        this.posX = x;
        this.posY = y;
    }

    private Direction randomDirection()
    {
        int random_number = (int) (Math.random() * 4);

        switch (random_number)
        {
            case 0 -> { // UP
                return Direction.UP;
            }
            case 1 -> { // RIGHT
                return Direction.RIGHT;
            }
            case 2 -> { // DOWN
                return Direction.DOWN;
            }
            case 3 -> { // LEFT
                return Direction.LEFT;
            }
            default -> {
            }
        }
        return Direction.UP;
    }

    public void changeDirection(Direction direction)
    {
        this.direction = direction;
    }

}
