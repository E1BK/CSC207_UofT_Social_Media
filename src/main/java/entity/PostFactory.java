package entity;

public class PostFactory{
    public Post create(String post_id, String title, String body){ return new Post(post_id, title, body); }
}