package entity;

public class Comment{
    private final String comment_id;
    private final String body;
    private final int likes;

    public Comment(String comment_id, String body, int likes){
        this.comment_id = comment_id;
        this.body = body;
        this.likes = likes;

    }
}
