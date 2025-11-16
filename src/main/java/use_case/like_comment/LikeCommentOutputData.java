package use_case.like_comment;

public class LikeCommentOutputData {

    private final String postId;
    private final int commentId;
    private final String username;    // user who liked
    private final int newLikeCount;

    public LikeCommentOutputData(String postId, int commentId,
                                 String username, int newLikeCount) {
        this.postId = postId;
        this.commentId = commentId;
        this.username = username;
        this.newLikeCount = newLikeCount;
    }

    public String getPostId() { return postId; }
    public int getCommentId() { return commentId; }
    public String getUsername() { return username; }
    public int getNewLikeCount() { return newLikeCount; }
}
