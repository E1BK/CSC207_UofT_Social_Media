package view;

import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.view_post.ViewPostController;
import use_case.make_post.PostViewData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

/**
 * Helper panel to render a post card.
 * Used both on Landing (PostViewData) and MyProfile (Map<String, String>).
 */
public class PostPanel extends JPanel {

    public JPanel panel = new JPanel();
    private ViewPostController viewPostController;

    // ---------- Landing page constructor (PostViewData) ----------
    public PostPanel(PostViewData post) {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        panel.setPreferredSize(new Dimension(300, 200));

        // Header (title, username, date)
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.white);

        JLabel titleLabel = new JLabel(post.getTitle());
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        JLabel userLabel = new JLabel("by " + post.getUsername());
        userLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        userLabel.setForeground(Color.GRAY);

        JLabel dateLabel = new JLabel(post.getPost_date());
        dateLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        dateLabel.setForeground(Color.GRAY);

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(userLabel, BorderLayout.CENTER);
        header.add(dateLabel, BorderLayout.SOUTH);

        // Body
        JTextArea bodyArea = new JTextArea(post.getBody());
        bodyArea.setPreferredSize(new Dimension(200, 150));
        bodyArea.setMaximumSize(new Dimension(200, 150));
        bodyArea.setMinimumSize(new Dimension(200, 150));
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        bodyArea.setFont(new Font("Helvetica", Font.PLAIN, 15));
        bodyArea.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        JLabel commentsLabel =
                new JLabel("Comments: " + post.getComments().size());
        commentsLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        commentsLabel.setForeground(Color.GRAY);

        // View button
        JButton viewButton = new JButton("View");
        viewButton.setMargin(new Insets(2, 10, 2, 10));
        viewButton.addActionListener(e -> {
            if (viewPostController != null) {
                viewPostController.viewPost(post.getUsername(), post.getPost_id());
            } else {
                System.out.println("ViewPostController not set in PostPanel (Landing)");
            }
        });

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setBackground(Color.white);
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(viewButton, BorderLayout.EAST);

        panel.add(headerWrapper, BorderLayout.NORTH);
        panel.add(bodyArea, BorderLayout.CENTER);
        panel.add(commentsLabel, BorderLayout.SOUTH);
    }

    // ---------- MyProfile constructor (Map-based) ----------
    public PostPanel(Map post) {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        panel.setPreferredSize(new Dimension(300, 200));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.white);

        String title = String.valueOf(post.get(MyProfileViewModel.TITLE));
        String username = String.valueOf(post.get(MyProfileViewModel.USERNAME));
        String dateRaw = String.valueOf(post.get(MyProfileViewModel.DATE));
        String date = dateRaw;
        int tIndex = dateRaw.indexOf("T");
        if (tIndex > 0) {
            date = dateRaw.substring(0, tIndex);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        JLabel userLabel = new JLabel("by " + username);
        userLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        userLabel.setForeground(Color.GRAY);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        dateLabel.setForeground(Color.GRAY);

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(userLabel, BorderLayout.CENTER);
        header.add(dateLabel, BorderLayout.SOUTH);

        String bodyText = String.valueOf(post.get(MyProfileViewModel.BODY));
        JTextArea bodyArea = new JTextArea(bodyText);
        bodyArea.setPreferredSize(new Dimension(200, 150));
        bodyArea.setMaximumSize(new Dimension(200, 150));
        bodyArea.setMinimumSize(new Dimension(200, 150));
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        bodyArea.setFont(new Font("Helvetica", Font.PLAIN, 15));
        bodyArea.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        String numComments =
                String.valueOf(post.get(MyProfileViewModel.NUM_OF_COMMENTS));
        JLabel commentsLabel = new JLabel("Comments: " + numComments);
        commentsLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        commentsLabel.setForeground(Color.GRAY);

        // View button for MyProfile
        JButton viewButton = new JButton("View");
        viewButton.setMargin(new Insets(2, 10, 2, 10));
        viewButton.addActionListener(e -> {
            if (viewPostController != null) {
                Object idObj = post.get(MyProfileViewModel.ID);
                int postId;
                try {
                    if (idObj instanceof Number n) {
                        postId = n.intValue();
                    } else {
                        postId = Integer.parseInt(String.valueOf(idObj));
                    }
                    viewPostController.viewPost(username, postId);
                } catch (Exception ex) {
                    System.out.println("Invalid post id in MyProfile PostPanel: " + idObj);
                }
            } else {
                System.out.println("ViewPostController not set in PostPanel (MyProfile)");
            }
        });

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setBackground(Color.white);
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(viewButton, BorderLayout.EAST);

        panel.add(headerWrapper, BorderLayout.NORTH);
        panel.add(bodyArea, BorderLayout.CENTER);
        panel.add(commentsLabel, BorderLayout.SOUTH);
    }

    // ---------- Wiring ----------
    public void setViewPostController(ViewPostController controller) {
        this.viewPostController = controller;
    }
}
