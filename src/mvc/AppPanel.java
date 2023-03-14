package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements ActionListener

{
    private AppFactory appFactory;
    protected controlPanel controls;
    private View view;
    private boolean saved;
    private String fName;
    private Model model;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        Model m = appFactory.makeModel();
        view = appFactory.makeView(m);
        controls = new controlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        controls.setPreferredSize(new Dimension(500, 500));
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
        Command cmmdEditCommand = appFactory.makeEditCommand(model, cmmd, null);
        System.out.println(cmmdEditCommand);
        cmmdEditCommand.execute();

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
                        fName = fName;
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
                    Utilities.inform("Nathan Duong Turtle Graphics, 2023. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = appFactory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

}