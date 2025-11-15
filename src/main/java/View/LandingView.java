// hasan

package View;

import interface_adapter.LandingViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LandingView extends JPanel implements ActionListener, PropertyChangeListener {

    // the LandingViewModel attribute gives the data to our LandingView object.
    private LandingViewModel landingViewModel;
    private final String viewName = "landing";

    public LandingView(LandingViewModel landingViewModel) {
        final JLabel welcome = new JLabel("Welcome, User!");
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
