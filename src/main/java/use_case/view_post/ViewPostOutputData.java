// Ioane
package use_case.view_post;

public class ViewPostOutputData {

    private final String username;
    private final int postId;
    private final String postTitle;
    private final String postBody;
    private final int[] commentIds;
    private final String[] commentBodies;
    private final int[] commentLikes;

    public ViewPostOutputData(String username,
                              int postId,
                              String postTitle,
                              String postBody,
                              int[] commentIds,
                              String[] commentBodies,
                              int[] commentLikes) {
        this.username = username;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.commentIds = commentIds;
        this.commentBodies = commentBodies;
        this.commentLikes = commentLikes;
    }

    public String getUsername() { return username; }

    public int getPostId() { return postId; }

    public String getPostTitle() { return postTitle; }

    public String getPostBody() { return postBody; }

    public int[] getCommentIds() { return commentIds; }

    public String[] getCommentBodies() { return commentBodies; }

    public int[] getCommentLikes() { return commentLikes; }
}