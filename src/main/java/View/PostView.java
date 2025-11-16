package View;

import entity.Comment;

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
    private final JButton addCommentButton;

    // Labels + Like buttons for 3 random comments
    private final JLabel commentLabel1;
    private final JLabel commentLabel2;
    private final JLabel commentLabel3;

    private final JButton likeButton1;
    private final JButton likeButton2;
    private final JButton likeButton3;

    // Comments currently displayed in the 3 slots
    private List<Comment> displayedComments = new ArrayList<>();

    public PostView() {
        // ----- TOP: Home + "Post" -----
        final JPanel titlePanel = new JPanel();
        homeButton = new JButton("Home");
        final JLabel titleInfo = new JLabel("Post");
        titlePanel.add(homeButton);
        titlePanel.add(titleInfo);

        // ----- POST TITLE -----
        final JPanel postTitlePanel = new JPanel();
        final JLabel postTitleInfo = new JLabel("Title: ");
        postTitle = new JLabel("Post Title");
        postTitlePanel.add(postTitleInfo);
        postTitlePanel.add(postTitle);

        // ----- POST BODY -----
        final JPanel postBodyPanel = new JPanel();
        final JLabel postBodyInfo = new JLabel("Body: ");
        postBody = new JLabel("Post body goes here");
        postBodyPanel.add(postBodyInfo);
        postBodyPanel.add(postBody);

        // ----- COMMENT INPUT -----
        final JPanel commentInputPanel = new JPanel();
        final JLabel commentInfo = new JLabel("Comment: ");
        commentInput = new JTextField(20);
        addCommentButton = new JButton("Add Comment");
        commentInputPanel.add(commentInfo);
        commentInputPanel.add(commentInput);
        commentInputPanel.add(addCommentButton);

        // ----- 3 COMMENTS + LIKE BUTTONS -----
        final JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        final JLabel commentsTitle = new JLabel("Comments:");

        // Row 1
        final JPanel row1 = new JPanel();
        commentLabel1 = new JLabel("");
        likeButton1 = new JButton("Like");
        row1.add(commentLabel1);
        row1.add(likeButton1);

        // Row 2
        final JPanel row2 = new JPanel();
        commentLabel2 = new JLabel("");
        likeButton2 = new JButton("Like");
        row2.add(commentLabel2);
        row2.add(likeButton2);

        // Row 3
        final JPanel row3 = new JPanel();
        commentLabel3 = new JLabel("");
        likeButton3 = new JButton("Like");
        row3.add(commentLabel3);
        row3.add(likeButton3);

        commentsPanel.add(commentsTitle);
        commentsPanel.add(row1);
        commentsPanel.add(row2);
        commentsPanel.add(row3);

        // ----- MAIN LAYOUT -----
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titlePanel);
        add(postTitlePanel);
        add(postBodyPanel);
        add(commentInputPanel);
        add(commentsPanel);
    }

    // For quick testing (optional)
    public static void main(String[] args) {
        JFrame frame = new JFrame("Post View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PostView view = new PostView();
        view.setPost("Hello UofT", "Welcome to our social media app!");

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1, "Nice post!", 3));
        comments.add(new Comment(2, "Cool idea.", 5));
        comments.add(new Comment(3, "Love this!", 2));
        comments.add(new Comment(4, "Another comment here.", 10));
        view.setComments(comments);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    // ===== Methods for presenter / controller =====

    public void setPost(String title, String body) {
        postTitle.setText(title);
        postBody.setText(body);
    }

    /**
     * Presenter calls this with ALL comments for the post.
     * The view will randomly pick up to 3 and display them with likes.
     */
    public void setComments(List<Comment> allComments) {
        // Pick up to 3 random comments
        List<Comment> copy = new ArrayList<>(allComments);
        Collections.shuffle(copy);

        displayedComments = new ArrayList<>();
        for (int i = 0; i < 3 && i < copy.size(); i++) {
            displayedComments.add(copy.get(i));
        }

        // Clear all first
        commentLabel1.setText("");
        commentLabel2.setText("");
        commentLabel3.setText("");

        likeButton1.setEnabled(false);
        likeButton2.setEnabled(false);
        likeButton3.setEnabled(false);

        // Fill in according to how many we have
        if (displayedComments.size() > 0) {
            Comment c = displayedComments.get(0);
            commentLabel1.setText(c.getBody() + " (likes: " + c.getLikes() + ")");
            likeButton1.setEnabled(true);
        }
        if (displayedComments.size() > 1) {
            Comment c = displayedComments.get(1);
            commentLabel2.setText(c.getBody() + " (likes: " + c.getLikes() + ")");
            likeButton2.setEnabled(true);
        }
        if (displayedComments.size() > 2) {
            Comment c = displayedComments.get(2);
            commentLabel3.setText(c.getBody() + " (likes: " + c.getLikes() + ")");
            likeButton3.setEnabled(true);
        }
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

    public JButton getAddCommentButton() {
        return addCommentButton;
    }

    public JButton getLikeButton1() {
        return likeButton1;
    }

    public JButton getLikeButton2() {
        return likeButton2;
    }

    public JButton getLikeButton3() {
        return likeButton3;
    }

    /**
     * Returns the comment_id of the comment currently displayed
     * in slot index 0, 1, or 2. Use this with LikeCommentController.
     */
    public int getCommentIdAtIndex(int index) {
        if (index < 0 || index >= displayedComments.size()) {
            return -1;
        }
        return displayedComments.get(index).getComment_id();
    }

    // ===== Required by interfaces =====

    @Override
    public void actionPerformed(ActionEvent e) {
        // optional: you can wire actions here instead of outside
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // optional: if you use a ViewModel, update here
    }
}
