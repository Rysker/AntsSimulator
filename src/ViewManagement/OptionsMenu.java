package ViewManagement;

import java.awt.*;
import javax.swing.*;

public class OptionsMenu extends JPanel
{
    public OptionsMenu()
    {
        setLayout(new GridLayout(3, 1));
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));
    }
}