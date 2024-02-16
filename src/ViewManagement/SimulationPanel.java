package ViewManagement;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Animal.*;
import Structure.*;
import Tools.*;
import DataTypes.Tuple;
import World.*;
import Drawable.*;

public class SimulationPanel extends JPanel
{
    private World world;
    private Tuple<ArrayList<Point>, IDrawable> preview;
    private double scaleX;
    private double scaleY;
    public static ITool tool;
    public SimulationPanel()
    {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 800));
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        this.tool = new Pointer();
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
    public void draw()
    {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawGrid(g);
        drawAnts(g);
        if(preview != null)
            drawPreview(g);
    }

    private void drawAnts(Graphics g)
    {
        final int radius = 1;

        for(Ant ant : World.ANTS)
        {
            g.setColor(ant.getColor());
            g.fillRect(ant.getPosition().getFirst() , ant.getPosition().getSecond(), radius, radius);
        }
    }

    private void drawGrid(Graphics g)
    {
        Block[][] blocks = world.getBlocks();
        int width = getWidth();
        int height = getHeight();

        int size = blocks.length;

        scaleX = (double) width / size;
        scaleY = (double) height / size;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                Block block = blocks[i][j];
                g.setColor(block.getColor());
                g.fillRect(i, j, 1, 1);
            }
        }
    }

    private void drawPreview(Graphics g)
    {
        Block[][] blocks = this.world.getBlocks();
        Block block;
        AStructure block_structure;
        ArrayList<Point> points = this.preview.getFirst();
        Color color = this.preview.getSecond().getColor();
        for (Point point: points)
        {
            block = blocks[point.x][point.y];
            block_structure = block.getStructure();
            if(block_structure.isDestructible())
            {
                g.setColor(color);
                g.fillRect(point.x, point.y, 1,1);
            }
        }
    }

    public void handleTool(int x, int y)
    {
        Tuple<ArrayList<Point>, IDrawable> result = this.tool.handle(x, y);
        Block[][] blocks = this.world.getBlocks();
        Block block;
        AStructure block_structure;
        String className = result.getSecond().getClassName();
        ArrayList<Point> points = result.getFirst();

        for (Point point : points)
        {
            block = blocks[point.x][point.y];
            block_structure = block.getStructure();
            if(block_structure.isDestructible())
                blocks[point.x][point.y].place(this.drawableFromString(className));
        }
        repaint();
    }

    public void paintPreview(int x, int y)
    {
        Tuple<ArrayList<Point>, IDrawable> result = this.tool.handle(x, y);
        if(result == null)
            this.preview = null;
        else
            this.preview = result;
        repaint();
    }

    private IDrawable drawableFromString(String name)
    {
        switch(name)
        {
            case "Wall":
                return new Wall(true);
            case "Empty":
                return new Empty();
            case "Food":
                return new Food();
            default:
                throw new IllegalArgumentException("Unknown object: " + name);
        }
    }
}