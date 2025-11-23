package view;

import app.GradientPanel;
import interface_adapter.view_post.ViewPostController;
import interface_adapter.view_post.ViewPostState;
import interface_adapter.view_post.ViewPostViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PostView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "post";

    private final ViewPostViewModel viewModel;
    private ViewPostController controller;

    private JLabel titleLabel;
    private JTextArea bodyArea;

    private final JLabel[] commentLabels = new JLabel[3];
    private final JButton[] likeButtons = new JButton[3];
    private final JLabel[] likeCountLabels = new JLabel[3];
    private final int[] commentIds = new int[3];

    public PostView(ViewPostViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Post title
        GradientPanel topPanel = new GradientPanel();
        topPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Post Title");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Post body + comments
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        bodyArea = new JTextArea(5, 40);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        bodyArea.setFont(new Font("Helvetica", Font.PLAIN, 16));
        bodyArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        centerPanel.add(new JScrollPane(bodyArea));
        centerPanel.add(Box.createVerticalStrut(20));

        for (int i = 0; i < 3; i++) {
            JPanel commentRow = new JPanel(new BorderLayout());
            commentRow.setBorder(new EmptyBorder(5, 0, 5, 0));

            commentLabels[i] = new JLabel("Comment " + (i + 1));
            likeButtons[i] = new JButton("Like");
            likeButtons[i].addActionListener(this);
            likeCountLabels[i] = new JLabel("0");

            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.add(likeButtons[i]);
            rightPanel.add(likeCountLabels[i]);

            commentRow.add(commentLabels[i], BorderLayout.CENTER);
            commentRow.add(rightPanel, BorderLayout.EAST);

            centerPanel.add(commentRow);
        }

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setViewPostController(ViewPostController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return viewName;
    }

    private void updateFromState(ViewPostState state) {
        titleLabel.setText(state.getPostTitle());
        bodyArea.setText(state.getPostBody());

        int n = Math.min(3, state.getCommentBodies().length);

        for (int i = 0; i < 3; i++) {
            if (i < n) {
                commentIds[i] = state.getCommentIds()[i];
                commentLabels[i].setText(state.getCommentBodies()[i]);
                likeCountLabels[i].setText(String.valueOf(state.getCommentLikes()[i]));
                likeButtons[i].setEnabled(true);
            } else {
                commentIds[i] = -1;
                commentLabels[i].setText("");
                likeCountLabels[i].setText("");
                likeButtons[i].setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < likeButtons.length; i++) {
            if (e.getSource() == likeButtons[i] && commentIds[i] != -1) {

                int currentLikes;
                try {
                    currentLikes = Integer.parseInt(likeCountLabels[i].getText());
                } catch (NumberFormatException ex) {
                    currentLikes = 0;
                }
                currentLikes++;
                likeCountLabels[i].setText(String.valueOf(currentLikes));
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName()) && evt.getNewValue() instanceof ViewPostState) {
            ViewPostState state = (ViewPostState) evt.getNewValue();
            updateFromState(state);
        }
    }
}
