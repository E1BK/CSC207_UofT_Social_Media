package use_case.view_post;

import entity.Post;

/**
 * Data access for viewing a post.
 *
 * Typical implementation:
 * - Reads from Grade API "user_repository_*" user to get all usernames.
 * - For each username, loads that user's info.posts array.
 * - Finds the Post with the given postId, constructs an entity.Post
 *   (and its Comment entities) and returns it.
 */
public interface ViewPostDataAccessInterface {

    /**
     * Returns true if a post with this ID exists somewhere in the system
     * (i.e., under any user's info.posts in the Grade API).
     */
    boolean existsPost(String postId);

    /**
     * Loads the Post (including its comments) from Grade API via the user repository.
     */
    Post getPost(String postId);
}
