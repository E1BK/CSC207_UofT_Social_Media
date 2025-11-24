// Julian
package view;

import app.GradientPanel;
import entity.Post;
import interface_adapter.profile.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The view for when a user is looking at someone's profile
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private ProfileViewModel profileViewModel;
    private String viewName = "profile";
    private ProfileController profileController;
    private int numOfLabels;

    // Labels
    private final JLabel username;
    private final JLabel bio;
    private final JLabel utorID;
    private final JPanel postContainer;

    // Buttons
    private final JButton back;
    private final JButton postButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public ProfileView(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        // Page Title
        JLabel name = new JLabel("ChatUofT > Profile");
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
        middlePanel.setMaximumSize(new Dimension(400, 100));
        middlePanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Add User
        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        usernameInfo.setFont(new Font("Helvetica", Font.BOLD, 40));
        username = new JLabel();
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

        // Add Bio
        final JPanel bioPanel = new JPanel();
        final JLabel bioInfo = new JLabel("Bio: ");
        bioInfo.setFont(new Font("Helvetica", Font.BOLD, 20));
        bio = new JLabel("Feeling SorE");
        bio.setFont(new Font("Helvetica", Font.BOLD, 20));
        bioPanel.add(bioInfo);
        bioPanel.add(bio);

        // Display Posts
        final JPanel postsPanel = new JPanel();
        postContainer = new JPanel();
        postContainer.setLayout(new BoxLayout(postContainer, BoxLayout.Y_AXIS));
        postsPanel.add(new JScrollPane(postContainer),  BorderLayout.CENTER);

//        JButton button = new JButton("Add");
//        button.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(numOfLabels);
//                final JPanel newPanel = new JPanel();
//                newPanel.add(new JLabel("Label " + numOfLabels++));
//                postContainer.add(newPanel);
//                postContainer.revalidate();
//                // Scroll down to last added panel
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        newPanel.scrollRectToVisible(newPanel.getBounds());
//                    }
//                });
//            }
//        });
//        postsPanel.add(button, BorderLayout.PAGE_END);

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
                        profileController.switchToLandingView();
                    }
                }
        );

        postButton.addActionListener(
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

    public String getViewName() {
        return viewName;
    }

    public void setProfileController(ProfileController controller) { this.profileController = controller; }
}

