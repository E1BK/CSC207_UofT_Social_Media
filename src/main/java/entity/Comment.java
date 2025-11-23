package entity;

public class Comment {
    private final int comment_id;
    private final String comment_body;
    private final String comment_date;
    private final int likes;

    public Comment(int comment_id, String body, String commentDate, int likes) {
        this.comment_id =  comment_id;
        this.comment_body = body;
        this.comment_date = commentDate;
        this.likes = likes;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getComment_body() {
        return comment_body;
    }

    public String getComment_date() {
        return comment_date;
    }

    public int getLikes() {
        return likes;
    }
}