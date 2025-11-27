// hasan

package view;

import app.GradientPanel;
import entity.Comment;
import entity.Post;
import entity.PostFactory;
import interface_adapter.landing.LandingState;
import interface_adapter.landing.MakePostController;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LandingView extends JPanel implements ActionListener, PropertyChangeListener {

    // the LandingViewModel attribute gives the data to our LandingView object.

    private final String viewName = "landing";
    private LandingViewModel landingViewModel;
    private MakePostController makePostController = null;
    private MyProfileController myProfileController = null;

    //    private final JTextField postBody;
    private final JTextField postTitle;
    private final JTextArea postBody;
    private final JButton makePost;

    private final JButton profile;
    private final JButton people;
    private final JButton home;






    public LandingView(LandingViewModel landingViewModel) {

        this.landingViewModel = landingViewModel;
        landingViewModel.addPropertyChangeListener(this);

        // top panel
        JLabel name = new JLabel("ChatUofT");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("What's happening at UofT?");
        JPanel titlePanel = new JPanel();
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        postTitle = new JTextField(20);
        postTitle.setFont(new Font("Helvetica", Font.PLAIN, 20));
        postTitle.setMargin(new Insets(10, 20, 10, 20));
        postTitle.setMaximumSize(new Dimension(300, 30));
        postTitle.setMinimumSize(new Dimension(300, 30));

        postBody = new JTextArea();
        postBody.setFont(new Font("Helvetica", Font.PLAIN, 20));
        postBody.setMinimumSize(new Dimension(300, 150));
        postBody.setMaximumSize(new Dimension(300, 150));
        postBody.setMargin(new Insets(5, 5, 5, 5));

        makePost = new JButton(LandingViewModel.MAKE_POST_BUTTON_LABEL);
        makePost.setFont(new Font("Helvetica", Font.BOLD, 20));
        makePost.setMargin(new Insets(10, 20, 10, 20));
        postTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        postBody.setAlignmentX(Component.CENTER_ALIGNMENT);
        makePost.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel gapPanel = new JPanel();
        gapPanel.setMinimumSize(new Dimension(20, 4));
        gapPanel.setMaximumSize(new Dimension(20, 4));

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.add(postTitle);
        postPanel.add(gapPanel);
        postPanel.add(postBody);
        postPanel.add(makePost);
        postPanel.setBorder(new EmptyBorder(5, 0, 5, 0));



            // HERE ADD THE POSTS DISPLAY
            ArrayList<Post> allPosts = landingViewModel.getState().getPosts();
            if (allPosts.size() <= 3) {
                PostFactory myPostFactory = new PostFactory();
                allPosts.add(myPostFactory.create("sophia", 17, "Need help with calculus", "I finally understand derivatives after hours of practice!", "2025-11-18", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("mike", 23, "Java project update", "Implemented the backend today—feels great!", "2025-11-18", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("julian", 31, "Exam stress", "Can't believe how fast finals are approaching.", "2025-11-18", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("ioane", 56, "Cloud watching", "Did you know the average cloud weighs over a million pounds? It's all about density! Watching those massive, weightless-looking giants drift by is truly mind-boggling. #ScienceFacts #Nature", "2025-11-18", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("hayden", 42, "CSC236 is hard", "Term Test 4 was really difficult! I really wish I had revised deterministic finite automata...", "2025-11-18", new ArrayList<Comment>()));

            }
            ArrayList<Post> postsToDisplay = new ArrayList<Post>();
            // assuming allPosts.size() >= 3:
            postsToDisplay.add(allPosts.getLast());
            postsToDisplay.add(allPosts.get(allPosts.size() - 2));
            postsToDisplay.add(allPosts.get(allPosts.size() - 3));


        PostPanel post1 = new PostPanel(postsToDisplay.getFirst());
        PostPanel post2 = new PostPanel(postsToDisplay.get(1));
        PostPanel post3 = new PostPanel(postsToDisplay.get(2));
        JPanel displayPanel = new JPanel();
        displayPanel.add(post1.panel);
        displayPanel.add(post2.panel);
        displayPanel.add(post3.panel);


        middlePanel.add(titlePanel, BorderLayout.NORTH);
        middlePanel.add(postPanel, BorderLayout.CENTER);
        middlePanel.add(displayPanel, BorderLayout.SOUTH);

        // bottom panel
        GradientPanel bottomPanel = new GradientPanel();
        profile = new JButton(LandingViewModel.ME_BUTTON_LABEL);
        profile.setFont(new Font("Helvetica", Font.BOLD, 15));
        people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
        people.setFont(new Font("Helvetica", Font.BOLD, 15));
        home = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
        home.setFont(new Font("Helvetica", Font.BOLD, 15));

        profile.setMargin(new Insets(10, 20, 10, 20));
        home.setMargin(new Insets(10, 20, 10, 20));
        people.setMargin(new Insets(10, 20, 10, 20));


        bottomPanel.add(home);
        bottomPanel.add(people);
        bottomPanel.add(profile);
        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        //

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);


        // action listeners:
        profile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profile)) {
                            System.out.println("CLICKED 'ME'!");
                            myProfileController.switchToMyProfileView();
                        }
                    }
                }
        );

        people.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(people)) {
                            System.out.println("CLICKED 'PEOPLE'!");
                            makePostController.switchToPeopleView();
                        }
                    }
                }
        );

        makePost.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(makePost)) {
                            System.out.println("CLICKED 'MAKE POST'!");
                            final LandingState landingState = landingViewModel.getState();
                            makePostController.execute(landingState.getUsername(),
                                    landingState.getNewpost_title(),
                                    landingState.getNewpost_body());
                        }
                    }
                }
        );

        // Document Listeners
        postBody.getDocument().addDocumentListener( new DocumentListener() {
            private void documentListenerHelper() {
                final LandingState landingState = landingViewModel.getState();
                landingState.setNewpost_body(postBody.getText());
                landingViewModel.setState(landingState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        postTitle.getDocument().addDocumentListener( new DocumentListener() {
            private void documentListenerHelper() {
                final LandingState landingState = landingViewModel.getState();
                landingState.setNewpost_title(postTitle.getText());
                landingViewModel.setState(landingState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { final LandingState state = (LandingState) evt.getNewValue(); }

    public void setMakePostController(MakePostController controller) {
        this.makePostController = controller;
    }

    public void setMyProfileController(MyProfileController controller) { this.myProfileController = controller; }
}
