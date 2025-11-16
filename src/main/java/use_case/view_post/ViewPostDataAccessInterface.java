package use_case.view_post;

import entity.Post;
import entity.Comment;

import java.util.List;

public interface ViewPostDataAccessInterface {
    /**
     * @param postId ID of the post
     * @return true if the post exists.
     */
    boolean existsPost(String postId);

    /**
     * @param postId ID of the post
     * @return the Post entity for that ID.
     */
    Post getPost(String postId);

    /**
     * @param postId ID of the post
     * @return list of comments for that post.
     */
    List<Comment> getCommentsForPost(String postId);
}
