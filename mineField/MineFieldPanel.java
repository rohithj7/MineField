package mineField;
import java.awt.*;
import javax.swing.*;
import mvc.*;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

/*
    3/20/23: Nathan Duong
        Properly formatted all direction buttons into controlPanel.
        Removed actionPerformed (redundant).

    03/21/23: David Song
        Reformatting adding the buttons. create new JPanel --> add button to JPanel
        --> add JPanel to controlPanel
 */

public class MineFieldPanel extends AppPanel {
    private final JButton nw;
    private final JButton n;
    private final JButton ne;
    private final JButton w;
    private final JButton e;
    private final JButton sw;
    private final JButton s;
    private final JButton se;

    public MineFieldPanel(AppFactory factory) {
        super(factory);
        MineField field = (MineField) model;
        controlPanel.setLayout(new GridLayout(4, 2));

        JPanel nowe = new JPanel();
        nw = new JButton("NW");
        nw.addActionListener(this);
        nowe.add(nw);
        controlPanel.add(nowe);

        JPanel no = new JPanel();
        n = new JButton("N");
        n.addActionListener(this);
        no.add(n);
        controlPanel.add(no);

        JPanel noea = new JPanel();
        ne = new JButton("NE");
        ne.addActionListener(this);
        noea.add(ne);
        controlPanel.add(noea);

        JPanel we = new JPanel();
        w = new JButton("W");
        w.addActionListener(this);
        we.add(w);
        controlPanel.add(we);

        JPanel ea = new JPanel();
        e = new JButton("E");
        e.addActionListener(this);
        ea.add(e);
        controlPanel.add(ea);

        JPanel sowe = new JPanel();
        sw = new JButton("SW");
        sw.addActionListener(this);
        sowe.add(sw);
        controlPanel.add(sowe);

        JPanel so = new JPanel();
        s = new JButton("S");
        s.addActionListener(this);
        so.add(s);
        controlPanel.add(so);

        JPanel soea = new JPanel();
        se = new JButton("SE");
        se.addActionListener(this);
        soea.add(se);
        controlPanel.add(soea);
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }


}