package use_case.view_post;

public class ViewPostInputData {
    private final String username;
    private final int postId;

    public ViewPostInputData(String username, int postId) {
        this.username = username;
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public int getPostId() {
        return postId;
    }
}
