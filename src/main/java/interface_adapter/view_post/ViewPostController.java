// Ioane
package interface_adapter.view_post;

import use_case.view_post.ViewPostInputBoundary;
import use_case.view_post.ViewPostInputData;

public class ViewPostController {

    private final ViewPostInputBoundary interactor;

    public ViewPostController(ViewPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void viewPost(String username, int postId) {
        ViewPostInputData inputData = new ViewPostInputData(username, postId);
        interactor.execute(inputData);
    }

    public void likeComment(String username, int postId, int commentId) {
    }
}

