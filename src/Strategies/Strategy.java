package Strategies;

import Animal.Ant;
import World.Block;

import java.util.List;


public interface Strategy {

     // Makes move for each ant in simulation
     void moveAnts(List<Ant> ants, Block[][] blocks);

}
