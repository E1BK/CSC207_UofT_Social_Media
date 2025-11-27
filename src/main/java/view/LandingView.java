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
                allPosts.add(myPostFactory.create("sophia", 17, "Need help with calculus", "I finally understand derivatives after hours of practice!", "882193", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("mike", 23, "Java project update", "Implemented the backend today—feels great!", "449201", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("linda", 31, "Exam stress", "Can't believe how fast finals are approaching.", "732510", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("arjun", 56, "New hobby!", "Started learning guitar and it's surprisingly relaxing.", "110834", new ArrayList<Comment>()));
                allPosts.add(myPostFactory.create("hasan", 42, "CSC236 is hard", "Term Test 4 was really difficult! hahahahhahahah a ahhahahaaa haahhuqk hfehfhiufhihf  ffhrifhi", "565775", new ArrayList<Comment>()));

            }
            ArrayList<Post> postsToDisplay = new ArrayList<Post>();
            // assuming allPosts.size() >= 3:
            postsToDisplay.add(allPosts.getLast());
            postsToDisplay.add(allPosts.get(allPosts.size() - 2));
            postsToDisplay.add(allPosts.get(allPosts.size() - 3));




            JPanel post1 = new JPanel();
//          post1.setLayout(new BoxLayout(post1, BoxLayout.Y_AXIS));
            post1.setLayout(new BorderLayout());
            post1.setBackground(Color.white);
            post1.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
//            post1.setMinimumSize(new Dimension(300, 200));
//            post1.setMaximumSize(new Dimension(300, 200));
            post1.setPreferredSize(new Dimension(300, 200));


            JPanel post1header = new JPanel();
                JLabel post1title = new JLabel(postsToDisplay.getFirst().getTitle());
                post1title.setFont(new Font("Helvetica", Font.BOLD, 20));
                JLabel post1username = new JLabel("by " + postsToDisplay.getFirst().getUsername());
                post1username.setFont(new Font("Helvetica", Font.ITALIC, 15));
            post1header.add(post1title);
            post1header.add(post1username);

            JTextArea post1body = new JTextArea(postsToDisplay.getFirst().getBody());
            post1body.setPreferredSize(new Dimension(200, 150));
            post1body.setMaximumSize(new Dimension(200, 150));
            post1body.setMinimumSize(new Dimension(200, 150));

            post1body.setLineWrap(true);
            post1body.setWrapStyleWord(true);
            post1body.setEditable(false);
            post1body.setFont(new Font("Helvetica", Font.PLAIN, 15));
            post1body.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
            post1.add(post1body, BorderLayout.CENTER);


            JLabel post1numComments = new JLabel(String.valueOf(postsToDisplay.getFirst().getComments().size()));
            post1.add(post1numComments, BorderLayout.NORTH);













        JPanel displayPanel = new JPanel();
        displayPanel.add(post1);
//        displayPanel.add(post2);
//        displayPanel.add(post3);







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
