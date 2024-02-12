package ViewManagement;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Animal.*;
import Structure.*;
import Tools.*;
import Tuple.Tuple;
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
        setPreferredSize(new Dimension(400, 400));
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
        if(preview != null)
            drawPreview(g);
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
                int x = (int) (j * scaleX);
                int y = (int) (i * scaleY);
                int blockWidth = (int) Math.max(1, scaleX);
                int blockHeight = (int) Math.max(1, scaleY);

                g.setColor(block.getColor());
                g.fillRect(x, y, blockWidth, blockHeight);
            }
        }
    }

    private void drawPreview(Graphics g)
    {
        ArrayList<Point> points = this.preview.getFirst();
        Color color = this.preview.getSecond().getColor();
        for (int i = 0; i < points.size(); i++)
        {
            Point point = points.get(i);
            g.setColor(color);
            int x = point.x;
            int y = point.y;
            int blockWidth = (int) Math.max(1, scaleX);
            int blockHeight = (int) Math.max(1, scaleY);
            g.fillRect(x, y, blockWidth,blockHeight);
        }
    }

    public void handleTool(int x, int y)
    {
        Tuple<ArrayList<Point>, IDrawable> result = this.tool.handle(x, y);
        Block[][] blocks = this.world.getBlocks();
        String className = result.getSecond().getClassName();
        ArrayList<Point> points = result.getFirst();

        double invScaleX = 1.0 / scaleX;
        double invScaleY = 1.0 / scaleY;

        for (Point point : points)
        {
            int blockX = (int) (point.x * invScaleX);
            int blockY = (int) (point.y * invScaleY);
            blocks[blockY][blockX].place(this.drawableFromString(className));
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
                return new Wall();
            case "Ant":
                return new Ant();
            case "Empty":
                return new Empty();
            default:
                throw new IllegalArgumentException("Unknown object: " + name);
        }
    }
}