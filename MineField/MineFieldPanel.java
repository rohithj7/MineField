package MineField;
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
        nowe.add(no);
        controlPanel.add(n);
        JPanel noea = new JPanel();
        ne = new JButton("NE");
        ne.addActionListener(this);
        nowe.add(noea);
        controlPanel.add(ne);
        JPanel we = new JPanel();
        w = new JButton("W");
        w.addActionListener(this);
        nowe.add(we);
        controlPanel.add(w);
        JPanel ea = new JPanel();
        e = new JButton("E");
        e.addActionListener(this);
        nowe.add(ea);
        controlPanel.add(e);
        JPanel sowe = new JPanel();
        sw = new JButton("SW");
        sw.addActionListener(this);
        nowe.add(sowe);
        controlPanel.add(sw);
        JPanel so = new JPanel();
        s = new JButton("S");
        s.addActionListener(this);
        nowe.add(so);
        controlPanel.add(s);
        JPanel soea = new JPanel();
        se = new JButton("SE");
        se.addActionListener(this);
        nowe.add(soea);
        controlPanel.add(se);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        MineField field = (MineField)model;
        if (e.getSource() == nw) {
            field.setDirection(MineField.Direction.NW);
        }
        if (e.getSource() == n) {
            field.setDirection(MineField.Direction.N);
        }
        if (e.getSource() == ne) {
            field.setDirection(MineField.Direction.NE);
        }
        if (e.getSource() == w) {
            field.setDirection(MineField.Direction.W);
        }
        if (e.getSource() == e) {
            field.setDirection(MineField.Direction.E);
        }
        if (e.getSource() == sw) {
            field.setDirection(MineField.Direction.SW);
        }
        if (e.getSource() == s) {
            field.setDirection(MineField.Direction.S);
        }
        if (e.getSource() == se) {
            field.setDirection(MineField.Direction.SE);
        }
    }
    
    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }


}
