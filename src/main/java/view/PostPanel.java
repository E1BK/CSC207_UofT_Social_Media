// hasan, ioane
package view;

import entity.Post;
import interface_adapter.view_post.ViewPostController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PostPanel extends JPanel {

    public JPanel panel = new JPanel();

    private final Post post;

    private ViewPostController viewPostController;

    public PostPanel(Post post) {
        this.post = post;

        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        panel.setPreferredSize(new Dimension(300, 200));

        JPanel post1header = new JPanel(new BorderLayout());
        post1header.setBackground(Color.white);

        JLabel post1title = new JLabel(post.getTitle());
        post1title.setFont(new Font("Helvetica", Font.BOLD, 20));

        JLabel post1username = new JLabel("by " + post.getUsername());
        post1username.setFont(new Font("Helvetica", Font.ITALIC, 15));
        post1username.setForeground(Color.GRAY);

        JLabel post1date = new JLabel(post.getPost_date());
        post1date.setFont(new Font("Helvetica", Font.ITALIC, 15));
        post1date.setForeground(Color.GRAY);

        JButton viewButton = new JButton("View");
        viewButton.setFont(new Font("Helvetica", Font.PLAIN, 12));

        viewButton.addActionListener(e -> {
            if (viewPostController != null) {
                viewPostController.viewPost("E1", 1);
            } else {
                System.err.println("ViewPostController not set in PostPanel");
            }
        });

        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setOpaque(false);
        titleRow.add(post1title, BorderLayout.WEST);
        titleRow.add(viewButton, BorderLayout.EAST);

        post1header.add(titleRow, BorderLayout.NORTH);
        post1header.add(post1username, BorderLayout.CENTER);
        post1header.add(post1date, BorderLayout.SOUTH);

        JTextArea post1body = new JTextArea(post.getBody());
        post1body.setPreferredSize(new Dimension(200, 150));
        post1body.setMaximumSize(new Dimension(200, 150));
        post1body.setMinimumSize(new Dimension(200, 150));

        post1body.setLineWrap(true);
        post1body.setWrapStyleWord(true);
        post1body.setEditable(false);
        post1body.setFont(new Font("Helvetica", Font.PLAIN, 15));
        post1body.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        JLabel post1numComments = new JLabel("Comments: " + post.getComments().size());
        post1numComments.setFont(new Font("Helvetica", Font.ITALIC, 15));
        post1numComments.setForeground(Color.GRAY);

        panel.add(post1header, BorderLayout.NORTH);
        panel.add(post1body, BorderLayout.CENTER);
        panel.add(post1numComments, BorderLayout.SOUTH);
    }

    public void setViewPostController(ViewPostController controller) {
        this.viewPostController = controller;
    }
}
