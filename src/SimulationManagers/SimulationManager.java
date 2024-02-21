package SimulationManagers;

import Animal.Ant;
import Strategies.Strategy;
import Strategies.generalStrategy;
import Structure.Nest;
import World.*;
import ViewManagement.*;
import DataTypes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        this.strategy = new generalStrategy();

        // Timer
        timer = new Timer(5, this);
        timer.start();
    }

    public void nextTick()
    {
        //Add moving all ants
        ArrayList<Tuple<Integer, Integer>> last_pos = this.getLastPositions();
        this.world.updatePheromones(last_pos);
        strategy.moveAnts(World.ANTS, world.getBlocks());
        this.redrawSimulation();
        System.out.println(Nest.getInstance().getCollectedFood());
    }

    private void redrawSimulation()
    {
        SimulationPanel tmp = LayoutPanel.getInstance().getSimulationPanel();
        tmp.draw();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        nextTick();
    }

    public ArrayList<Tuple<Integer, Integer>> getLastPositions()
    {
        ArrayList<Tuple<Integer, Integer>> result = new ArrayList<>();
        for(Ant ant: this.world.ANTS)
            result.add(ant.getPosition());
        return result;
    }



}
