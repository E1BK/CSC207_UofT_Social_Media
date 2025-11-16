package use_case.add_comment;

import entity.User;
import entity.Post;
import entity.Comment;
import entity.CommentFactory;

import java.util.ArrayList;

public class AddCommentInteractor implements AddCommentInputBoundary {

    private final AddCommentDataAccessInterface dataAccess;
    private final AddCommentOutputBoundary presenter;
    private final CommentFactory commentFactory;

    public AddCommentInteractor(AddCommentDataAccessInterface dataAccess,
                                AddCommentOutputBoundary presenter,
                                CommentFactory commentFactory) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.commentFactory = commentFactory;
    }

    @Override
    public void execute(AddCommentInputData inputData) {
        String username = inputData.getUsername();
        String postId = inputData.getPostId();
        String commentText = inputData.getCommentBody();

        if (!dataAccess.existsUser(username)) {
            presenter.prepareFailView("User does not exist.");
            return;
        }
        if (!dataAccess.existsPost(postId)) {
            presenter.prepareFailView("Post does not exist.");
            return;
        }
        if (commentText == null || commentText.trim().isEmpty()) {
            presenter.prepareFailView("Comment cannot be empty.");
            return;
        }

        // Owner of the post (the one whose info.posts contains this post)
        User owner = dataAccess.getOwnerOfPost(postId);
        Post post = dataAccess.getPost(postId);

        ArrayList<Comment> comments = post.getComments();
        int newCommentId = comments.size() + 1; // simple scheme; adjust if needed

        Comment newComment = commentFactory.create(newCommentId, commentText, 0);
        comments.add(newComment);

        // Persist changes via Grade API
        dataAccess.saveOwner(owner);

        presenter.prepareSuccessView(
                new AddCommentOutputData(postId, username, commentText)
        );
    }
}
