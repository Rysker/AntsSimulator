package Strategies;

import Animal.Ant;
import Tuple.Tuple;
import World.Block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Pheromones determine choice of next move
public class byPheromone implements Strategy {

    public void moveAnts(List<Ant> ants, Block[][] blocks) {
        Random random = new Random();

        for(Ant ant : ants)
        {
            Tuple<Integer, Integer> pos = ant.getPosition();
            double[] directionPoints = new double[4]; // [UP, RIGHT, DOWN, LEFT]
            double pointsSum;
            double[] directionChances = new double[4];
            double randomNumber = random.nextDouble(); // returns [0,1)

            directionPoints[0] = blocks[pos.getFirst()][pos.getSecond()-1].getPoints();
            directionPoints[1] = blocks[pos.getFirst()+1][pos.getSecond()].getPoints();
            directionPoints[2] = blocks[pos.getFirst()][pos.getSecond()+1].getPoints();
            directionPoints[3] = blocks[pos.getFirst()-1][pos.getSecond()].getPoints();

            pointsSum = Arrays.stream(directionPoints).sum();

            // Chances for each direction
            for(int i=0; i < directionPoints.length; ++i)
                directionChances[i] = directionPoints[i] / pointsSum;

            for(int i=0; i< directionChances.length; ++i)
            {
                if(randomNumber < directionChances[i])
                {
                    makeMoveFromDirection(ant, i);
                    break;
                }
            }



        }
    }

    private void makeMoveFromDirection(Ant ant, int direction)
    {
        switch (direction)
        {
            case 0 -> ant.moveUp();
            case 1 -> ant.moveRight();
            case 2 -> ant.moveDown();
            case 3 -> ant.moveLeft();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

}
