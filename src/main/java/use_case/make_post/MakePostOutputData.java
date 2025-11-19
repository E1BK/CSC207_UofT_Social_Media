package use_case.make_post;

public class MakePostOutputData {
    private int post_id;
    private String username;
    private String title;
    private String body;
    private String post_date;

    public MakePostOutputData(int post_id, String username, String title, String body, String post_date) {
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.body = body;
        this.post_date = post_date;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPost_date() {
        return post_date;
    }
}