package use_case.like_comment;

import entity.User;
import entity.Post;
import entity.Comment;

/**
 * Data access for liking a comment.
 *
 * Typical Grade API implementation:
 * - Uses repository user to iterate over all users and their info.posts.
 * - Finds the Post with postId, and within it the Comment with commentId.
 * - Updates likes, writes the owner User back to Grade API.
 */
public interface LikeCommentDataAccessInterface {

    boolean existsUser(String username);        // liker exists?

    boolean existsPost(String postId);          // post exists?

    boolean existsComment(String postId, int commentId);

    /**
     * Returns the User who owns the post with this postId.
     */
    User getOwnerOfPost(String postId);

    /**
     * Returns the Post that contains this comment.
     */
    Post getPost(String postId);

    /**
     * Optional: prevent double-liking.
     * Implementation can return false if double-like tracking isn't implemented.
     */
    boolean hasUserLikedComment(String username, String postId, int commentId);

    /**
     * Persists the updated owner (with modified posts/comments) back to Grade API.
     */
    void saveOwner(User owner);
}
