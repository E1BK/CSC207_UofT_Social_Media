package use_case.add_comment;

import entity.Comment;
import use_case.view_post.ViewPostDataAccessInterface;

public interface AddCommentDataAccessInterface extends ViewPostDataAccessInterface {

    void addCommentToPost(String username, int postId, Comment comment);
}