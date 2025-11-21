package view;

import app.GradientPanel;
import entity.Post;
import interface_adapter.my_profile.MyProfileController;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MyProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private MyProfileViewModel myProfileViewModel;
    private String viewName = "my profile";
    private MyProfileController myProfileController;
    private int numOfLabels;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JLabel utorID;
    private final JPanel postContainer;

    // Buttons
    private final JButton back;
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
        back = new JButton (ProfileViewModel.BACK_BUTTON_LABEL);
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
        username = new JLabel("Me!");
        username.setFont(new Font("Helvetica", Font.BOLD, 40));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        // Add ID
        final JPanel idPanel = new JPanel();
        final JLabel idInfo = new JLabel("utorID: ");
        idInfo.setFont(new Font("Helvetica", Font.BOLD, 20));
        utorID = new JLabel("JIMMY123");
        utorID.setFont(new Font("Helvetica", Font.BOLD, 20));
        idPanel.add(idInfo);
        idPanel.add(utorID);

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
        final JPanel postsPanel = new JPanel();
        postContainer = new JPanel();
        postContainer.setLayout(new BoxLayout(postContainer, BoxLayout.Y_AXIS));
        postsPanel.add(new JScrollPane(postContainer),  BorderLayout.CENTER);

        // Temp until posts are added
        ProfileState state = new ProfileState();
        List<Post> postList = state.getPosts();
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
        postButton = new JButton (ProfileViewModel.POST_BUTTON_LABEL);
        postButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        postButton.setMargin(new Insets(10, 20, 10, 20));
        searchButton = new JButton (ProfileViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        searchButton.setMargin(new Insets(10, 20, 10, 20));
        profileButton = new JButton (ProfileViewModel.PROFILE_BUTTON_LABEL);
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
                        // TODO To Implement
                    }
                }
        );

        passwordConfirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO To Implement
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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ProfileState state = (ProfileState) evt.getNewValue();
            username.setText(state.getUsername());
            System.out.println("hello");
        }
    }

    private void addPosts(List<Post> postList) {
        // Creates post preview to display on profile
        // TODO Make it display the 5 most recent/random posts
        for (int i = 0; i < postList.size(); i++) {
            JLabel postTitle = new JLabel(postList.get(i).getTitle());
            JLabel postDate = new JLabel(postList.get(i).getPost_date());
            JPanel postBody = new JPanel();
            JLabel postInfo = new JLabel(postList.get(i).getBody());

            postBody.setLayout(new BoxLayout(postBody, BoxLayout.X_AXIS));
            postBody.add(postInfo,  BorderLayout.LINE_START);
            postBody.add(Box.createHorizontalGlue());

            JPanel postHeader = new JPanel();
            postHeader.setLayout(new BoxLayout(postHeader, BoxLayout.X_AXIS));
            postHeader.add(postTitle, BorderLayout.LINE_START);
            postHeader.add(Box.createHorizontalGlue());
            postHeader.add(postDate, BorderLayout.LINE_END);

            // TODO Change postPanel to postInfo, add a new panel postPanel with layout X_axis, add postInfo and postButton to access the posts from the post view
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.add(postHeader);
            postPanel.add(postBody);
            postPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

            postContainer.add(postPanel);
        }
    }

    public String getViewName() { return viewName; }

    public void setMyProfileController(MyProfileController controller) {this.myProfileController = controller;}
}
