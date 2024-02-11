package Mouse;

import java.awt.event.*;

import ViewManagement.SimulationPanel;

public class Mouse extends MouseAdapter
{
    private SimulationPanel simulationPanel;
    private boolean enable = true;
    private boolean pressed = false;
    private int mouseX;
    private int mouseY;

    public Mouse(SimulationPanel simulationPanel)
    {
        this.simulationPanel = simulationPanel;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        this.pressed = true;
        invokeToolHandle(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        invokeToolHandle(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.pressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.paint(this.mouseX, this.mouseY);
    }

    public void enable()
    {
        this.enable = true;
    }

    private void invokeToolHandle(int x, int y)
    {
        if (enable && pressed)
           this.simulationPanel.handleTool(x, y);
    }

    public void paint(int x, int y)
    {
        if (enable && !pressed)
            this.simulationPanel.paintPreview(x, y);
    }
}
