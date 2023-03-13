package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel  extends JPanel implements ActionListener
{
    private Model model;
    private ControlPanel controls;
    private View view;
    private boolean saved;
    private String fName;

    public AppPanel() {
        model = new Model();
        view = new View(model);
        controls = new ControlPanel();
        fName = "";
        saved = false;
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);
        //his.add(new JButton("foo"));

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("model Graphics");
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "East", "South", "West", "Clear", "Pen", "Color", "steps"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //blank :)
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setLayout(new GridLayout(4, 2));

            setBackground(Color.PINK);
            JPanel p = new JPanel();


            JButton northwest = new JButton("NW");
            Font newButtonFont=new Font("Times New Roman",northwest.getFont().getStyle(),20);
            Dimension d = new Dimension(100, 25);
            northwest.setFont(newButtonFont);
            northwest.setPreferredSize(d);
            northwest.addActionListener(AppPanel.this);
            p.add(northwest);
            add(p);

            JButton north = new JButton("N");
            north.setFont(newButtonFont);
            north.setPreferredSize(d);
            north.addActionListener(AppPanel.this);
            p.add(north);
            add(p);

            JButton northeast = new JButton("NE");
            northeast.setFont(newButtonFont);
            northeast.setPreferredSize(d);
            northeast.addActionListener(AppPanel.this);
            p.add(northeast);
            add(p);

            JButton west = new JButton("W");
            west.setFont(newButtonFont);
            west.setPreferredSize(d);
            west.addActionListener(AppPanel.this);
            p.add(west);
            add(p);

            JButton east = new JButton("E");
            east.setFont(newButtonFont);
            east.setPreferredSize(d);
            east.addActionListener(AppPanel.this);
            p.add(east);
            add(p);

            JButton southwest = new JButton("SW");
            southwest.setFont(newButtonFont);
            southwest.setPreferredSize(d);
            southwest.addActionListener(AppPanel.this);
            p.add(southwest);
            add(p);

            JButton south = new JButton("S");
            south.setFont(newButtonFont);
            south.setPreferredSize(d);
            south.addActionListener(AppPanel.this);
            p.add(south);
            add(p);

            JButton southeast = new JButton("SE");
            southeast.setFont(newButtonFont);
            southeast.setPreferredSize(d);
            southeast.addActionListener(AppPanel.this);
            p.add(southeast);
            add(p);
        }
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}