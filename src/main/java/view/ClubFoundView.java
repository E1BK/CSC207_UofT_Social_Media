//package view;
//
//import app.GradientPanel;
//import entity.Club;
//import interface_adapter.clubs.ClubsController;
//import interface_adapter.clubs.ClubsViewModel;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//public class ClubFoundView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    private ClubsViewModel clubsViewModel;
//    private Club foundClub;
//    private ClubsController clubsController = null;
//
//    public ClubFoundView(ClubsViewModel clubsViewModel, Club foundClub) {
//        this.clubsViewModel = clubsViewModel;
//        this.clubsViewModel.addPropertyChangeListener(this);
//        this.foundClub = foundClub;
//
//        // top panel
//        JLabel name = new JLabel("ChatUofT > People");
//        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
//        GradientPanel topPanel = new GradientPanel();
//        topPanel.add(name);
//        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
//
//        // middle panel
//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new BorderLayout());
//
//        JLabel title = new JLabel("Clubs: The heart of UofT's campus");
//        JPanel titlePanel = new JPanel();
//        title.setFont(new Font("Helvetica", Font.BOLD, 40));
//        titlePanel.add(title);
//        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
//
//
//        final JTextField searchBar = new JTextField(20);
//        JLabel searchPrompt = new JLabel("Find a club:");
//        searchPrompt.setFont(new Font("Helvetica", Font.BOLD, 20));
//        searchBar.setFont(new Font("Helvetica", Font.PLAIN, 20));
//        JButton searchButton = new JButton("Search");
//        searchButton.setFont(new Font("Helvetica", Font.BOLD, 20));
//        LabelTextPanel searchBarPanel = new LabelTextPanel(searchPrompt, searchBar, searchButton);
//
//        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
//        searchBarPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
//        searchButton.setMargin(new Insets(4, 20, 4, 20));
//        searchBar.setMargin(new Insets(10, 20, 10, 20));
//
//        JPanel titleAndSearchPanel = new JPanel();
//        titleAndSearchPanel.setLayout(new BoxLayout(titleAndSearchPanel, BoxLayout.Y_AXIS));
//        titleAndSearchPanel.add(titlePanel);
//        titleAndSearchPanel.add(searchBarPanel);
//
//        JPanel displayPanel = new JPanel();
//        displayPanel.add(new JLabel(foundClub.getName()));
//
//        middlePanel.add(titleAndSearchPanel, BorderLayout.NORTH);
//        middlePanel.add(displayPanel);
//
//
//
//        // bottom panel
//        GradientPanel bottomPanel = new GradientPanel();
////        JButton me = new JButton("Me");
////        me.setFont(new Font("Helvetica", Font.BOLD, 15));
////        JButton people = new JButton("People");
////        people.setFont(new Font("Helvetica", Font.BOLD, 15));
//        JButton home = new JButton("Return Home");
//        home.setFont(new Font("Helvetica", Font.BOLD, 15));
//        home.setMargin(new Insets(10, 20, 10, 20));
//        bottomPanel.add(home);
//        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
//
//        this.setLayout(new BorderLayout());
//        this.add(topPanel, BorderLayout.NORTH);
//        this.add(middlePanel, BorderLayout.CENTER);
//        this.add(bottomPanel, BorderLayout.SOUTH);
//
//        // action listeners:
//        home.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(home)) {
//                            clubsController.switchToLandingView();
//                        }
//                    }
//                }
//        );
//
//        searchButton.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(searchButton)) {
//                            String searchQuery = searchBar.getText();
//                            clubsController.execute(searchQuery);
//                        }
//                    }
//                }
//        );
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
//
//
//
//}
