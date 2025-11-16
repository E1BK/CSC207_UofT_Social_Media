package use_case.view_post;

public class ViewPostInputData {
    private final String postId;

    public ViewPostInputData(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }
}
