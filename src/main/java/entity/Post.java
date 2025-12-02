package entity;

import java.util.ArrayList;

public class Post{
    private final String username;
    private final int post_id;
    private final String title;
    private final String body;
    private final String post_date;
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
    public Post(int post_id, String username, String title, String body, String postDate, ArrayList<Comment> comments){
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.body = body;
        this.post_date = postDate;
        this.comments = comments;
    }


    public Post(int post_id, String username, String title, String body, String postDate){
        this.username = username;
        this.post_id = post_id;
        this.title = title;
        this.body = body;
        this.post_date = postDate;
        this.comments = new ArrayList<Comment>();
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

    public String getPost_date() {
        return post_date;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}