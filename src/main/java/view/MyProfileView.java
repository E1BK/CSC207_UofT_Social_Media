package view;

import app.GradientPanel;
import entity.Post;
import interface_adapter.change_password.ChangePasswordController;
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
    private int numOfLabels;

    // Controllers
    private MyProfileController myProfileController;
    private MyProfileChangePasswordController changePasswordController = null;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JLabel email;
    private final JPanel postContainer;

    // Buttons
    private final JButton back;
    private final JButton bioConfirm;
    private final JButton passwordConfirm;
    private final JButton postButton;
    private final JButton searchButton;
    private final JButton profileButton;
    private ArrayList<Post> posts;

    public MyProfileView(MyProfileViewModel myProfileViewModel) {
        this.myProfileViewModel = myProfileViewModel;
        this.myProfileViewModel.addPropertyChangeListener(this);

        // Page Title
        JLabel name = new JLabel("ChatUofT > My Profile");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        back = new JButton (MyProfileViewModel.BACK_BUTTON_LABEL);
        back.setMargin(new Insets(8, 20, 8, 20));
        topPanel.add(back, BorderLayout.WEST);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(name, BorderLayout.CENTER);
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

        // Display Posts
        //posts = new ArrayList<Post>();
        final JPanel postsPanel = new JPanel();
        postContainer = new JPanel();
        postContainer.setLayout(new BoxLayout(postContainer, BoxLayout.Y_AXIS));
        postsPanel.add(new JScrollPane(postContainer),  BorderLayout.CENTER);

        // Temp until posts are added
        MyProfileState state = new MyProfileState();
        ArrayList<Post> postList = state.getPosts();
        addPosts(postList);

        postsPanel.add(postContainer);
        postsPanel.setSize(new Dimension(300, 200));
        postsPanel.setVisible(true);

        // Add to middle Panel
        middlePanel.add(usernamePanel);
        middlePanel.add(idPanel);
        middlePanel.add(bioPanel);
        middlePanel.add(passwordPanel);
        middlePanel.add(postsPanel);

        // Page Navigation
        GradientPanel bottomPanel = new GradientPanel();
        postButton = new JButton (MyProfileViewModel.POST_BUTTON_LABEL);
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

        // Adds functionality to the buttons
        back.addActionListener(
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

        //Creates Frame
        this.setLayout( new BorderLayout() );

        this.add(topPanel,  BorderLayout.NORTH);
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.add(middlePanel);
        bodyPanel.add(postsPanel);
        this.add(bodyPanel,  BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getActionCommand().contains("View")) {
            for (int i = 0; i < posts.size(); i++) {
                if (e.getActionCommand().contains (STR."\{i}")) {
                    myProfileController.switchToCurrentPost(posts.get(i));
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final MyProfileState state = (MyProfileState) e.getNewValue();
            username.setText(state.getUsername());
            passwordInputField.setText(state.getPassword());
            bioInputField.setText(state.getBio());
            email.setText(state.getEmail());
            addPosts(state.getPosts());
        }
    }

    private void addPosts(ArrayList<Post> posts) {
        // Creates post preview to display on profile
        // TODO Make it display the 5 most recent/random posts
        for (int i = 0; i < posts.size(); i++) {
            JLabel postTitle = new JLabel(posts.get(i).getTitle());
            JLabel postDate = new JLabel(posts.get(i).getPost_date());
            JPanel postBody = new JPanel();
            JLabel postInfo = new JLabel(posts.get(i).getBody());

            postBody.setLayout(new BoxLayout(postBody, BoxLayout.X_AXIS));
            postBody.add(postInfo,  BorderLayout.LINE_START);
            postBody.add(Box.createHorizontalGlue());

            JPanel postHeader = new JPanel();
            postHeader.setLayout(new BoxLayout(postHeader, BoxLayout.X_AXIS));
            postHeader.add(postTitle, BorderLayout.LINE_START);
            postHeader.add(Box.createHorizontalGlue());
            postHeader.add(postDate, BorderLayout.LINE_END);

            JButton seePostButton = new JButton(STR."View \{i}");
            seePostButton.setFont(new Font("Helvetica", Font.BOLD, 15));

            JPanel postInfoPanel = new JPanel();
            postInfoPanel.setLayout(new BoxLayout(postInfoPanel, BoxLayout.Y_AXIS));
            postInfoPanel.add(postHeader);
            postInfoPanel.add(postBody);
            postInfoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
            JPanel postSpacer = new JPanel();
            postSpacer.setMinimumSize(new Dimension(5, 5));
            postPanel.add(postInfoPanel);
            postPanel.add(postSpacer);
            postPanel.add(seePostButton);

            postContainer.add(postPanel);
        }

        this.posts = posts;
    }

    public String getViewName() { return viewName; }

    public void setMyProfileController(MyProfileController controller) { this.myProfileController = controller; }
    public void setChangePasswordController(MyProfileChangePasswordController controller) { this.changePasswordController = controller; }
}
