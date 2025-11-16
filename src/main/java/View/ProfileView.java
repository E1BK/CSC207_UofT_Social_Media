// Julian
package view;

import interface_adapter.make_post.PostController;
import interface_adapter.profile.ProfileController;
import interface_adapter.user_search.SearchController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for when a user is looking at someone's profile
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private String viewName = "profile";
    private ProfileController profileController;
    private SearchController searchController;
    private PostController postController;

    // Labels
    private final JLabel username;
    private final JLabel bio;

    // Buttons
    private final JButton back;
    private final JButton postsButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public ProfileView() {
        // Add page title
        final JPanel title = new JPanel();
        final JLabel titleInfo = new JLabel("Profiles");
        back = new JButton ("Back");
        title.add(back);
        title.add(titleInfo);

        // Add user
        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        username = new JLabel("SorEgo");
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        // Add bio
        final JPanel bioPanel = new JPanel();
        final JLabel bioInfo = new JLabel("Bio:");
        bio = new JLabel("Feeling SorE");
        bioPanel.add(bioInfo);
        bioPanel.add(bio);

        // Add buttons
        final JPanel buttons = new JPanel();
        postsButton = new JButton ("Posts");
        searchButton = new JButton ("Search");
        profileButton = new JButton ("Profile");
        buttons.add(postsButton);
        buttons.add(searchButton);
        buttons.add(profileButton);

        back.addActionListener(this);

        postsButton.addActionListener(this);
        searchButton.addActionListener(this);
        profileButton.addActionListener(this);

        // Creates frame
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

        this.add(title);
        this.add(usernamePanel);
        this.add(bioPanel);
        this.add(buttons);
    }

    public static void main(String[] args) {
        // For testing
        JFrame frame = new JFrame("Profile View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new ProfileView());

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getSource().equals(postsButton)) { postController.execute(); }
        else if (e.getSource().equals(searchButton)){ searchController.execute(); }
        else if (e.getSource().equals(profileButton)){ profileController.execute(); }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // to implement
    }

    public String getViewName() {
        return viewName;
    }
}
