package view;

import app.GradientPanel;
import interface_adapter.logout.LogoutController;
import interface_adapter.my_profile.MyProfileController;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.my_profile_change_password.MyProfileChangePasswordController;
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

public class MyProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private MyProfileViewModel myProfileViewModel;
    private String viewName = "my profile";

    // Controllers
    private MyProfileController myProfileController;
    private MyProfileChangePasswordController changePasswordController = null;
    private LogoutController logoutController = null;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JLabel email;
//    private final JPanel postContainer;
    private final JPanel row1;
    private final JPanel row2;

    // Buttons
    private final JButton bioConfirm;
    private final JButton passwordConfirm;
    private final JButton homeButton;
    private final JButton searchButton;
    private final JButton profileButton;
    private final JButton logoutButton;
    private ArrayList<PostViewData> posts;

    public MyProfileView(MyProfileViewModel myProfileViewModel) {
        this.myProfileViewModel = myProfileViewModel;
        this.myProfileViewModel.addPropertyChangeListener(this);

        // Page Title
        JLabel name = new JLabel("ChatUofT > My Profile");
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
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        logoutButton.setMargin(new Insets(10, 30, 10, 30));
        JPanel usernameSpacer = new JPanel();
        usernameSpacer.setMinimumSize(new Dimension(100, 100));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);
        usernamePanel.add(logoutButton);


        // Add ID
        final JPanel idPanel = new JPanel();
        final JLabel idInfo = new JLabel("Email: ");
        idInfo.setFont(new Font("Helvetica", Font.BOLD, 20));
        email = new JLabel("");
        email.setFont(new Font("Helvetica", Font.BOLD, 20));
        idPanel.add(idInfo);
        idPanel.add(email);

        // Add Bio Editor
        final JPanel bioPanel = new JPanel();
        bioConfirm = new JButton("Confirm");
        bioConfirm.setFont(new Font("Helvetica", Font.BOLD, 20));
        final JLabel bioLabel = new JLabel("Bio: ");
        bioLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        final LabelTextPanel bioInfo = new LabelTextPanel(bioLabel,
                                                          bioInputField,
                                                          bioConfirm);
        bioPanel.add(bioInfo);

        // Add Change Password
        final JPanel passwordPanel = new  JPanel();
        final JLabel passwordLabel = new JLabel("New Password: ");
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        passwordConfirm = new JButton("Confirm");
        passwordConfirm.setFont(new Font("Helvetica", Font.BOLD, 20));
        final LabelTextPanel passwordInfo = new LabelTextPanel(passwordLabel,
                                                               passwordInputField,
                                                               passwordConfirm);
        passwordPanel.add(passwordInfo);

        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        row1 = new JPanel();
        postsPanel.add(row1);
        row2 = new JPanel();
        postsPanel.add(row2);
        postsPanel.add(Box.createRigidArea(new Dimension(0, 0)));

        JPanel bioPasswordPanel = new JPanel();
        bioPasswordPanel.setLayout(new BoxLayout(bioPasswordPanel, BoxLayout.X_AXIS));
        bioPasswordPanel.add(bioPanel);
        bioPasswordPanel.add(passwordPanel);


        // Add to middle Panel
        middlePanel.add(usernamePanel);
        middlePanel.add(idPanel);
        middlePanel.add(bioPasswordPanel);
        middlePanel.add(postsPanel);

        // Page Navigation
        GradientPanel bottomPanel = new GradientPanel();
        homeButton = new JButton (MyProfileViewModel.HOME_BUTTON_LABEL);
        homeButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        homeButton.setMargin(new Insets(10, 20, 10, 20));
        searchButton = new JButton (MyProfileViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        searchButton.setMargin(new Insets(10, 20, 10, 20));
        profileButton = new JButton (MyProfileViewModel.PROFILE_BUTTON_LABEL);
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
                        myProfileController.switchToLandingView();
                    }
                }
        );

        bioConfirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final MyProfileState state = myProfileViewModel.getState();
                        state.setBio(bioInputField.getText());

                        System.out.println(e.getActionCommand());
                        changePasswordController.execute(
                                state.getUsername(),
                                passwordInputField.getText(),
                                state.getBio(),
                                state.getEmail(),
                                state.getName(),
                                "bio"
                        );
                    }
                }
        );

        passwordConfirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final MyProfileState state = myProfileViewModel.getState();
                        state.setPassword(passwordInputField.getText());

                        System.out.println(e.getActionCommand());
                        changePasswordController.execute(
                                state.getUsername(),
                                passwordInputField.getText(),
                                state.getBio(),
                                state.getEmail(),
                                state.getName(),
                                "password"
                        );
                    }
                }
        );

        homeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        myProfileController.switchToPostView();
                    }
                }
        );

        searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        myProfileController.switchToSearchView();
                    }
                }
        );

        profileButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        myProfileController.switchToMyProfileView();
                    }
                }
        );

        logoutButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(e.getActionCommand());
                        logoutController.execute();
                        myProfileController.switchToLoginSignupView();
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
                    //myProfileController.switchToCurrentPost((int) posts.get(i).get(myProfileViewModel.ID));
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final MyProfileState state = myProfileViewModel.getState();
            username.setText(state.getUsername());
            passwordInputField.setText(state.getPassword());
            bioInputField.setText(state.getBio());
            email.setText(state.getEmail());

            int row1Count = row1.getComponentCount();
            int row2Count = row2.getComponentCount();
            for (int i = 0; i < row1Count; i++) { row1.remove(0); }
            for (int i = 0; i < row2Count; i++) { row2.remove(0); }

            if (!Objects.equals(username.getText(), "")) {
                myProfileController.execute(state.getUsername(), state.getPassword(), state.getEmail(), state.getBio());
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
            PostPanel post = new  PostPanel(posts.get(posts.size() - i));
            row1.add(post.panel);
        }

        for  (int i = 1; i <= row2Size; i++) {
            PostPanel post = new  PostPanel(posts.get(posts.size() - i - 3));
            row2.add(post.panel);
        }

        this.posts = posts;
    }

    public String getViewName() { return viewName; }

    public void setMyProfileController(MyProfileController controller) { this.myProfileController = controller; }
    public void setChangePasswordController(MyProfileChangePasswordController controller) {
        this.changePasswordController = controller;
    }
    public void setLogoutController(LogoutController controller) { this.logoutController = controller; }
}
