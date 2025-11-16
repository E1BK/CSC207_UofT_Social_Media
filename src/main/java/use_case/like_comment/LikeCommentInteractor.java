package use_case.like_comment;

import entity.User;
import entity.Post;
import entity.Comment;
import entity.CommentFactory;

import java.util.ArrayList;

public class LikeCommentInteractor implements LikeCommentInputBoundary {

    private final LikeCommentDataAccessInterface dataAccess;
    private final LikeCommentOutputBoundary presenter;
    private final CommentFactory commentFactory;

    public LikeCommentInteractor(LikeCommentDataAccessInterface dataAccess,
                                 LikeCommentOutputBoundary presenter,
                                 CommentFactory commentFactory) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.commentFactory = commentFactory;
    }

    @Override
    public void execute(LikeCommentInputData inputData) {
        String username = inputData.getUsername();
        String postId = inputData.getPostId();
        int commentId = inputData.getCommentId();

        if (!dataAccess.existsUser(username)) {
            presenter.prepareFailView("User does not exist.");
            return;
        }

        if (!dataAccess.existsPost(postId)) {
            presenter.prepareFailView("Post does not exist.");
            return;
        }

        if (!dataAccess.existsComment(postId, commentId)) {
            presenter.prepareFailView("Comment does not exist.");
            return;
        }

        if (dataAccess.hasUserLikedComment(username, postId, commentId)) {
            presenter.prepareFailView("User has already liked this comment.");
            return;
        }

        User owner = dataAccess.getOwnerOfPost(postId);
        Post post = dataAccess.getPost(postId);

        ArrayList<Comment> comments = post.getComments();

        int index = -1;
        Comment target = null;
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            if (c.getComment_id() == commentId) {
                index = i;
                target = c;
                break;
            }
        }

        if (target == null || index == -1) {
            presenter.prepareFailView("Comment not found in post.");
            return;
        }

        int newLikeCount = target.getLikes() + 1;

        // Since Comment has no setters, create updated Comment using CommentFactory
        Comment updated = commentFactory.create(
                target.getComment_id(),
                target.getBody(),
                newLikeCount
        );

        comments.set(index, updated);

        // Persist updated owner via Grade API
        dataAccess.saveOwner(owner);

        presenter.prepareSuccessView(
                new LikeCommentOutputData(postId, commentId, username, newLikeCount)
        );
    }
}
