package view;

import entity.Post;
import use_case.make_post.PostViewData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This is a "helper" class used to make
 * a panel containing a post
 * This is used to display posts on the Landing Page
 */
public class PostPanel extends JPanel {

    public JPanel panel = new JPanel();

    public PostPanel(PostViewData post) {

        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
//            panel.setMinimumSize(new Dimension(300, 200));
//            panel.setMaximumSize(new Dimension(300, 200));
        panel.setPreferredSize(new Dimension(300, 200));

        JPanel post1header = new JPanel();
        post1header.setBackground(Color.white);
        post1header.setLayout(new BorderLayout());
        JLabel post1title = new JLabel(post.getTitle());
        post1title.setFont(new Font("Helvetica", Font.BOLD, 20));
        JLabel post1username = new JLabel("by " + post.getUsername());
        post1username.setFont(new Font("Helvetica", Font.ITALIC, 15));
        post1username.setForeground(Color.GRAY);
        JLabel post1date = new JLabel(post.getPost_date());
        post1date.setFont(new Font("Helvetica", Font.ITALIC, 15));
        post1date.setForeground(Color.GRAY);
        post1header.add(post1title, BorderLayout.NORTH);
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


}
