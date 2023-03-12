package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener {

    protected Model model;
    protected AppFactory factory;
    protected ControlPanel controlPanel;

    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent evt) {
        Command command = factory.makeCommand(model, evt.getActionCommand());
        if (command != null)
            command.execute();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    protected class ControlPanel extends JPanel {

        }
    }
}