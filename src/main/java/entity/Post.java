package entity;

import java.util.ArrayList;


public class Post{
    private final String username;
    private final int post_id;
    private final String title;
    private final String body;
    private final ArrayList<Comment> comments;
    // other attributes that should be added later:
    // createdAt
    // likes

    /**
     * Creates a new Post associated with the given user and specifications.
     * @param post_id the unique identifier of this post
     * @param username the username
     * @param title the title
     * @param body the body of content
     * @param comments any comments that this post has
     */
    public Post(int post_id, String username, String title, String body, ArrayList<Comment> comments){
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getUsername() {return username;}

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