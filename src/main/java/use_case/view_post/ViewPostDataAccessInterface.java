package use_case.view_post;

import entity.Post;

public interface ViewPostDataAccessInterface {
    /**
     * Return the post with the given postId for the given username,
     * or null if not found.
     */
    Post getPost(String username, int postId);
}
