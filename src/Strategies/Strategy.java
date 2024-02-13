package Strategies;

import Animal.Ant;
import World.Block;


public interface Strategy {

     // Makes move for each ant in simulation
     void moveAnts(Ant[] ants, Block[][] blocks);

}
