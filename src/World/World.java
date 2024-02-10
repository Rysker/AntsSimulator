package World;

public class World
{
    private Block[][] blocks;

    public World(int size)
    {
        this.blocks = new Block[size][size];
        initializeWorld(size);
    }

    private void initializeWorld(int size)
    {
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                blocks[i][j] = new Block(i, j);
    }

    public Block[][] getBlocks()
    {
        return this.blocks;
    }
}
