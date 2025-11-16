package use_case.add_comment;

public class AddCommentOutputData {

    private final String postId;
    private final String username;
    private final String commentBody;

    public AddCommentOutputData(String postId, String username, String commentBody) {
        this.postId = postId;
        this.username = username;
        this.commentBody = commentBody;
    }

    public String getPostId() { return postId; }
    public String getUsername() { return username; }
    public String getCommentBody() { return commentBody; }
}
