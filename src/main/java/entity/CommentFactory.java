package entity;

public class CommentFactory{
    public Comment create(String comment_id, String body, int likes){
        return new Comment(comment_id, body, likes);
    }
}
