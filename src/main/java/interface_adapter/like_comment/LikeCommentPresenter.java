package interface_adapter.like_comment;

import View.PostView;
import use_case.like_comment.LikeCommentOutputBoundary;
import use_case.like_comment.LikeCommentOutputData;

import javax.swing.*;

public class LikeCommentPresenter implements LikeCommentOutputBoundary {

    private final PostView postView;

    public LikeCommentPresenter(PostView postView) {
        this.postView = postView;
    }

    @Override
    public void prepareSuccessView(LikeCommentOutputData outputData) {
        String message = "Liked comment " + outputData.getCommentId()
                + ". New likes: " + outputData.getNewLikeCount();
        JOptionPane.showMessageDialog(null, message);

        // Later, if your PostView shows like counts, you’d also
        // update that display here.
    }

    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
