package entity;

public class CommentFactory{
    public Comment create(int comment_id, String comment_body, int likes){ return new Comment(comment_id, comment_body, likes); }
}