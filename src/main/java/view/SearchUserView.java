// hasan, russell
/** (russell)
 * SearchUserView is the GUI for the "People" / user search screen.
 *
 * functions:
 * - Show a text field and a "Search" button for entering a friend's username.
 * - Forward the entered username to SearchUserController, instead of accessing
 *   the database or data access layer directly.
 * - Provide navigation buttons ("Home", "People", "Me") that ask the controller
 *   to switch views via the ViewManager.
 *
 *  notes:
 * - searchBar and searchButton are stored as fields (not local variables) so
 *   the whole view can access them (for adding listeners, reading input, or
 *   clearing / disabling the button).
 * - The ActionListener on searchButton calls searchUserController.execute(username),
 *   which triggers the SearchUser use case (SearchUserInteractor).
 */
package view;

import app.GradientPanel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileController;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserViewModel;
import interface_adapter.search_user.SearchUserState;
import interface_adapter.profile.ProfileController;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchUserView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "searchUser";
    private SearchUserViewModel searchUserViewModel;
    private SearchUserController searchUserController = null;
    private ProfileController profileController = null;   // to profile

    private final JButton me;
    private final JButton people;
    private final JButton home;


    // (russell) newly added: label for showing success/fail message
    private final JLabel resultLabel;

    // (russell) newly added
    // store searchBar and searchButton as fields
    // Russell: button to go to the found user's profile
    private final JButton viewProfileButton;
    // instead of local variables so that the whole view can access them
    // (for adding listeners, clearing input, disabling the button ...)
    private final JTextField searchBar;
    private final JButton searchButton;

    public SearchUserView(SearchUserViewModel searchUserViewModel) {
        this.searchUserViewModel = searchUserViewModel;
        this.searchUserViewModel.addPropertyChangeListener(this);

        // top panel
        JLabel name = new JLabel("ChatUofT > People");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Russell: new middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder(new EmptyBorder(60, 0, 40, 0));

        // title
        JLabel title = new JLabel("Find your friends here!");
        JPanel titlePanel = new JPanel();
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Searching Area
        searchBar = new JTextField(20);
        JLabel searchPrompt = new JLabel("Your friend's username:");
        searchPrompt.setFont(new Font("Helvetica", Font.BOLD, 20));
        searchBar.setFont(new Font("Helvetica", Font.PLAIN, 20));
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 20));

        LabelTextPanel searchBarPanel =
                new LabelTextPanel(searchPrompt, searchBar, searchButton);

        searchBarPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        searchBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchBar.setMargin(new Insets(10, 20, 10, 20));
        searchButton.setMargin(new Insets(6, 20, 6, 20));

        // results and View Profile button
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        viewProfileButton.setMargin(new Insets(10, 40, 10, 40));
        viewProfileButton.setEnabled(false);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        messagePanel.add(resultLabel);
        messagePanel.add(Box.createVerticalStrut(8));
        messagePanel.add(viewProfileButton);
        messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        middlePanel.add(titlePanel);
        middlePanel.add(Box.createVerticalStrut(25));
        middlePanel.add(searchBarPanel);
        middlePanel.add(Box.createVerticalStrut(15));
        middlePanel.add(messagePanel);

        // bottom panel
        GradientPanel bottomPanel = new GradientPanel();
        me = new JButton(LandingViewModel.ME_BUTTON_LABEL);
        me.setFont(new Font("Helvetica", Font.BOLD, 15));
        people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
        people.setFont(new Font("Helvetica", Font.BOLD, 15));
        home = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
        home.setFont(new Font("Helvetica", Font.BOLD, 15));

        me.setMargin(new Insets(10, 20, 10, 20));
        home.setMargin(new Insets(10, 20, 10, 20));
        people.setMargin(new Insets(10, 20, 10, 20));
        searchButton.setMargin(new Insets(6, 20, 6, 20));
        searchBar.setMargin(new Insets(10, 20, 10, 20));

        bottomPanel.add(home);
        bottomPanel.add(people);
        bottomPanel.add(me);
        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners:

        home.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(home)) {
                            System.out.println(evt.getActionCommand());
                            searchUserController.switchToLandingView();

                        }
                    }
                }
        );

        me.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(me)) {
                            System.out.println(evt.getActionCommand());
                            searchUserController.switchToMeView();
                        }
                    }
                }
        );

        // (russell) newly added
        // it forwards the entered username to the SearchUserController,
        // which then calls the search use case (SearchUserInteractor) with
        // a SearchUserInputData object.
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (searchUserController != null) {
                    String username = searchBar.getText();
                    System.out.println("SEARCH for: " + username);
                    searchUserController.execute(username);
                } else {
                    System.out.println("SearchUserController is null (not set yet)");
                }
            }
        });

        // Russell: viewProfileButton listener
        // Later can call controller to show this user's profile
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (profileController == null) {
                    System.out.println("ProfileController is null");
                    return;
                }

                SearchUserState state = searchUserViewModel.getState();
                String username = state.getSelectedUsername();
                // Russell NEW NOV29: pass this username to the Profile use case.
                // the execute(String username) method needs to be included in ProfileController
                if (username != null && !username.isEmpty()) {
                    System.out.println("View Profile clicked for " + username);
                    // (!!!) after Profile implements execute(String username
                    // (!!!) uncomment the next line
                    // profileController.execute(username);
                    profileController.switchToProfileView();
                } else {
                    System.out.println("View Profile clicked but no selectedUsername in state");
                }
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
        SearchUserState state = searchUserViewModel.getState();
        String message = state.getMessage();
        resultLabel.setText(message);
        String selectedUsername = state.getSelectedUsername();
        boolean hasUser = selectedUsername != null && !selectedUsername.isEmpty();
        viewProfileButton.setEnabled(hasUser);
    }

    public void setSearchUserController(SearchUserController searchUserController) {
        this.searchUserController = searchUserController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}
