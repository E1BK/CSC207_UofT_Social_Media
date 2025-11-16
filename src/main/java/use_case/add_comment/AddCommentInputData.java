package use_case.add_comment;

public class AddCommentInputData {
    private final String username;   // the user who is commenting
    private final String postId;     // the post to comment on
    private final String commentBody;

    public AddCommentInputData(String username, String postId, String commentBody) {
        this.username = username;
        this.postId = postId;
        this.commentBody = commentBody;
    }

    public String getUsername() { return username; }
    public String getPostId() { return postId; }
    public String getCommentBody() { return commentBody; }
}
