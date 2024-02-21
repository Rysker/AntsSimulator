package DataTypes;

public enum Direction
{
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction getOpposite()
    {
        return switch(this) {
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case LEFT -> RIGHT;
            case DOWN -> UP;
        };
    }
}
