package MineField;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private final JLabel label;
    public Patch patch;

    public Cell() {
        setPreferredSize(new Dimension(40, 40)); // sets size of cell panel
        label = new JLabel("?");
        add(label);
    }

    public void reveal() {
        label.setText("" + patch); // adds label to cell panel
        setEnabled(false); // disables cell panel
        if (patch.occupied) {
            setBackground(Color.RED);
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
        } else if (patch.goal) {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.GREEN));
        } else {
            setBackground(Color.LIGHT_GRAY);
        }
    }

    public void setText(String text) {
        label.setText(text);
    }
}