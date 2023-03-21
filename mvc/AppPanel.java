package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Author: David Song
 * Date: 03/11/2023
 *
 * Description: set up the whole window that is seen when the program is run.
 *
 * Nathan Duong
 * 3/14/2023
 * Updated to accommodate for specific edit commands from AppFactory, Removed old turtle graphics cases
 *
 *
 */

public abstract class AppPanel extends JPanel implements ActionListener, PropertyChangeListener

{
    private AppFactory appFactory;
    protected controlPanel controlPanel;
    private View view;
    private String fName;
    protected Model model;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        model = appFactory.makeModel();
        view = appFactory.makeView(model);
        controlPanel = new controlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(500, 500));
        this.add(view);
        view.setPreferredSize(new Dimension(500,500));
    }

    public void display() {
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}

    protected class controlPanel extends JPanel {

        public controlPanel() {
            setBackground(Color.PINK);
        }

    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appFactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"Help", "About"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmmd = e.getActionCommand();

        try {
            switch (cmmd) {

                case "Save": {
                    if (fName == null) {
                        fName = Utilities.getFileName((String) null, false);
                    }
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Save As": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, false);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.fName = fName;
                        model = (Model) is.readObject();
                        model.initSupport();
                        view.setModel(model);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        fName = null;
                        model = appFactory.makeModel();
                        view.setModel(model);
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(appFactory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = appFactory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    for (String s: appFactory.getEditCommands()) {
                        if(cmmd == s) {
                            appFactory.makeEditCommand(model, cmmd, null).execute();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

}


