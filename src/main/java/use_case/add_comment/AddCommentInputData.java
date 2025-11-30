//Ioane
package use_case.add_comment;

public class AddCommentInputData {

    private final String username;
    private final int postId;
    private final String commentBody;

    public AddCommentInputData(String username, int postId, String commentBody) {
        this.username = username;
        this.postId = postId;
        this.commentBody = commentBody;
    }

    public String getUsername() {
        return username;
    }

    public int getPostId() {
        return postId;
    }

    public String getCommentBody() {
        return commentBody;
    }
}