package interface_adapter.make_post;

public class MakePostState {

    private String newpost_username = "";
    private String newpost_title = "";
    private String newpost_body = "";
    private String postError = "";

    public MakePostState() {
    }

    public MakePostState(MakePostState copy) {
        newpost_username = copy.newpost_username;
        newpost_title = copy.newpost_title;
        newpost_body = copy.newpost_body;
        postError = copy.postError;
    }

    public String getNewpost_title() { return newpost_title; }

    public void setNewpost_title(String newpost_title) { this.newpost_title = newpost_title; }

    public String getNewpost_body() { return newpost_body; }

    public void setNewpost_body(String newpost_body) { this.newpost_body = newpost_body; }

    public String getNewpost_username() {
        return newpost_username;
    }

    public void setNewpost_username(String newpost_username) {
        this.newpost_username = newpost_username;
    }

    public String getpostError() { return postError; }

    public void setpostError(String postError) { this.postError = postError; }
}
