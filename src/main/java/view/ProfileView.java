// Julian
package view;

import interface_adapter.landing.LandingViewModel;
import interface_adapter.landing.MakePostController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
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
    private ProfileViewModel profileViewModel;
    private String viewName = "profile";
    private ProfileController profileController;
    private SearchController searchController;
    private MakePostController makePostController;

    // Labels
    private final JLabel username;
    private final JLabel bio;

    // Buttons
    private final JButton back;
    private final JButton postsButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public ProfileView(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        profileViewModel.addPropertyChangeListener(this);

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

        // Adds functionality to the buttons
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        profileController.switchToLandingView();
                    }
                }
        );

        postsButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileController.switchToPostView();
                }
            }
        );

        searchButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileController.switchToSearchView();
                }
            }
        );

        profileButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileController.switchToMyProfileView();
                }
            }
        );

        // Creates frame
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

        this.add(title);
        this.add(usernamePanel);
        this.add(bioPanel);
        this.add(buttons);
    }

//    public static void main(String[] args) {
//        // For testing
//        JFrame frame = new JFrame("Profile View");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        frame.add(new ProfileView(new ProfileViewModel()));
//
//        frame.pack();
//        frame.setVisible(true);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getSource().equals(postsButton)) {  }
        else if (e.getSource().equals(searchButton)) {
            makePostController.switchToPeopleView();
            }
        //else if (e.getSource().equals(profileButton)){ profileController.execute(); }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // to implement
    }

    public String getViewName() {
        return viewName;
    }

    public void setProfileController(ProfileController controller) { this.profileController = controller; }
}

