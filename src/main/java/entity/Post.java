package entity;

import java.util.ArrayList;

public class Post{
    private final int post_id;
    private final String title;
    private final String body;
    private final ArrayList<Comment> comments;

    public Post(int post_id, String title, String body, ArrayList<Comment> comments){
        this.post_id = post_id;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}