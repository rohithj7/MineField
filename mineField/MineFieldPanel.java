package mineField;
import java.awt.*;
import javax.swing.*;
import mvc.*;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
public class MineFieldPanel extends AppPanel {
    private JButton nw;
    private JButton n;
    private JButton ne;
    private JButton w;
    private JButton e;
    private JButton sw;
    private JButton s;
    private JButton se;
    public MineFieldPanel(AppFactory factory){
        super(factory);
        MineField field = (MineField)model;
        field.addPropertyChangeListener(this);
        controlPanel.setLayout(new GridLayout(4,2));
        nw = new JButton("NW");
        nw.addActionListener(this);
        controlPanel.add(nw);
        n = new JButton("N");
        n.addActionListener(this);
        controlPanel.add(n);
        ne = new JButton("NE");
        ne.addActionListener(this);
        controlPanel.add(ne);
        w = new JButton("W");
        w.addActionListener(this);
        controlPanel.add(w);
        e = new JButton("E");
        e.addActionListener(this);
        controlPanel.add(e);
        sw = new JButton("SW");
        sw.addActionListener(this);
        controlPanel.add(sw);
        s = new JButton("S");
        s.addActionListener(this);
        controlPanel.add(s);
        se = new JButton("SE");
        se.addActionListener(this);
        controlPanel.add(se);

        //MineFieldView view = new MineFieldView((MineField) model);
    }
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        MineField field = (MineField)model;
        if (e.getSource() == nw) {
            field.setDirection(Direction.NW);
        }
        if (e.getSource() == n) {
            field.setDirection(Direction.N);
        }
        if (e.getSource() == ne) {
            field.setDirection(Direction.NE);
        }
        if (e.getSource() == w) {
            field.setDirection(Direction.W);
        }
        if (e.getSource() == e) {
            field.setDirection(Direction.E);
        }
        if (e.getSource() == sw) {
            field.setDirection(Direction.SW);
        }
        if (e.getSource() == s) {
            field.setDirection(Direction.S);
        }
        if (e.getSource() == se) {
            field.setDirection(Direction.SE);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        repaint();
        /*
        if (evt.getPropertyName().equals("mineField")) {
            repaint();
        }
    } */

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }


}