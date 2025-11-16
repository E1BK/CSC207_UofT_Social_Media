package interface_adapter.view_post;

import use_case.view_post.ViewPostInputBoundary;
import use_case.view_post.ViewPostInputData;

public class ViewPostController {

    private final ViewPostInputBoundary interactor;

    public ViewPostController(ViewPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Call this when the user selects/clicks a post.
     * postId should uniquely identify the post (e.g., "1", "2", ...).
     */
    public void execute(String postId) {
        ViewPostInputData inputData = new ViewPostInputData(postId);
        interactor.execute(inputData);
    }
}
