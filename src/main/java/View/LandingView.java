// hasan

package View;

import interface_adapter.landing.LandingViewModel;

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

        final JPanel buttons = new JPanel();
        JButton me = new JButton("Me");
        buttons.add(me);

        me.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(me)) {
//                            final LoginState currentState = loginViewModel.getState();
//
//                            loginController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
                            System.out.println("CLICKED 'ME'!");
                        }
                    }
                }
        );
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
