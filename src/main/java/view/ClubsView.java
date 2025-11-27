package view;

import app.GradientPanel;
import interface_adapter.clubs.ClubsController;
import interface_adapter.clubs.ClubsState;
import interface_adapter.clubs.ClubsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClubsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "clubs";
    private ClubsViewModel clubsViewModel;
    private ClubsController clubsController = null;

    private final JTextField searchBar;
    private final JLabel clubInfoField = new JLabel();
    private final JButton searchButton;


    public ClubsView(ClubsViewModel clubsViewModel) {
        this.clubsViewModel = clubsViewModel;
        this.clubsViewModel.addPropertyChangeListener(this);

        // top panel
        JLabel name = new JLabel("ChatUofT > People");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Clubs: The heart of UofT's campus");
        JPanel titlePanel = new JPanel();
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));


        searchBar = new JTextField(20);
        JLabel searchPrompt = new JLabel("Find a club:");
        searchPrompt.setFont(new Font("Helvetica", Font.BOLD, 20));
        searchBar.setFont(new Font("Helvetica", Font.PLAIN, 20));
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        LabelTextPanel searchBarPanel = new LabelTextPanel(searchPrompt, searchBar, searchButton);

        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        searchBarPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        searchButton.setMargin(new Insets(4, 20, 4, 20));
        searchBar.setMargin(new Insets(10, 20, 10, 20));

        JPanel titleAndSearchPanel = new JPanel();
        titleAndSearchPanel.setLayout(new BoxLayout(titleAndSearchPanel, BoxLayout.Y_AXIS));
        titleAndSearchPanel.add(titlePanel);
        titleAndSearchPanel.add(searchBarPanel);

//        JPanel displayPanel = new JPanel();
//        if (clubsViewModel.getState().getClubToDisplay() != null) {
//            displayPanel.add(new JLabel(clubsViewModel.getState().getClubToDisplay().getName()));
//        } else {
//            displayPanel.add(new JLabel("No club found."));
//        }


        middlePanel.add(titleAndSearchPanel, BorderLayout.NORTH);
        clubInfoField.setText(clubsViewModel.getState().getFoundClubName());
        middlePanel.add(clubInfoField);



        // bottom panel
        GradientPanel bottomPanel = new GradientPanel();
//        JButton me = new JButton("Me");
//        me.setFont(new Font("Helvetica", Font.BOLD, 15));
//        JButton people = new JButton("People");
//        people.setFont(new Font("Helvetica", Font.BOLD, 15));
        JButton home = new JButton("Return Home");
        home.setFont(new Font("Helvetica", Font.BOLD, 15));
        home.setMargin(new Insets(10, 20, 10, 20));
        bottomPanel.add(home);
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
                            clubsController.switchToLandingView();
                            clubsViewModel.firePropertyChange();
                        }
                    }
                }
        );

        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            final ClubsState currentState = clubsViewModel.getState();

                            String searchQuery = searchBar.getText();
                            clubsController.execute(searchQuery);
                        }
                    }
                }
        );

        searchBar.getDocument().addDocumentListener(new DocumentListener(){

            private void documentListenerHelper() {
                final ClubsState currentState = clubsViewModel.getState();
                currentState.setFoundClubName(searchBar.getText());
                clubsViewModel.setState(currentState);
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

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("GOT HERE!");
        final ClubsState state = (ClubsState) evt.getNewValue();
        setFields(state);

        clubInfoField.setText(state.getFoundClubName());
    }
    private void setFields(ClubsState state) {
        clubInfoField.setText(state.getFoundClubName());
    }

    public void setClubsController(ClubsController clubsController) {
        this.clubsController = clubsController;
    }
}
