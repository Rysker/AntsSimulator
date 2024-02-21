package World;

import Animal.Ant;
import Structure.Nest;
import Structure.Wall;
import DataTypes.Tuple;

import java.util.ArrayList;
import java.util.List;

public class World
{
    public static int SIZE;
    private Block[][] blocks;

    public static List<Ant> ANTS;

    public World(int size)
    {
        SIZE = size;
        ANTS = new ArrayList<>();
        this.blocks = new Block[SIZE][SIZE];
        initializeWorld();
    }

    private void initializeWorld()
    {
        for(int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                blocks[i][j] = new Block(i, j);

                // Nest
                if(i >= SIZE/2-25 && i <= SIZE/2+25 && j >= SIZE/2-25 && j <= SIZE/2+25)
                    blocks[i][j].setStructure(Nest.getInstance());

                // Walls on simulation edges
                if(i == SIZE-1 || i == 0 || j == 0 || j ==SIZE-1)
                    blocks[i][j].setStructure(new Wall(false));

//                //Setting pheromones for testing purposes
//                if(i == 0 && j <= 400)
//                    blocks[i][j].setPheromone();
//                if(j == 400 && i <= 400)
//                    blocks[i][j].setPheromone();
            }
        }
    }

    public static void spawnAnt()
    {
        Tuple<Integer, Integer>[] possiblePosition = new Tuple[4];
        possiblePosition[0] = new Tuple<>(SIZE/2-30, SIZE/2);
        possiblePosition[1] = new Tuple<>(SIZE/2+30, SIZE/2);
        possiblePosition[2] = new Tuple<>(SIZE/2, SIZE/2+30);
        possiblePosition[3] = new Tuple<>(SIZE/2, SIZE/2-30);


        int randomNumber = (int) (Math.random() * possiblePosition.length);

        System.out.println(randomNumber);
        for(int i = 0; i < 10; i ++)
            ANTS.add(new Ant(possiblePosition[randomNumber]));
    }

    public Block[][] getBlocks()
    {
        return this.blocks;
    }

    public void updatePheromones(ArrayList<Tuple<Integer, Integer>> positions) {
        for (Ant ant : ANTS)
        {
            Tuple<Integer, Integer> pos = ant.getPosition();
            this.blocks[pos.getFirst()][pos.getSecond()].updatePheromone(ant.getFood());
        }
//        for(Tuple<Integer, Integer> pos: positions)
//            this.blocks[pos.getFirst()][pos.getSecond()].updatePheromone();
        for(int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                this.blocks[i][j].weakenPheromone();

    }

}
