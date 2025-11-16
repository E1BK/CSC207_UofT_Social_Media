package use_case.view_post;

import entity.Post;
import entity.Comment;

import java.util.List;

public class ViewPostInteractor implements ViewPostInputBoundary {

    private final ViewPostDataAccessInterface dataAccess;
    private final ViewPostOutputBoundary presenter;

    public ViewPostInteractor(ViewPostDataAccessInterface dataAccess,
                              ViewPostOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewPostInputData inputData) {
        String postId = inputData.getPostId();

        if (!dataAccess.existsPost(postId)) {
            presenter.prepareFailView("Post not found.");
            return;
        }

        Post post = dataAccess.getPost(postId);
        List<Comment> comments = post.getComments();

        presenter.prepareSuccessView(new ViewPostOutputData(post, comments));
    }
}
