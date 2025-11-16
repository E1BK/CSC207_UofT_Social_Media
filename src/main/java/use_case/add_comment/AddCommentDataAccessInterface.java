package use_case.add_comment;

import entity.User;
import entity.Post;

/**
 * Data access for adding a comment.
 *
 * Typical implementation (with Grade API):
 * - Uses user repository user ("user_repository_*") to know all existing users.
 * - Finds which user owns the post with the given postId (searching info.posts).
 * - Loads that owner's User object.
 * - Converts User/Post to entity.User/entity.Post.
 * - After modification, writes the updated User back to Grade API.
 */
public interface AddCommentDataAccessInterface {

    boolean existsUser(String username);   // commenter exists?

    boolean existsPost(String postId);     // post exists somewhere in the system?

    /**
     * Returns the User who owns the post with the given postId.
     * The implementation will find this by scanning the Grade API user repository.
     */
    User getOwnerOfPost(String postId);

    /**
     * Returns the Post (entity) with this postId.
     * The implementation will build a Post from the JSON under the owner's info.posts.
     */
    Post getPost(String postId);

    /**
     * Saves the owner User back to Grade API, including its modified posts/comments.
     */
    void saveOwner(User owner);
}
