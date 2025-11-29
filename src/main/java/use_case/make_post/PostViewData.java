package use_case.make_post;

import entity.Comment;

import java.util.ArrayList;

public class PostViewData {
    private final String username;
    private final int post_id;
    private final String title;
    private final String body;
    private final String post_date;
    private final ArrayList<Comment> comments;

    public PostViewData(String username, int post_id, String title, String body, String post_date, ArrayList<Comment> comments) {
        this.username = username;
        this.post_id = post_id;
        this.title = title;
        this.body = body;
        this.post_date = post_date;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPost_date() {
        return post_date;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
