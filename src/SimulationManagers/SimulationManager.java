package SimulationManagers;

import Strategies.Strategy;
import Strategies.byPheromone;
import Strategies.randomMovement;
import World.*;
import ViewManagement.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationManager implements ActionListener
{
    private final int size = 800;
    private World world;
    private MainFrame window;
    private Timer timer;

    private final Strategy strategy;

    public SimulationManager()
    {
        this.world = new World(this.size);
        this.window = new MainFrame(this.world);
        this.window.setVisible(true);

        // Strategy
        this.strategy = new randomMovement();

        // Timer
        timer = new Timer(500, this);
        timer.start();
    }

    public void nextTick()
    {
        //Add moving all ants
        strategy.moveAnts(World.ANTS, world.getBlocks());
        this.redrawSimulation();
    }

    private void redrawSimulation()
    {
        SimulationPanel tmp = LayoutPanel.getInstance().getSimulationPanel();
        tmp.draw();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextTick();
    }
}
