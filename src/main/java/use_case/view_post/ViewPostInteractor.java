package use_case.view_post;

import entity.Comment;
import entity.Post;

import java.util.ArrayList;
import java.util.Collections;
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
        try {
            Post post = dataAccess.getPost(inputData.getUsername(), inputData.getPostId());
            if (post == null) {
                presenter.prepareFailView("Post not found.");
                return;
            }

            List<Comment> comments = new ArrayList<>(post.getComments());
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
                    post.getTitle(),
                    post.getBody(),
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
