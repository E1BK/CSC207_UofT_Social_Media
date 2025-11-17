package entity;

public class CommentFactory{
    public Comment create(int comment_id, String comment_body, String comment_date, int likes){
        return new Comment(comment_id, comment_body, comment_date, likes);
    }
}