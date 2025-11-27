//import app.GradientPanel;
//import interface_adapter.landing.LandingViewModel;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;this.landingViewModel = landingViewModel;
//        landingViewModel.addPropertyChangeListener(this);
//
//// top panel
//JLabel name = new JLabel("ChatUofT");
//        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
//GradientPanel topPanel = new GradientPanel();
//        topPanel.add(name);
//        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
//
//// middle panel
//JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new BorderLayout());
//
//JLabel title = new JLabel("What's happening at UofT?");
//JPanel titlePanel = new JPanel();
//        title.setFont(new Font("Helvetica", Font.BOLD, 40));
//        titlePanel.add(title);
//        titlePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
//
//JTextField postTitle = new JTextField(20);
//        postTitle.setFont(new Font("Helvetica", Font.PLAIN, 20));
//        postTitle.setMargin(new Insets(10, 20, 10, 20));
//        postTitle.setMaximumSize(new Dimension(300, 30));
//        postTitle.setMinimumSize(new Dimension(300, 30));
//
//postBody = new JTextArea();
//        postBody.setFont(new Font("Helvetica", Font.PLAIN, 20));
//        postBody.setMinimumSize(new Dimension(300, 200));
//        postBody.setMaximumSize(new Dimension(300, 200));
//        postBody.setMargin(new Insets(5, 5, 5, 5));
//
//makePost = new JButton(LandingViewModel.MAKE_POST_BUTTON_LABEL);
//        makePost.setFont(new Font("Helvetica", Font.BOLD, 20));
//        makePost.setMargin(new Insets(10, 20, 10, 20));
//        postBody.setAlignmentX(Component.CENTER_ALIGNMENT);
//        makePost.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//JPanel gapPanel = new JPanel();
//        gapPanel.setMinimumSize(new Dimension(20, 4));
//        gapPanel.setMaximumSize(new Dimension(20, 4));
//
//JPanel postPanel = new JPanel();
//        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
//        postPanel.add(postTitle);
//        postPanel.add(gapPanel);
//        postPanel.add(postBody);
//        postPanel.add(makePost);
//        postPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
//
//        middlePanel.add(titlePanel, BorderLayout.NORTH);
//        middlePanel.add(postPanel, BorderLayout.CENTER);
//
//// bottom panel
//GradientPanel bottomPanel = new GradientPanel();
//me = new JButton(LandingViewModel.ME_BUTTON_LABEL);
//        me.setFont(new Font("Helvetica", Font.BOLD, 15));
//people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
//        people.setFont(new Font("Helvetica", Font.BOLD, 15));
//home = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
//        home.setFont(new Font("Helvetica", Font.BOLD, 15));
//
//        me.setMargin(new Insets(10, 20, 10, 20));
//        home.setMargin(new Insets(10, 20, 10, 20));
//        people.setMargin(new Insets(10, 20, 10, 20));
//
//
//        bottomPanel.add(home);
//        bottomPanel.add(people);
//        bottomPanel.add(me);
//        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
//
//        //
//
//        this.setLayout(new BorderLayout());
//        this.add(topPanel, BorderLayout.NORTH);
//        this.add(middlePanel, BorderLayout.CENTER);
//        this.add(bottomPanel, BorderLayout.SOUTH);