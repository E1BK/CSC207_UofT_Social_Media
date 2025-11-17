package entity;

import java.util.ArrayList;

public class PostFactory{

    public Post create(
            String username,
            int post_id,
            String title,
            String body,
            String post_date,
            ArrayList<Comment> comments) {

        return new Post(post_id, username, title, body, post_date, comments);
    }
}