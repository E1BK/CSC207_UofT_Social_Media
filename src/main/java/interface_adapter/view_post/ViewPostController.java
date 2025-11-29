// Ioane
package interface_adapter.view_post;

import use_case.add_comment.AddCommentInputBoundary;
import use_case.add_comment.AddCommentInputData;
import use_case.view_post.ViewPostInputBoundary;
import use_case.view_post.ViewPostInputData;

public class ViewPostController {

    private final ViewPostInputBoundary viewPostInteractor;
    private final AddCommentInputBoundary addCommentInteractor;

    public ViewPostController(ViewPostInputBoundary viewPostInteractor,
                              AddCommentInputBoundary addCommentInteractor) {
        this.viewPostInteractor = viewPostInteractor;
        this.addCommentInteractor = addCommentInteractor;
    }

    public void viewPost(String username, int postId) {
        ViewPostInputData input = new ViewPostInputData(username, postId);
        viewPostInteractor.execute(input);
    }

    public void addComment(String username, int postId, String commentBody) {
        AddCommentInputData input = new AddCommentInputData(username, postId, commentBody);
        addCommentInteractor.execute(input);
    }
}