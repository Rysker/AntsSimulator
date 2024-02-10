package ViewManagement;

import java.awt.*;
import javax.swing.*;
import World.*;

public class SimulationPanel extends JPanel
{
    World world;
    public SimulationPanel()
    {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
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

        Block[][] blocks = world.getBlocks();
        int width = getWidth();
        int height = getHeight();

        int size = blocks.length;

        double scaleX = (double) width / size;
        double scaleY = (double) height / size;

        System.out.println(scaleX);
        System.out.println(scaleY);

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
}