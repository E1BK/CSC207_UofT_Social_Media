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

    private final JButton me;
    private final JButton people;
    private final JButton home;


    // (russell) newly added: label for showing success/fail message
    private final JLabel resultLabel;

    // (russell) newly added
    // We store searchBar (line 59) and searchButton (line 63) as fields
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

        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Find your friends here!");
        JPanel titlePanel = new JPanel();
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        titlePanel.add(title);

        //(russell) newly added
        // We store searchBar (line 59) and searchButton (line 63) as fields
        // instead of local variables so that the whole view can access them
        // (for adding listeners, clearing input, disabling the button ...)
        searchBar = new JTextField(20);
        JLabel searchPrompt = new JLabel("Your friend's username:");
        searchPrompt.setFont(new Font("Helvetica", Font.BOLD, 20));
        searchBar.setFont(new Font("Helvetica", Font.PLAIN, 20));
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        LabelTextPanel searchBarPanel = new LabelTextPanel(searchPrompt, searchBar, searchButton);

        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        searchBarPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        middlePanel.add(titlePanel, BorderLayout.NORTH);
        middlePanel.add(searchBarPanel, BorderLayout.CENTER);

        // Russell: panel for result message (e.g. "Found user: gaohe" / "User Not Found")
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Helvetica", Font.ITALIC, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(resultLabel, BorderLayout.CENTER);
        messagePanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        middlePanel.add(messagePanel, BorderLayout.SOUTH);

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
                            System.out.println("CLICKED 'HOME'!");
                            searchUserController.switchToLandingView();

                        }
                    }
                }
        );

        me.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(me)) {
                            System.out.println("CLICKED 'ME'!");
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
        // Russell: whenever SearchUserViewModel fires a change,
        // read the latest state and update the result label on the UI.
        SearchUserState state = searchUserViewModel.getState();
        String message = state.getMessage();
        resultLabel.setText(message);
    }

    public void setSearchUserController(SearchUserController searchUserController) {
        this.searchUserController = searchUserController;
    }
}
