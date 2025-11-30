// hasan

package view;

import app.GradientPanel;
import interface_adapter.landing.LandingController;
import interface_adapter.landing.LandingState;
import interface_adapter.make_post.MakePostController;
import interface_adapter.make_post.MakePostState;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.make_post.MakePostViewModel;
import interface_adapter.my_profile.MyProfileController;
import use_case.make_post.PostViewData;

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
    private MakePostViewModel makePostViewModel;
    private MakePostController makePostController = null;
    private MyProfileController myProfileController = null;
    private LandingController landingController = null;

    //    private final JTextField postBody;
    private final JTextField postTitle;
    private final JTextArea postBody;
    private final JButton makePost;

    private final JButton profile;
    private final JButton people;
    private final JButton home;

    PostPanel post1;
    PostPanel post2;
    PostPanel post3;
    JPanel displayPanel;
    JButton refreshButton;

    ArrayList<PostViewData> postsToDisplay;

    public LandingView(LandingViewModel landingViewModel, MakePostViewModel makePostViewModel) {

        this.landingViewModel = landingViewModel;
        this.makePostViewModel = makePostViewModel;

        landingViewModel.addPropertyChangeListener(this);
        makePostViewModel.addPropertyChangeListener(this);

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

        // detour: clubs!
        JButton clubsButton = new JButton("See Clubs");
        clubsButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        clubsButton.setMargin(new Insets(10, 30, 10, 30));
        clubsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // detour: clubs complete

        // postPanel is where you can make a post:
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.add(postTitle);
        postPanel.add(gapPanel);
        postPanel.add(postBody);
        postPanel.add(makePost);
        postPanel.add(clubsButton);
        postPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        postsToDisplay = new ArrayList<PostViewData>();

        displayPanel = new JPanel();
        displayPanel.setBorder(new EmptyBorder(0, 0, 0, 10));

        refreshButton = new JButton("⟳");
        refreshButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new Dimension(60, 60));

        refreshButton.addActionListener(e -> {
            landingController.execute();
        });



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
                            System.out.println(evt.getActionCommand());
                            landingController.switchToProfileView();
                        }
                    }
                }
        );

        people.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(people)) {
                            System.out.println(evt.getActionCommand());
                            landingController.switchToPeopleView();
                        }
                    }
                }
        );

        makePost.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(makePost)) {
                            System.out.println(evt.getActionCommand());
                            final MakePostState makePostState = makePostViewModel.getState();
                            final LandingState landingState = landingViewModel.getState();
                            makePostController.execute(landingState.getUsername(),
                                    makePostState.getNewpost_title(),
                                    makePostState.getNewpost_body());
                        }
                    }
                }
        );

        clubsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clubsButton)) {
                            System.out.println("CLICKED 'SEE CLUBS'!");
                            landingController.switchToClubsView();
                        }
                    }
                }
        );

        // Document Listeners
        postBody.getDocument().addDocumentListener( new DocumentListener() {
            private void documentListenerHelper() {
                final MakePostState makePostState = makePostViewModel.getState();
                makePostState.setNewpost_body(postBody.getText());
                makePostViewModel.setState(makePostState);
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
                final MakePostState makePostState = makePostViewModel.getState();
                makePostState.setNewpost_title(postTitle.getText());
                makePostViewModel.setState(makePostState);
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
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if (source == landingViewModel) {
            LandingState state = landingViewModel.getState();

            if (!state.isInitialized()) {
                state.setInitialized(true);
                landingController.execute();
                landingViewModel.setState(state);
            }

            refreshPosts(state);
        }
        else if (source == makePostViewModel) {
            MakePostState state = makePostViewModel.getState();

            postTitle.setText(state.getNewpost_title());
            postBody.setText(state.getNewpost_body());

            String err = state.getpostError();
            if (err != null && !err.isBlank()) {
                JOptionPane.showMessageDialog(this, err);
                state.setpostError("");
                makePostViewModel.setState(state);
            }
        }
    }

    public void setMakePostController(MakePostController controller) {
        this.makePostController = controller;
    }

    public void setLandingController(LandingController controller) {this.landingController = controller;}

    public void setMyProfileController(MyProfileController controller) { this.myProfileController = controller; }

    public void refreshPosts(LandingState landingState) {
        displayPanel.removeAll();
        postsToDisplay = landingState.getPosts();
        post1 = new PostPanel(postsToDisplay.getFirst());
        post2 = new PostPanel(postsToDisplay.get(1));
        post3 = new PostPanel(postsToDisplay.get(2));
        displayPanel.add(post1.panel);
        displayPanel.add(post2.panel);
        displayPanel.add(post3.panel);
        displayPanel.add(refreshButton);
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}
