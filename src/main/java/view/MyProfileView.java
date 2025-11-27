package view;

import app.GradientPanel;
import entity.Comment;
import entity.Post;
import entity.PostFactory;
import interface_adapter.my_profile.MyProfileController;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.my_profile_change_password.MyProfileChangePasswordController;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MyProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private MyProfileViewModel myProfileViewModel;
    private String viewName = "my profile";

    // Controllers
    private MyProfileController myProfileController;
    private MyProfileChangePasswordController changePasswordController = null;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JLabel email;

    // Buttons
//    private final JButton back;
    private final JButton bioConfirm;
    private final JButton passwordConfirm;
    private final JButton postButton;
    private final JButton searchButton;
    private final JButton profileButton;

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

        // Display Posts: postsPanel
        ArrayList<Post> allMyPosts = myProfileViewModel.getState().getPosts();
        ArrayList<Post> postsToDisplay = new ArrayList<>();

        if (allMyPosts.size() < 6) {
            PostFactory myPostFactory = new PostFactory();
            postsToDisplay.add(myPostFactory.create(myProfileViewModel.getState().getUsername(), 17, "Need help with calculus", "I finally understand derivatives after hours of practice!", "2025-11-18", new ArrayList<Comment>()));
            postsToDisplay.add(myPostFactory.create(myProfileViewModel.getState().getUsername(), 23, "Java project update", "Implemented the backend today—feels great!", "2025-11-18", new ArrayList<Comment>()));
            postsToDisplay.add(myPostFactory.create(myProfileViewModel.getState().getUsername(), 31, "Exam stress", "Can't believe how fast finals are approaching.", "2025-11-18", new ArrayList<Comment>()));
            postsToDisplay.add(myPostFactory.create(myProfileViewModel.getState().getUsername(), 56, "Cloud watching", "Did you know the average cloud weighs over a million pounds? It's all about density! Watching those massive, weightless-looking giants drift by is truly mind-boggling. #ScienceFacts #Nature", "2025-11-18", new ArrayList<Comment>()));
            postsToDisplay.add(myPostFactory.create(myProfileViewModel.getState().getUsername(), 42, "CSC236 is hard", "Term Test 4 was really difficult! I really wish I had revised deterministic finite automata...", "2025-11-18", new ArrayList<Comment>()));
        } else {
            postsToDisplay.add(allMyPosts.getLast());
            postsToDisplay.add(allMyPosts.get(allMyPosts.size() - 2));
            postsToDisplay.add(allMyPosts.get(allMyPosts.size() - 3));
            postsToDisplay.add(allMyPosts.get(allMyPosts.size() - 4));
            postsToDisplay.add(allMyPosts.get(allMyPosts.size() - 5));
            postsToDisplay.add(allMyPosts.get(allMyPosts.size() - 6));
        }

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        PostPanel post1 = new PostPanel(postsToDisplay.getFirst());
        PostPanel post2 = new PostPanel(postsToDisplay.get(1));
        PostPanel post3 = new PostPanel(postsToDisplay.get(2));
        PostPanel post4 = new PostPanel(postsToDisplay.get(3));
        PostPanel post5 = new PostPanel(postsToDisplay.get(4));
        PostPanel post6 = new PostPanel(postsToDisplay.getLast());
        row1.add(post1.panel);
        row1.add(post2.panel);
        row1.add(post3.panel);
        row2.add(post4.panel);
        row2.add(post5.panel);
        row2.add(post6.panel);

        JPanel postsPanel = new JPanel();
        postsPanel.add(row1);

        // Add to middle Panel
        middlePanel.add(usernamePanel);
        middlePanel.add(idPanel);
        middlePanel.add(bioPanel);
        middlePanel.add(passwordPanel);
        middlePanel.add(postsPanel);


        // Page Navigation
        GradientPanel bottomPanel = new GradientPanel();
        postButton = new JButton (MyProfileViewModel.HOME_BUTTON_LABEL);
        postButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        postButton.setMargin(new Insets(10, 20, 10, 20));
        searchButton = new JButton (MyProfileViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        searchButton.setMargin(new Insets(10, 20, 10, 20));
        profileButton = new JButton (MyProfileViewModel.PROFILE_BUTTON_LABEL);
        profileButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        profileButton.setMargin(new Insets(10, 20, 10, 20));
        bottomPanel.add(postButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(profileButton);

        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));


        //Creates Frame
        this.setLayout( new BorderLayout() );

        this.add(topPanel,  BorderLayout.NORTH);
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.add(middlePanel);
        bodyPanel.add(postsPanel);
        this.add(bodyPanel,  BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Adds functionality to the buttons
        postButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
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
                    }
                }
        );

        passwordConfirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final MyProfileState state = myProfileViewModel.getState();
                        state.setPassword(passwordInputField.getText());

                        changePasswordController.execute(
                                state.getUsername(),
                                passwordInputField.getText(),
                                state.getBio(),
                                state.getEmail(),
                                state.getName(),
                                state.getPosts()
                        );
                    }
                }
        );

        postButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myProfileController.switchToPostView();
                    }
                }
        );

        searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myProfileController.switchToSearchView();
                    }
                }
        );

        profileButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myProfileController.switchToMyProfileView();
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

//        if (e.getActionCommand().contains("View")) {
//            for (int i = 0; i < posts.size(); i++) {
//                if (e.getActionCommand().contains (STR."\{i}")) {
//                    myProfileController.switchToCurrentPost(posts.get(i));
//                }
//            }
//        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final MyProfileState state = (MyProfileState) e.getNewValue();
            username.setText(state.getUsername());
            passwordInputField.setText(state.getPassword());
            bioInputField.setText(state.getBio());
            email.setText(state.getEmail());
//            addPosts(state.getPosts());
        }
    }

//    private void addPosts(ArrayList<Post> posts) {
//        // Creates post preview to display on profile
//        // TODO Make it display the 5 most recent/random posts
//        for (int i = 0; i < posts.size(); i++) {
//            JLabel postTitle = new JLabel(posts.get(i).getTitle());
//            JLabel postDate = new JLabel(posts.get(i).getPost_date());
//            JPanel postBody = new JPanel();
//            JLabel postInfo = new JLabel(posts.get(i).getBody());
//
//            postBody.setLayout(new BoxLayout(postBody, BoxLayout.X_AXIS));
//            postBody.add(postInfo,  BorderLayout.LINE_START);
//            postBody.add(Box.createHorizontalGlue());
//
//            JPanel postHeader = new JPanel();
//            postHeader.setLayout(new BoxLayout(postHeader, BoxLayout.X_AXIS));
//            postHeader.add(postTitle, BorderLayout.LINE_START);
//            postHeader.add(Box.createHorizontalGlue());
//            postHeader.add(postDate, BorderLayout.LINE_END);
//
//            JButton seePostButton = new JButton(STR."View \{i}");
//            seePostButton.setFont(new Font("Helvetica", Font.BOLD, 15));
//
//            JPanel postInfoPanel = new JPanel();
//            postInfoPanel.setLayout(new BoxLayout(postInfoPanel, BoxLayout.Y_AXIS));
//            postInfoPanel.add(postHeader);
//            postInfoPanel.add(postBody);
//            postInfoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
//
//            JPanel postPanel = new JPanel();
//            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
//            JPanel postSpacer = new JPanel();
//            postSpacer.setMinimumSize(new Dimension(5, 5));
//            postPanel.add(postInfoPanel);
//            postPanel.add(postSpacer);
//            postPanel.add(seePostButton);
//
//            postContainer.add(postPanel);
//        }
//
//        this.posts = posts;
//    }

    public String getViewName() { return viewName; }

    public void setMyProfileController(MyProfileController controller) { this.myProfileController = controller; }
    public void setChangePasswordController(MyProfileChangePasswordController controller) { this.changePasswordController = controller; }
}
