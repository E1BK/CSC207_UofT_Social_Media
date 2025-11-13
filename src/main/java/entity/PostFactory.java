package entity;

import java.util.ArrayList;

public class PostFactory{
    public Post create(int post_id, String title, String body, ArrayList<Comment> comments){ return new Post(post_id, title, body, comments); }
}