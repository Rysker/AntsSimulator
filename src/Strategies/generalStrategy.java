package Strategies;

import Animal.Ant;
import DataTypes.Direction;
import DataTypes.Tuple;
import World.Block;
import Structure.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class generalStrategy implements Strategy
{
    @Override
    public void moveAnts(List<Ant> ants, Block[][] blocks)
    {
        for(Ant ant : ants)
        {
            Tuple<Integer, Integer> pos = ant.getPosition();
            Direction direction = ant.getDirection();
            ArrayList<Block> new_blocks = blocksFromDirection(pos, direction, blocks);
            if(!(foodNearby(new_blocks, ant) && ant.getFood() == false))
            {
                Tuple<ArrayList<Double>, Integer> result = this.checkBlocks(new_blocks);
                ArrayList<Double> chances = result.getFirst();
                int sum = result.getSecond();
                double random_chances = 0.05;
                if(ant.getFood() == true)
                    random_chances = 0.01;
                if(this.firstDecision() <= random_chances || sum == 0)
                    this.randomChoice(ant, new_blocks);
                else
                    this.pheromoneChoice(ant, new_blocks, chances);
            }
        }
    }

    private ArrayList<Block> blocksFromDirection(Tuple<Integer, Integer> pos, Direction direction, Block[][] blocks)
    {
        Point point = new Point(pos.getFirst(), pos.getSecond());
        ArrayList<Block> result = new ArrayList<>();
        switch(direction)
        {
            case UP -> {
                result.add(blocks[point.x][point.y - 1]);
                result.add(blocks[point.x + 1][point.y]);
                result.add(blocks[point.x - 1][point.y]);
            }

            case RIGHT -> {
                result.add(blocks[point.x + 1][point.y]);
                result.add(blocks[point.x][point.y - 1]);
                result.add(blocks[point.x][point.y + 1]);
            }

            case LEFT -> {
                result.add(blocks[point.x - 1][point.y]);
                result.add(blocks[point.x][point.y - 1]);
                result.add(blocks[point.x][point.y + 1]);
            }

            case DOWN -> {
                result.add(blocks[point.x][point.y + 1]);
                result.add(blocks[point.x + 1][point.y]);
                result.add(blocks[point.x - 1][point.y]);
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        return result;
    }

    private double firstDecision()
    {
        Random random = new Random();
        return random.nextDouble(); // returns [0,1)
    }

    private Tuple<ArrayList<Double>, Integer> checkBlocks(ArrayList<Block> blocks)
    {
        ArrayList<Double> powerFractions = new ArrayList<>();
        int sum = 0;
        for (Block block : blocks)
            sum += block.getPheromone();

        if(sum == 0)
            return new Tuple<>(powerFractions, sum);

        for (Block block : blocks)
        {
            double fraction = (double) block.getPheromone() / sum;
            powerFractions.add(fraction);
        }
        return new Tuple<>(powerFractions, sum);
    }

    private void randomChoice(Ant ant, ArrayList<Block> blocks)
    {
        Block block;
        int random_number = (int) (Math.random() * 2) + 1;
        int choice = (int) (Math.random() * 10);
        if(choice <= 7)
            block = blocks.get(0);
        else
            block = blocks.get(random_number);
        if(!isPassable(block.getStructure(), ant))
        {
            Point point = new Point(block.getX(), block.getY());
            Direction new_direction = directionFromChange(ant.getPosition(), new Tuple<>(block.getX(), block.getY()));
            ant.changePosition(point.x, point.y);
            ant.changeDirection(new_direction);
        }
    }

    private void pheromoneChoice(Ant ant, ArrayList<Block> blocks, ArrayList<Double> chances)
    {
        Random random = new Random();
        double randomValue = random.nextDouble(); // Random number between 0.0 and 1.0

        double cumulativeProbability = 0.0;
        for (int i = 0; i < chances.size(); i++)
        {
            cumulativeProbability += chances.get(i);
            if (randomValue <= cumulativeProbability)
            {
                Block block = blocks.get(i);
                if(!isPassable(block.getStructure(), ant))
                {
                    Point point = new Point(block.getX(), block.getY());
                    Direction new_direction = directionFromChange(ant.getPosition(), new Tuple<>(block.getX(), block.getY()));
                    ant.changePosition(point.x, point.y);
                    ant.changeDirection(new_direction);
                }
                break;
            }
        }
    }

    private Direction directionFromChange(Tuple<Integer, Integer> old_pos, Tuple<Integer, Integer> new_pos)
    {
        Point old = new Point(old_pos.getFirst(), old_pos.getSecond());
        Point new_one = new Point(new_pos.getFirst(), new_pos.getSecond());
        int x_change = (old.x - new_one.x);
        int y_change = (old.y - new_one.y);
        if(x_change == -1)
            return Direction.RIGHT;
        else if (x_change == 1)
            return Direction.LEFT;
        else if(y_change == -1)
            return Direction.DOWN;
        else if (y_change == 1)
            return Direction.UP;
        else
            return Direction.UP;
    }


    private boolean isPassable(AStructure structure, Ant ant)
    {
        if (structure instanceof Food)
        {
            ant.turnDirection();
            ant.addFood();
            return true;
        }
        else if(structure instanceof Wall)
        {
            ant.turnDirection();
            return true;
        }
        else if(structure instanceof Nest)
        {
            if(ant.getFood() == true)
                ((Nest) structure).addFood();
            ant.removeFood();
            ant.turnDirection();
            return true;
        }
        return false;
    }


    private boolean foodNearby(ArrayList<Block> blocks, Ant ant)
    {
        for(Block block: blocks)
        {
            if (block.getStructure() instanceof Food)
            {
                ant.turnDirection();
                ant.addFood();
                return true;
            }
        }
        return false;
    }
}
