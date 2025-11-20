// hasan
package view;

import app.GradientPanel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileController;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserViewModel;

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

        JTextField searchBar = new JTextField(20);
        JLabel searchPrompt = new JLabel("Your friend's username:");
        searchPrompt.setFont(new Font("Helvetica", Font.BOLD, 20));
        searchBar.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        LabelTextPanel searchBarPanel = new LabelTextPanel(searchPrompt, searchBar, searchButton);

        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        searchBarPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        middlePanel.add(titlePanel, BorderLayout.NORTH);
        middlePanel.add(searchBarPanel, BorderLayout.CENTER);

        // IMPORTANT:
        // middlePanel.add(displayResultPanel, BorderLayout.SOUTH)
        // NOTE: when the searching functionality has been implemented.
        // then use the displayResultPanel to display the resulting posts
        // of the searched user, or to display the error message if the user does not exist.

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
    }

    public void setSearchUserController(SearchUserController searchUserController) {
        this.searchUserController = searchUserController;
    }
}
