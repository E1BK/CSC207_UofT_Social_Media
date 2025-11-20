// hasan

package view;

import app.GradientPanel;
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
        postBody.setMinimumSize(new Dimension(300, 200));
        postBody.setMaximumSize(new Dimension(300, 200));
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

        middlePanel.add(titlePanel, BorderLayout.NORTH);
        middlePanel.add(postPanel, BorderLayout.CENTER);

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
