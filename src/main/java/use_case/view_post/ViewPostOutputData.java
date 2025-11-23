package use_case.view_post;

public class ViewPostOutputData {
    private final String postTitle;
    private final String postBody;
    private final int[] commentIds;
    private final String[] commentBodies;
    private final int[] commentLikes;

    public ViewPostOutputData(String postTitle,
                              String postBody,
                              int[] commentIds,
                              String[] commentBodies,
                              int[] commentLikes) {
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.commentIds = commentIds;
        this.commentBodies = commentBodies;
        this.commentLikes = commentLikes;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public int[] getCommentIds() {
        return commentIds;
    }

    public String[] getCommentBodies() {
        return commentBodies;
    }

    public int[] getCommentLikes() {
        return commentLikes;
    }
}
