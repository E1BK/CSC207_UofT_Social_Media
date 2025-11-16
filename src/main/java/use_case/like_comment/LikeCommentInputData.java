package use_case.like_comment;

public class LikeCommentInputData {

    private final String username;  // user who is liking
    private final String postId;    // post that holds the comment
    private final int commentId;    // ID of the comment inside that post

    public LikeCommentInputData(String username, String postId, int commentId) {
        this.username = username;
        this.postId = postId;
        this.commentId = commentId;
    }

    public String getUsername() { return username; }
    public String getPostId() { return postId; }
    public int getCommentId() { return commentId; }
}
