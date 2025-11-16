package interface_adapter.add_comment;

import View.PostView;
import use_case.add_comment.AddCommentOutputBoundary;
import use_case.add_comment.AddCommentOutputData;

import javax.swing.*;

public class AddCommentPresenter implements AddCommentOutputBoundary {

    private final PostView postView;

    public AddCommentPresenter(PostView postView) {
        this.postView = postView;
    }

    @Override
    public void prepareSuccessView(AddCommentOutputData outputData) {
        // Clear the input box after successful comment
        postView.clearCommentInput();

        JOptionPane.showMessageDialog(
                null,
                "Comment added successfully."
        );

        // If you want the new comment to appear immediately,
        // you can later re-run the ViewPost use case from here.
    }

    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
