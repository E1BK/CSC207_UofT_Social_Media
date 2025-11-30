package view;

import app.GradientPanel;
import interface_adapter.logout.LogoutController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.make_post.PostViewData;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private ProfileViewModel profileViewModel;
    private String viewName = "profile";

    // Controllers
    private ProfileController profileController;
    private LogoutController logoutController = null;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JLabel email;
    private final JLabel bio;
    //    private final JPanel postContainer;
    private final JPanel row1;
    private final JPanel row2;

    // Buttons
    private final JButton homeButton;
    private final JButton searchButton;
    private final JButton profileButton;
    private ArrayList<PostViewData> posts;

    public ProfileView(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        // Page Title
        JLabel name = new JLabel("ChatUofT > Profile");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Page Body
        final JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setMaximumSize(new Dimension(1080, 100));
        middlePanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Add username
        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        usernameInfo.setFont(new Font("Helvetica", Font.BOLD, 40));
        username = new JLabel();
        username.setFont(new Font("Helvetica", Font.BOLD, 40));
        JPanel usernameSpacer = new JPanel();
        usernameSpacer.setMinimumSize(new Dimension(100, 100));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        // Add ID
        final JPanel idPanel = new JPanel();
        final JLabel idInfo = new JLabel("Email: ");
        idInfo.setFont(new Font("Helvetica", Font.BOLD, 20));
        email = new JLabel("");
        email.setFont(new Font("Helvetica", Font.BOLD, 20));
        idPanel.add(idInfo);
        idPanel.add(email);

        // Add Bio
        final JPanel bioPanel = new JPanel();
        final JLabel bioInfo = new JLabel("Bio: ");
        bioInfo.setFont(new Font("Helvetica", Font.BOLD, 20));
        bio = new JLabel("");
        bio.setFont(new Font("Helvetica", Font.BOLD, 20));
        bioPanel.add(bioInfo);
        bioPanel.add(bio);

        // Add Posts
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        row1 = new JPanel();
        postsPanel.add(row1);
        row2 = new JPanel();
        postsPanel.add(row2);
        postsPanel.add(Box.createRigidArea(new Dimension(0, 0)));

        // Add to middle Panel
        middlePanel.add(usernamePanel);
        middlePanel.add(idPanel);
        middlePanel.add(bioPanel);
        middlePanel.add(postsPanel);

        // Page Navigation
        GradientPanel bottomPanel = new GradientPanel();
        homeButton = new JButton (ProfileViewModel.HOME_BUTTON_LABEL);
        homeButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        homeButton.setMargin(new Insets(10, 20, 10, 20));
        searchButton = new JButton (ProfileViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        searchButton.setMargin(new Insets(10, 20, 10, 20));
        profileButton = new JButton (ProfileViewModel.PROFILE_BUTTON_LABEL);
        profileButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        profileButton.setMargin(new Insets(10, 20, 10, 20));
        bottomPanel.add(homeButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(profileButton);
        bottomPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        // Creates Frame
        this.setLayout( new BorderLayout() );

        this.add(topPanel,  BorderLayout.NORTH);
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.add(middlePanel);
        bodyPanel.add(postsPanel);
        this.add(bodyPanel,  BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Adds functionality to the buttons
        homeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        profileController.switchToLandingView();
                    }
                }
        );

        homeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        profileController.switchToPostView();
                    }
                }
        );

        searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        profileController.switchToSearchView();
                    }
                }
        );

        profileButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        profileController.switchToMyProfileView();
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().contains("View")) {
            for (int i = 0; i < posts.size(); i++) {
                if (e.getActionCommand().contains (STR."\{i}")) {
                    System.out.println(e.getActionCommand());
                    //profileController.switchToCurrentPost((int) posts.get(i).get(profileViewModel.ID));
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final ProfileState state = (ProfileState) e.getNewValue();
            username.setText(state.getUsername());
            bio.setText(state.getBio());
            email.setText(state.getEmail());

            int row1Count = row1.getComponentCount();
            int row2Count = row2.getComponentCount();
            for (int i = 0; i < row1Count; i++) { row1.remove(0); }
            for (int i = 0; i < row2Count; i++) { row2.remove(0); }

            if (!Objects.equals(username.getText(), "")) {
                addPosts(state.getPosts());
            }
        }
    }

    private void addPosts(ArrayList<PostViewData> posts) {
        int row1Size = 3;
        int row2Size = 3;

        if (posts.size() < 3) { row1Size = posts.size(); }
        if (posts.size() < 6) { row2Size = posts.size() - 3;}

        for (int i = 1; i <= row1Size; i++) {
            System.out.println(posts.size());
            System.out.println(posts.size()-i);
            PostPanel post = new  PostPanel(posts.get(posts.size()-i));
            row1.add(post.panel);
        }

        for  (int i = 1; i <= row2Size; i++) {
            PostPanel post = new  PostPanel(posts.get(posts.size()-i - 3));
            row2.add(post.panel);
        }

        this.posts = posts;
    }

    public String getViewName() { return viewName; }

    public void setProfileController(ProfileController controller) { this.profileController = controller; }
}
