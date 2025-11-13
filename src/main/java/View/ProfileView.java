package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    // Labels
    private final JLabel username;
    private final JLabel bio;

    // Buttons
    private final JButton back;
    private final JButton postsButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public ProfileView() {
        final JPanel title = new JPanel();
        final JLabel titleInfo = new JLabel("Profiles");
        back = new JButton ("Back");
        title.add(back);
        title.add(titleInfo);

        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        username = new JLabel("SorEgo");
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        final JPanel bioPanel = new JPanel();
        final JLabel bioInfo = new JLabel("Bio:");
        bio = new JLabel("Feeling SorE");
        bioPanel.add(bioInfo);
        bioPanel.add(bio);

        final JPanel buttons = new JPanel();
        postsButton = new JButton ("Posts");
        searchButton = new JButton ("Search");
        profileButton = new JButton ("Profile");
        buttons.add(postsButton);
        buttons.add(searchButton);
        buttons.add(profileButton);

        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

        add(title);
        add(usernamePanel);
        add(bioPanel);
        add(buttons);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Profile View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new ProfileView());

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // to implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // to implement
    }
}
