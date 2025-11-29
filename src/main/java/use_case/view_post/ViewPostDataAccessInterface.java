// Ioane
package use_case.view_post;

import entity.Post;

public interface ViewPostDataAccessInterface {

    Post getPost(String username, int postId);
}