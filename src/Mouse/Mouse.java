package Mouse;

import java.awt.event.*;

import ViewManagement.SimulationPanel;

public class Mouse extends MouseAdapter
{
    private SimulationPanel simulationPanel;
    private boolean enable = true;
    private boolean pressed = false;

    public Mouse(SimulationPanel simulationPanel)
    {
        this.simulationPanel = simulationPanel;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(!this.pressed)
        {
            this.pressed = true;
            invokeToolHandle(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (pressed)
            invokeToolHandle(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.pressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (!pressed)
            this.simulationPanel.paintPreview(e.getX(), e.getY());
    }

    private void invokeToolHandle(int x, int y)
    {
        if (enable)
        {
            new Thread(() -> {
                simulationPanel.handleTool(x, y);
            }).start();
        }

    }

    private void delay()
    {
        try
        {
            Thread.sleep(5);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}