package entity;

public class Comment {
    private int comment_id;
    private String body;
    private int likes;

    public Comment(int comment_id, String body, int likes) {
        this.comment_id =  comment_id;
        this.body = body;
        this.likes = likes;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getBody() {
        return body;
    }

    public int getLikes() {
        return likes;
    }
}