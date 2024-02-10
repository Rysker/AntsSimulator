package ViewManagement;
import javax.swing.*;
import java.awt.*;

public class LayoutPanel extends JPanel
{
    private static LayoutPanel instance;
    private final SimulationPanel simulationPanel;
    private final OptionsMenu optionsMenu;
    private LayoutPanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.optionsMenu = new OptionsMenu();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        add(optionsMenu, c);

        this.simulationPanel = new SimulationPanel();
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        add(simulationPanel, c);
    }

    public static LayoutPanel getInstance()
    {
        if (instance == null)
            instance = new LayoutPanel();
        return instance;
    }

    public SimulationPanel getSimulationPanel()
    {
        return this.simulationPanel;
    }

    public OptionsMenu getOptionsMenu()
    {
        return this.optionsMenu;
    }

}
