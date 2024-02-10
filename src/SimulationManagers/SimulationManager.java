package SimulationManagers;

import World.*;
import ViewManagement.*;
public class SimulationManager
{
    private final int size = 1000;
    private World world;
    private MainFrame window;

    public SimulationManager()
    {
        this.world = new World(this.size);
        this.window = new MainFrame(this.world);
        this.window.setVisible(true);
    }

    public void nextTick()
    {
        //Add moving all ants
        this.redrawSimulation();
    }

    private void redrawSimulation()
    {
        SimulationPanel tmp = LayoutPanel.getInstance().getSimulationPanel();
        tmp.draw();
    }

}
