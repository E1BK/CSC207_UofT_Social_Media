package interface_adapter.add_comment;

import use_case.add_comment.AddCommentInputBoundary;
import use_case.add_comment.AddCommentInputData;

public class AddCommentController {

    private final AddCommentInputBoundary interactor;

    // You might know the username and postId from the current session / context
    public AddCommentController(AddCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Call this when the user presses the "Post Comment" button.
     */
    public void execute(String username, String postId, String commentBody) {
        AddCommentInputData inputData =
                new AddCommentInputData(username, postId, commentBody);
        interactor.execute(inputData);
    }
}
