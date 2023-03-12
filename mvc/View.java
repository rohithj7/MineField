package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/* 3/11/2023: Nathan Duong
 */

public class View extends JPanel implements PropertyChangeListener {

    private Model model;

    public View(Model m) {
        model = m;
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model newModel) {
        model.removePropertyChangeListener(this);
        model = newModel;
        model.addPropertyChangeListener(this);
        model.initSupport();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
