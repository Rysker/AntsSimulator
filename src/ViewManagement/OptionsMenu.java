package ViewManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Tools.*;
public class OptionsMenu extends JPanel implements ActionListener
{
    private ButtonGroup buttonGroup;
    public OptionsMenu()
    {
        setLayout(new GridLayout(4, 1));

        buttonGroup = new ButtonGroup();

        JToggleButton wallButton = createToggleButton("Wall");
        JToggleButton antButton = createToggleButton("Ant");
        JToggleButton rubberButton = createToggleButton("Rubber");
        JToggleButton foodButton = createToggleButton("Food");

        buttonGroup.add(wallButton);
        buttonGroup.add(antButton);
        buttonGroup.add(foodButton);
        buttonGroup.add(rubberButton);


        add(wallButton);
        add(antButton);
        add(foodButton);
        add(rubberButton);

        SimulationPanel.tool = new Pointer();
    }

    private JToggleButton createToggleButton(String text)
    {
        JToggleButton button = new JToggleButton(text);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JToggleButton selectedButton = (JToggleButton) e.getSource();
        String buttonText = selectedButton.getText();
        String oldTool = SimulationPanel.tool.identifyTool();

        switch (buttonText)
        {
            case "Wall":
                SimulationPanel.tool = new Brush("Wall");
                break;
            case "Ant":
                SimulationPanel.tool = new Brush("Ant");
                break;
            case "Rubber":
                SimulationPanel.tool = new Brush("Rubber");
                break;
            case "Food":
                SimulationPanel.tool = new Brush("Food");
            default:
                break;
        }
        if(oldTool.equals(SimulationPanel.tool.identifyTool()))
        {
            selectedButton.setBackground(null);
            SimulationPanel.tool = new Pointer();
        }
        System.out.println("Current tool: " + SimulationPanel.tool.identifyTool());
    }
}