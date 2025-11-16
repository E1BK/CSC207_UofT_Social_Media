package interface_adapter.view_post;

import View.PostView;
import entity.Comment;
import entity.Post;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPostPresenter implements ViewPostOutputBoundary {

    private final PostView postView;

    public ViewPostPresenter(PostView postView) {
        this.postView = postView;
    }

    @Override
    public void prepareSuccessView(ViewPostOutputData outputData) {
        Post post = outputData.getPost();
        List<Comment> commentEntities = outputData.getComments();

        // Set post title and body in the GUI
        postView.setPost(post.getTitle(), post.getBody());

        // Convert Comment entities to Strings for the view
        List<String> commentsAsText = new ArrayList<>();
        for (Comment c : commentEntities) {
            commentsAsText.add(c.getBody());
        }

        // PostView will pick 3 random comments to show
        postView.setComments(commentsAsText);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
