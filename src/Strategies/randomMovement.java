package Strategies;

import Animal.Ant;
import World.Block;

import java.util.List;

public class randomMovement implements Strategy{
    @Override
    public void moveAnts(List<Ant> ants, Block[][] blocks) {

        for(Ant ant : ants)
        {
           int random_number = (int) (Math.random() * 4);

            switch (random_number) {
                case 0 -> // UP
                        ant.moveUp();
                case 1 -> // RIGHT
                        ant.moveRight();
                case 2 -> // DOWN
                        ant.moveDown();
                case 3 -> // LEFT
                        ant.moveLeft();
                default -> {
                }
            }
        }
    }
}
