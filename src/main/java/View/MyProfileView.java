package view;

import interface_adapter.my_profile.MyProfileController;
import interface_adapter.my_profile.MyProfileViewModel;
import view.LabelTextPanel;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables
    private MyProfileViewModel myProfileViewModel;
    private String viewName = "profile";
    private MyProfileController myProfileController;

    // Textfields
    private final JTextField bioInputField = new JTextField(15);

    // Labels
    private final JLabel username;

    // Buttons
    private final JButton back;
    private final JButton bioConfirm;
    private final JButton postsButton;
    private final JButton searchButton;
    private final JButton profileButton;

    public MyProfileView(MyProfileViewModel myProfileViewModel) {
        this.myProfileViewModel = myProfileViewModel;
        myProfileViewModel.addPropertyChangeListener(this);

        // Add page title
        final JPanel title = new JPanel();
        final JLabel titleInfo = new JLabel("My Profile");
        back = new JButton ("Back");
        title.add(back);
        title.add(titleInfo);

        // Add username
        final JPanel usernamePanel = new JPanel();
        final JLabel usernameInfo = new JLabel("Profile: ");
        username = new JLabel("Me!");
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        // Add bio editor
        final JPanel bioPanel = new JPanel();
        bioConfirm = new JButton("Confirm");
        final LabelTextPanel bioInfo = new LabelTextPanel(new JLabel("Bio: "),
                                                               bioInputField,
                                                               bioConfirm);
        bioPanel.add(bioInfo);

        // Add buttons
        final JPanel buttons = new JPanel();
        postsButton = new JButton ("Posts");
        searchButton = new JButton ("Search");
        profileButton = new JButton ("Profile");
        buttons.add(postsButton);
        buttons.add(searchButton);
        buttons.add(profileButton);

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

        postsButton.addActionListener(
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

        this.add(title);
        this.add(usernamePanel);
        this.add(bioPanel);
        this.add(buttons);
    }

    public static void main(String[] args) {
        //For Testing
        JFrame frame = new JFrame("My Profile View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new MyProfileView(new MyProfileViewModel()));

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() { return viewName; }

    public void setProfileController(MyProfileController controller) {this.myProfileController = controller;}
}
