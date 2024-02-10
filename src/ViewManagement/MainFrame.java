package ViewManagement;

import javax.swing.*;
import World.*;
public class MainFrame extends JFrame
{
    private LayoutPanel layout;
    public MainFrame(World world)
    {
        setTitle("Ants Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = LayoutPanel.getInstance();
        this.layout.getSimulationPanel().setWorld(world);
        getContentPane().add(this.layout);
        pack();
        setLocationRelativeTo(null);

    }

    @Override
    public void setVisible(boolean b)
    {
        super.setVisible(b);
    }
}