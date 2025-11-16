package interface_adapter.like_comment;

import use_case.like_comment.LikeCommentInputBoundary;
import use_case.like_comment.LikeCommentInputData;

public class LikeCommentController {

    private final LikeCommentInputBoundary interactor;

    public LikeCommentController(LikeCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Call this when the user clicks "Like" on a specific comment.
     */
    public void execute(String username, String postId, int commentId) {
        LikeCommentInputData inputData =
                new LikeCommentInputData(username, postId, commentId);
        interactor.execute(inputData);
    }
}
