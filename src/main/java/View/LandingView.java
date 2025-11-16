// hasan

package View;

import interface_adapter.landing.LandingState;
import interface_adapter.landing.MakePostController;
import interface_adapter.landing.LandingViewModel;

import javax.swing.*;
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

    private final JLabel title;
    private final JLabel postBodyPrompt;
    private final JTextField postBody;
    private final JButton makePost;

    private final JButton me;
    private final JButton people;
    private final JButton posts;


    public LandingView(LandingViewModel landingViewModel) {

        this.landingViewModel = landingViewModel;
        landingViewModel.addPropertyChangeListener(this);

        title = new JLabel(LandingViewModel.TITLE_LABEL);
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        postBodyPrompt = new JLabel("What's happening at UofT today?");
        postBodyPrompt.setFont(new Font("Helvetica", Font.PLAIN, 25));
        postBody = new JTextField();
        postBody.setFont(new Font("Helvetica", Font.PLAIN, 12));
        postBody.setMinimumSize(new Dimension(300, 200));
        postBody.setMaximumSize(new Dimension(300, 200));
        makePost = new JButton(LandingViewModel.MAKE_POST_BUTTON_LABEL);
        makePost.setFont(new Font("Helvetica", Font.PLAIN, 15));
        me = new JButton(LandingViewModel.ME_BUTTON_LABEL);
        me.setFont(new Font("Helvetica", Font.PLAIN, 15));
        people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
        people.setFont(new Font("Helvetica", Font.PLAIN, 15));
        posts = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
        posts.setFont(new Font("Helvetica", Font.PLAIN, 15));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        postBodyPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        postBody.setAlignmentX(Component.CENTER_ALIGNMENT);
        makePost.setAlignmentX(Component.CENTER_ALIGNMENT);

        me.setMargin(new Insets(10, 20, 10, 20));
        makePost.setMargin(new Insets(10, 20, 10, 20));
        people.setMargin(new Insets(10, 20, 10, 20));
        posts.setMargin(new Insets(10, 20, 10, 20));

//        me.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        final LabelTextPanel postTitleInfo = new LabelTextPanel(new JLabel("Post Title:"), postTitleField);
//        final LabelTextPanel postBodyInfo = new LabelTextPanel(new JLabel("Post Body:"), postBodyField, true);
//
//        final JPanel topButtons = new JPanel();
//        makePost = new JButton(LandingViewModel.MAKE_POST_BUTTON_LABEL);
//        topButtons.add(makePost);
//
//        final JPanel bottomButtons = new JPanel();
//        me = new JButton(LandingViewModel.ME_BUTTON_LABEL);
//        people = new JButton(LandingViewModel.PEOPLE_BUTTON_LABEL);
//        posts = new JButton(LandingViewModel.POSTS_BUTTON_LABEL);
//        bottomButtons.add(posts);
//        bottomButtons.add(me);
//        bottomButtons.add(people);

        me.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(me)) {
//                            final LoginState currentState = loginViewModel.getState();
//
//                            loginController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
                            System.out.println("CLICKED 'ME'!");
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


        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.add(postBodyPrompt);

        postPanel.add(postBody);
        postPanel.add(makePost);



        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(posts);
        buttonsPanel.add(people);
        buttonsPanel.add(me);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 10, 0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buttonsPanel);

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));


        this.add(titlePanel, BorderLayout.NORTH);
        this.add(postPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);


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
        final LandingState state = (LandingState) evt.getNewValue();
    }

    public void setMakePostController(MakePostController controller) {
        this.makePostController = controller;
    }
}
