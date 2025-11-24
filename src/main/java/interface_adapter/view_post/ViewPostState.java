// Ioane
package interface_adapter.view_post;

public class ViewPostState {

    private String postTitle = "";
    private String postBody = "";
    private int[] commentIds = new int[0];
    private String[] commentBodies = new String[0];
    private int[] commentLikes = new int[0];
    private String errorMessage = "";

    public ViewPostState() {}

    public ViewPostState(ViewPostState copy) {
        this.postTitle = copy.postTitle;
        this.postBody = copy.postBody;
        this.commentIds = copy.commentIds;
        this.commentBodies = copy.commentBodies;
        this.commentLikes = copy.commentLikes;
        this.errorMessage = copy.errorMessage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int[] getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(int[] commentIds) {
        this.commentIds = commentIds;
    }

    public String[] getCommentBodies() {
        return commentBodies;
    }

    public void setCommentBodies(String[] commentBodies) {
        this.commentBodies = commentBodies;
    }

    public int[] getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(int[] commentLikes) {
        this.commentLikes = commentLikes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

