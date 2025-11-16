//Ioane Bekurishvili

package interface_adapter.view_post;

import entity.Comment;
import entity.Post;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;
import View.PostView;

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
        List<Comment> comments = outputData.getComments();

        // Set post title & body on the GUI
        postView.setPost(post.getTitle(), post.getBody());

        // Convert Comment entities to plain strings for the view
        List<String> commentStrings = new ArrayList<>();
        for (Comment c : comments) {
            // You can include likes if you want, e.g. "body (likes: X)"
            commentStrings.add(c.getBody());
        }

        // The PostView will randomly pick 3 to show
        postView.setComments(commentStrings);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // For now you might show a dialog, or set some label.
        // Example:
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}