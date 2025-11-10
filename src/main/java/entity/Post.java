package entity;

public class Post{
    private final String post_id;
    private final String title;
    private final String body;

    public Post(String post_id, String title, String body){
        this.post_id = post_id;
        this.title = title;
        this.body = body;
    }
}