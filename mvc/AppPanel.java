package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener

{
    private AppFactory appFactory;
    private controlPanel controlPanel;
    private View view;
    private boolean saved;
    private String fName;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        Model m = appFactory.makeModel();
        view = appFactory.makeView(m);
        controlPanel = new controlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(500, 500));
        this.add(view);
        view.setPreferredSize(new Dimension(500,500));

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(1000, 560);
        frame.setVisible(true);
    }

    public AppPanel() {
        Model m = appFactory.makeModel();
        view = appFactory.makeView(m);
        controlPanel = new controlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(500, 500));
        this.add(view);
        view.setPreferredSize(new Dimension(500,500));

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(1000, 560);
        frame.setVisible(true);
    }



    public void display() {
        repaint();
    }

    public class controlPanel extends JPanel {

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
        JMenu helpMenu = Utilities.makeMenu("Help", appFactory.getHelp(), this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}