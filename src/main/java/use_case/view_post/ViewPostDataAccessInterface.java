// Ioane
package use_case.view_post;

import entity.Post;

/**
 * Interface for retrieving a single Post for a given user.
 * Implemented by DBUserDataAccessObject.
 */
public interface ViewPostDataAccessInterface {

    /**
     * Return the post with the given id for the given username,
     * or null if not found.
     */
    Post getPost(String username, int postId);
}
