package use_case.view_post;

import entity.Post;
import entity.Comment;

import java.util.List;

public class ViewPostInteractor implements ViewPostInputBoundary {

    private final ViewPostDataAccessInterface viewPostDataAccess;
    private final ViewPostOutputBoundary viewPostPresenter;

    public ViewPostInteractor(ViewPostDataAccessInterface viewPostDataAccess,
                              ViewPostOutputBoundary viewPostPresenter) {
        this.viewPostDataAccess = viewPostDataAccess;
        this.viewPostPresenter = viewPostPresenter;
    }

    @Override
    public void execute(ViewPostInputData viewPostInputData) {
        String postId = viewPostInputData.getPostId();

        if (!viewPostDataAccess.existsPost(postId)) {
            viewPostPresenter.prepareFailView("Post not found.");
            return;
        }

        Post post = viewPostDataAccess.getPost(postId);
        List<Comment> comments = viewPostDataAccess.getCommentsForPost(postId);

        ViewPostOutputData outputData = new ViewPostOutputData(post, comments);
        viewPostPresenter.prepareSuccessView(outputData);
    }
}
