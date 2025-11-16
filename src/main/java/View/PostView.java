package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostView extends JPanel implements ActionListener, PropertyChangeListener {

    // Post labels
    private final JLabel postTitle;
    private final JLabel postBody;

    // Top button
    private final JButton homeButton;

    // Comment input
    private final JTextField commentInput;
    private final JButton postCommentButton;

    // Comments (3 random)
    private final JLabel commentLabel1;
    private final JLabel commentLabel2;
    private final JLabel commentLabel3;

    // Store all comments so we can choose 3 random
    private List<String> allComments = new ArrayList<>();

    public PostView() {
        // Top: Home + "Post"
        final JPanel titlePanel = new JPanel();
        homeButton = new JButton("Home");
        final JLabel titleInfo = new JLabel("Post");
        titlePanel.add(homeButton);
        titlePanel.add(titleInfo);

        // Post title and body
        final JPanel postPanel = new JPanel();
        final JLabel postTitleInfo = new JLabel("Title: ");
        postTitle = new JLabel("Post Title");
        postPanel.add(postTitleInfo);
        postPanel.add(postTitle);

        final JPanel bodyPanel = new JPanel();
        final JLabel bodyInfo = new JLabel("Body: ");
        postBody = new JLabel("Post body goes here");
        bodyPanel.add(bodyInfo);
        bodyPanel.add(postBody);

        // Comment input
        final JPanel commentInputPanel = new JPanel();
        final JLabel commentInfo = new JLabel("Comment: ");
        commentInput = new JTextField(20);
        postCommentButton = new JButton("Post Comment");
        commentInputPanel.add(commentInfo);
        commentInputPanel.add(commentInput);
        commentInputPanel.add(postCommentButton);

        // 3 comment labels
        final JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        final JLabel commentsTitle = new JLabel("Comments:");
        commentLabel1 = new JLabel("");
        commentLabel2 = new JLabel("");
        commentLabel3 = new JLabel("");
        commentsPanel.add(commentsTitle);
        commentsPanel.add(commentLabel1);
        commentsPanel.add(commentLabel2);
        commentsPanel.add(commentLabel3);

        // Main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titlePanel);
        add(postPanel);
        add(bodyPanel);
        add(commentInputPanel);
        add(commentsPanel);
    }

    // For quick testing
    public static void main(String[] args) {
        JFrame frame = new JFrame("Post View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PostView view = new PostView();
        view.setPost("Hello UofT", "Welcome to our social media app!");

        List<String> comments = new ArrayList<>();
        comments.add("Nice post!");
        comments.add("Cool!");
        comments.add("Love this.");
        comments.add("This is another comment.");
        view.setComments(comments);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    // -------- Methods for presenter / controller --------

    public void setPost(String title, String body) {
        postTitle.setText(title);
        postBody.setText(body);
    }

    /** Presenter calls this with ALL comments for the post. */
    public void setComments(List<String> comments) {
        allComments = new ArrayList<>(comments);
        showThreeRandomComments();
    }

    public String getCommentInputText() {
        return commentInput.getText();
    }

    public void clearCommentInput() {
        commentInput.setText("");
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getPostCommentButton() {
        return postCommentButton;
    }

    // -------- Internal: choose 3 random comments and show them --------

    private void showThreeRandomComments() {
        // Clear labels first
        commentLabel1.setText("");
        commentLabel2.setText("");
        commentLabel3.setText("");

        if (allComments.isEmpty()) {
            return;
        }

        List<String> copy = new ArrayList<>(allComments);
        Collections.shuffle(copy);

        if (copy.size() > 0) commentLabel1.setText(copy.get(0));
        if (copy.size() > 1) commentLabel2.setText(copy.get(1));
        if (copy.size() > 2) commentLabel3.setText(copy.get(2));
    }

    // -------- Required by interfaces (can wire later) --------

    @Override
    public void actionPerformed(ActionEvent e) {
        // to implement: connect buttons to controllers if needed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // to implement: update from ViewModel if you use one
    }
}
