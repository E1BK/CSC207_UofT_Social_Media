package use_case.add_comment;

import entity.Comment;
import entity.CommentFactory;
import entity.Post;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddCommentInteractor implements AddCommentInputBoundary {

    private final AddCommentDataAccessInterface dataAccess;
    private final CommentFactory commentFactory;
    private final ViewPostOutputBoundary presenter;

    public AddCommentInteractor(AddCommentDataAccessInterface dataAccess,
                                CommentFactory commentFactory,
                                ViewPostOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.commentFactory = commentFactory;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddCommentInputData inputData) {
        try {
            // Get current post to compute new comment id
            Post post = dataAccess.getPost(inputData.getUsername(), inputData.getPostId());
            if (post == null) {
                throw new RuntimeException("Post not found.");
            }

            int nextId = 1;
            for (Comment c : post.getComments()) {
                if (c.getComment_id() >= nextId) {
                    nextId = c.getComment_id() + 1;
                }
            }

            String today = LocalDate.now().toString();
            Comment newComment = commentFactory.create(
                    nextId,
                    inputData.getCommentBody(),
                    today,
                    0
            );

            dataAccess.addCommentToPost(inputData.getUsername(), inputData.getPostId(), newComment);

            // Reload updated post
            Post updatedPost = dataAccess.getPost(inputData.getUsername(), inputData.getPostId());
            if (updatedPost == null) {
                throw new RuntimeException("Post not found after adding comment.");
            }

            List<Comment> comments = new ArrayList<>(updatedPost.getComments());
            Collections.shuffle(comments);

            int n = Math.min(3, comments.size());
            int[] ids = new int[n];
            String[] bodies = new String[n];
            int[] likes = new int[n];

            for (int i = 0; i < n; i++) {
                Comment c = comments.get(i);
                ids[i] = c.getComment_id();
                bodies[i] = c.getComment_body();
                likes[i] = c.getLikes();
            }

            ViewPostOutputData output = new ViewPostOutputData(
                    inputData.getUsername(),
                    inputData.getPostId(),
                    updatedPost.getTitle(),
                    updatedPost.getBody(),
                    ids,
                    bodies,
                    likes
            );
            presenter.prepareSuccessView(output);
        } catch (RuntimeException ex) {
            presenter.prepareFailView(ex.getMessage());
        }
    }
}