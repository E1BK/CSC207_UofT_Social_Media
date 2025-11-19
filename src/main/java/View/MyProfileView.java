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
    private String viewName = "myProfile";
    private MyProfileController myProfileController;
    private int numOfLabels;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);

    // Labels
    private final JLabel username;
    private final JPanel postContainer;

    // Buttons
    private final JButton back;
    private final JButton bioConfirm;
    private final JButton postButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public MyProfileView(MyProfileViewModel myProfileViewModel) {
        this.myProfileViewModel = myProfileViewModel;
        myProfileViewModel.addPropertyChangeListener(this);

        // Page Title
        JLabel name = new JLabel("UofTeam > " + "My Profile");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        back = new JButton (ProfileViewModel.BACK_BUTTON_LABEL);
        back.setMargin(new Insets(10, 20, 10, 20));
        topPanel.add(back, BorderLayout.WEST);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(name, BorderLayout.CENTER);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Page Body
        final JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        // Add username
        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        username = new JLabel("Me!");
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        // Add Bio Editor
        final JPanel bioPanel = new JPanel();
        bioConfirm = new JButton("Confirm");
        final LabelTextPanel bioInfo = new LabelTextPanel(new JLabel("Bio: "),
                                                               bioInputField,
                                                               bioConfirm);
        bioPanel.add(bioInfo);

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
        middlePanel.add(usernamePanel, BorderLayout.NORTH);
        middlePanel.add(bioPanel, BorderLayout.CENTER);
        middlePanel.add(postsPanel, BorderLayout.SOUTH);

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
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void addPosts(List<Post> postList) {
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

            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.add(postHeader);
            postPanel.add(postBody);
            postPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

            postContainer.add(postPanel);
        }
    }

    public String getViewName() { return viewName; }

    public void setProfileController(MyProfileController controller) {this.myProfileController = controller;}
}
