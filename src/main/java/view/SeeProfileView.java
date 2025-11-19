package view;

import app.GradientPanel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.see_profile.SeeProfileController;
import interface_adapter.see_profile.SeeProfileState;
import interface_adapter.see_profile.SeeProfileViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SeeProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "altprofile";
    private SeeProfileViewModel profileViewModel;
    private SeeProfileController seeProfileController = null;

    public SeeProfileView(SeeProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        // top panel
        JLabel name = new JLabel("ChatUofT > Me");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        //

        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Firstname Lastname");
        JPanel titlePanel = new JPanel();
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        //

        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        JPanel bioLabelPanel = new JPanel();
        bioLabelPanel.add(bioLabel);

        JLabel bioText = new JLabel("I hate clean architecture");
        bioText.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JPanel bioTextPanel = new JPanel();
        bioTextPanel.add(bioText);
        bioTextPanel.setBackground(Color.WHITE);

        JPanel bioPanel = new JPanel();
        bioPanel.add(bioLabelPanel);
        bioPanel.add(bioTextPanel);

        //

        JLabel utoridLabel = new JLabel("utorID:");
        utoridLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        JPanel utoridLabelPanel = new JPanel();
        utoridLabelPanel.add(utoridLabel);

        JLabel utoridText = new JLabel("sample12");
        utoridText.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JPanel utoridTextPanel = new JPanel();
        utoridTextPanel.add(utoridText);
        utoridTextPanel.setBackground(Color.WHITE);

        JPanel utoridPanel = new JPanel();
        utoridPanel.add(utoridLabelPanel);
        utoridPanel.add(utoridTextPanel);

        //

        JLabel programLabel = new JLabel("POSt:");
        programLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        JPanel programLabelPanel = new JPanel();
        programLabelPanel.add(programLabel);

        JLabel programText = new JLabel("Political Science");
        programText.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JPanel programTextPanel = new JPanel();
        programTextPanel.add(programText);
        programTextPanel.setBackground(Color.WHITE);

        JPanel programPanel = new JPanel();
        programPanel.add(programLabelPanel);
        programPanel.add(programTextPanel);

        //

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(bioPanel, BorderLayout.NORTH);
        infoPanel.add(utoridPanel, BorderLayout.CENTER);
        infoPanel.add(programPanel, BorderLayout.SOUTH);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.add(infoPanel, BorderLayout.NORTH);

        middlePanel.add(titlePanel, BorderLayout.NORTH);
        middlePanel.add(wrapperPanel, BorderLayout.CENTER);
        // IMPORTANT:
        // middlePanel.add(displayPostsPanel, BorderLayout.SOUTH)
        // NOTE: when the functionality has been implemented.
        // then use the displayPostsPanel to display all the posts
        // of this user, or to display an error message if the user does not exist.
        //

        // bottom panel
        GradientPanel bottomPanel = new GradientPanel();
        JButton me = new JButton(LandingViewModel.ME_BUTTON_LABEL);
        me.setFont(new Font("Helvetica", Font.BOLD, 15));
        JButton people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
        people.setFont(new Font("Helvetica", Font.BOLD, 15));
        JButton home = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
        home.setFont(new Font("Helvetica", Font.BOLD, 15));

        me.setMargin(new Insets(10, 20, 10, 20));
        home.setMargin(new Insets(10, 20, 10, 20));
        people.setMargin(new Insets(10, 20, 10, 20));

        bottomPanel.add(home);
        bottomPanel.add(people);
        bottomPanel.add(me);
        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        //

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners:

        home.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(home)) {
                            System.out.println("CLICKED 'HOME'!");
                            seeProfileController.switchToHomeView();

                        }
                    }
                }
        );

        people.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(people)) {
                            System.out.println("CLICKED 'PEOPLE'!");
                            seeProfileController.switchToPeopleView();

                        }
                    }
                }
        );

    }



    //    // Variables
//    // Labels
//    private final JLabel username;
//    private final JLabel bio;
//
//    // Buttons
//    private final JButton back;
//    private final JButton postsButton;
//    private final JButton searchButton;
//    private final JButton profileButton;
//
//    public ProfileView() {
//        final JPanel title = new JPanel();
//        final JLabel titleInfo = new JLabel("Profiles");
//        back = new JButton ("Back");
//        title.add(back);
//        title.add(titleInfo);
//
//        final JPanel usernamePanel = new JPanel();
//        final JLabel usernameInfo = new JLabel("Profile: ");
//        username = new JLabel("SorEgo");
//        usernamePanel.add(usernameInfo);
//        usernamePanel.add(username);
//
//        final JPanel bioPanel = new JPanel();
//        final JLabel bioInfo = new JLabel("Bio:");
//        bio = new JLabel("Feeling SorE");
//        bioPanel.add(bioInfo);
//        bioPanel.add(bio);
//
//        final JPanel buttons = new JPanel();
//        postsButton = new JButton ("Posts");
//        searchButton = new JButton ("Search");
//        profileButton = new JButton ("Profile");
//        buttons.add(postsButton);
//        buttons.add(searchButton);
//        buttons.add(profileButton);
//
//        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
//
//        add(title);
//        add(usernamePanel);
//        add(bioPanel);
//        add(buttons);
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Profile View");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        frame.add(new ProfileView());
//
//        frame.pack();
//        frame.setVisible(true);
//    }
//

    public String getViewName() {return viewName;}

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SeeProfileState state = (SeeProfileState) evt.getNewValue();
    }

    public void setSeeProfileController(SeeProfileController profileController) {this.seeProfileController = profileController;}



}
