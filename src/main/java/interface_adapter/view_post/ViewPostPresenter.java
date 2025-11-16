package interface_adapter.view_post;

import View.PostView;
import entity.Post;
import entity.Comment;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

import javax.swing.*;
import java.util.List;

public class ViewPostPresenter implements ViewPostOutputBoundary {

    private final PostView postView;

    public ViewPostPresenter(PostView postView) {
        this.postView = postView;
    }

    @Override
    public void prepareSuccessView(ViewPostOutputData outputData) {
        Post post = outputData.getPost();
        List<Comment> comments = outputData.getComments();

        // Set post title and body in the GUI
        postView.setPost(post.getTitle(), post.getBody());

        // Pass the Comment entities directly to PostView
        postView.setComments(comments);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
